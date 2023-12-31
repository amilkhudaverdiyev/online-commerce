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
        log.error("customer {}", customer);
        log.error("account {}", account);
        var orders = customer.getOrders();
        log.error("orders {}", orders);
        if (account.getAmount().compareTo(payment) < 0) {
            log.error("amount {}", account.getAmount());
            throw new NotDataFound(ResponseMessage.SMALL_AMOUNT_IN_ACCOUNT);
        } else {
            for (Order order : orders
            ) {
                if (payment.equals(order.getTotalAmount()) && order.getStatus().equals(OrderStatus.ACCEPTED)) {
                    log.error("total amount {}", order.getTotalAmount());
                    account.setAmount(customer.getAccounts().getAmount().subtract(payment));
                    log.error("accountSet {}", account);
                    accountRepository.save(account);
                    var receiveAccountEntity = accountRepository.findByCardNumber(recieveAccount).orElseThrow(
                            () -> new NotDataFound(ResponseMessage.ACCOUNT_NOT_FOUND));
                    log.error("receiveAccountEntity {}", receiveAccountEntity);
                    receiveAccountEntity.setAmount(receiveAccountEntity.getAmount().add(payment));
                    accountRepository.save(receiveAccountEntity);
                    var transactionLogRequest = TransactionLogRequest.builder()
                            .id(UUID.randomUUID().toString())
                            .senderId(account.getAccountId())
                            .receiverId(receiveAccountEntity.getAccountId())
                            .payment(payment)
                            .status(PaymentStatus.SUCCESSFULLY)
                            .build();
                    log.error("transactionLogRequest {}", transactionLogRequest);
                    transactionLogService.createTransactionLog(transactionLogRequest);
                    order.setStatus(OrderStatus.SENDING);
                    log.error("orderSet {}", order);
                    return ResponseMessage.AMOUNT_SEND_SUCCESSFULLY;
                }
            }
        }
        throw new NotDataFound(ResponseMessage.ORDER_NOT_FOUND);
    }
}

