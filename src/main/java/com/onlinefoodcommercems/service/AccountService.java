package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.AccountRequest;
import com.onlinefoodcommercems.dto.response.AccountResponse;
import com.onlinefoodcommercems.dto.update.AccountUpdateRequest;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAllAccounts();

    AccountResponse createAccount(AccountRequest account);

    void update(String id, AccountUpdateRequest accountRequest);
}
