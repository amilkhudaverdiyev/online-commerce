package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.user.LoginResponse;
import com.onlinefoodcommercems.entity.ConfirmationToken;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.jwt.JwtService;
import com.onlinefoodcommercems.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username {}", username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.EMAIL_NOT_FOUND));
    }

    public LoginResponse loadUserByUsernameAndPassword(String email, String password) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(ResponseMessage.USER_NOT_FOUND_MESSAGE, email)));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            var token = jwtService.generateToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUsername(user.getUsername());
            loginResponse.setName(user.getName());
            loginResponse.setSurname(user.getSurname());
            loginResponse.setToken(token);

            return loginResponse;
        } else {
            throw new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND);
        }
    }

    public String signUpUser(Customer user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException(ResponseMessage.EMAIL_ALREADY_TAKEN);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}
