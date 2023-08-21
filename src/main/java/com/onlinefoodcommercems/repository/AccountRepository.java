package com.onlinefoodcommercems.repository;

import com.onlinefoodcommercems.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findByCardNumber(String cardNumber);

}
