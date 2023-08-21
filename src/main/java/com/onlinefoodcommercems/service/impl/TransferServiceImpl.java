package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.TransactionLogRequest;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.enums.PaymentStatus;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.repository.AccountRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.TransactionLogService;
import com.onlinefoodcommercems.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionLogService transactionLogService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String transfer(String sendAccount, String recieveAccount, BigDecimal payment) {
        var customer = customerRepository.findByUsername(sendAccount).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND));
        var account = customer.getAccounts();
        var orders = customer.getOrders();
        if (account.getAmount().compareTo(payment) < 0) {
            throw new NotDataFound(ResponseMessage.SMALL_AMOUNT_IN_ACCOUNT);
        } else {
            for (Order order : orders
            ) {
                if (payment.equals(order.getTotalAmount()) && order.getStatus().equals(OrderStatus.ACCEPTED)) {
                    account.setAmount(customer.getAccounts().getAmount().subtract(payment));
                    accountRepository.save(account);
                    var receiveAccountEntity = accountRepository.findByCardNumber(recieveAccount).orElseThrow(
                            () -> new NotDataFound(ResponseMessage.ACCOUNT_NOT_FOUND));
                    receiveAccountEntity.setAmount(receiveAccountEntity.getAmount().add(payment));
                    accountRepository.save(receiveAccountEntity);
                    var transactionLogRequest = TransactionLogRequest.builder()
                            .id(UUID.randomUUID().toString())
                            .senderId(account.getAccountId())
                            .receiverId(receiveAccountEntity.getAccountId())
                            .payment(payment)
                            .status(PaymentStatus.SUCCESSFULLY)
                            .build();
                    transactionLogService.createTransactionLog(transactionLogRequest);
                    order.setStatus(OrderStatus.SENDING);
                    return ResponseMessage.AMOUNT_SEND_SUCCESSFULLY;
                }
            }
        }
        return "YES";
    }
}

