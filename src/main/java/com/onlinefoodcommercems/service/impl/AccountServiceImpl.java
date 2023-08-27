package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.AccountRequest;
import com.onlinefoodcommercems.dto.response.AccountResponse;
import com.onlinefoodcommercems.dto.update.AccountUpdateRequest;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.AccountMapper;
import com.onlinefoodcommercems.repository.AccountRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponse> getAllAccounts() {
        var accounts = accountRepository.findAll();
        log.error("account {}", accounts);
        return accountMapper.toDTOs(accounts);
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        var account = accountMapper.fromDTO(accountRequest);
        log.error("account {}", account);
        var customer = customerRepository.findById(accountRequest.getId())
                .orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        account.setCustomer(customer);
        log.error("account created {}", account);
        return accountMapper.toDTO(accountRepository.save(account));
    }

    @Override
    public void update(String username, AccountUpdateRequest accountRequest) {
        var customer = customerRepository.findByUsername(username).orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        if (customer.isEnabled()) {
            log.error("account {}", customer);
            accountMapper.toDtoUpdate(customer.getAccounts(), accountRequest);
            log.error("account updated {}", customer.getAccounts());
            accountRepository.save(customer.getAccounts());
        } else {
            throw new UsernameNotFoundException(ResponseMessage.ACCESS_DENIED);
        }
    }
}