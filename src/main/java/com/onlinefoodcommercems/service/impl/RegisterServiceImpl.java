package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CustomerRequest;
import com.onlinefoodcommercems.dto.request.PasswordResetRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationRequest;
import com.onlinefoodcommercems.dto.user.AuthenticationResponse;
import com.onlinefoodcommercems.entity.ConfirmationToken;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.entity.UserAuthority;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.exception.PasswordRequestException;
import com.onlinefoodcommercems.mapper.AddressMapper;
import com.onlinefoodcommercems.mapper.CustomerMapper;
import com.onlinefoodcommercems.repository.ConfirmationTokenRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.ConfirmationTokenService;
import com.onlinefoodcommercems.service.RegisterService;
import com.onlinefoodcommercems.service.email.EmailSender;
import com.onlinefoodcommercems.service.email.EmailValidator;
import com.onlinefoodcommercems.service.jwt.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final UserDetailsServiceImpl userDetailsServiceImp;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final AddressMapper addressMapper;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void changePassword(String email, PasswordResetRequest request) {
        log.error("request " + request);
        if (request.getNewPassword() != null && !request.getNewPassword().equals(request.getRepeatPassword())) {
            throw new PasswordRequestException(ResponseMessage.PASSWORDS_DO_NOT_MATCH);
        }

        var user = customerRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND));
        log.error("user" + user);
        if (bCryptPasswordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            log.error("passwords" + user.getPassword(), request.getOldPassword());
            user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
            customerRepository.save(user);
        } else {
            throw new PasswordRequestException(ResponseMessage.WRONG_OLD_PASSWORD);
        }
    }

    @Override
    public String forgotPassword(String username) {
        var user = customerRepository.findByUsername(username).orElseThrow(() -> new NotDataFound(ResponseMessage.USER_NOT_FOUND));
        log.error("username" + username);
        try {
            user.setPassword(null);
            emailSender.sendOtpEmail(username);
            log.error("email" + emailSender);
        } catch (MessagingException e) {
            return ResponseMessage.MESSAGE_DONT_SEND;
        }
        return username;
    }

    @Override
    public void setPassword(String username, String  code, String newPassword) {
        var user = customerRepository.findByUsername(username).orElseThrow(() -> new NotDataFound(ResponseMessage.USER_NOT_FOUND));
        log.error("activation code in database " + user.getActivationCode());
        log.error("activation code from request " + code);
        if (Objects.equals(code, user.getActivationCode())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user.setActivationCode(null);
            customerRepository.save(user);
        } else {
            throw new NotDataFound(ResponseMessage.ACTIVATION_CODE_WRONG);
        }

    }


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        log.error("request {}", request);
        var user = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.ACCESS_DENIED));
        log.error("user{}", user);
        if (user.isEnabled()) {
            if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                var token = jwtService.generateToken(user);
                updateToken(user, token);
                log.error("token === " + token);
                return AuthenticationResponse.builder().username(request.getUsername()).token(token).build();
            } else {
                throw new PasswordRequestException(ResponseMessage.PASSWORD_IS_WRONG);
            }
        } else {
            throw new UsernameNotFoundException(ResponseMessage.NOT_CONFIRMED);
        }
    }

    @Override
    public void registerAdmin(CustomerRequest request) {
        boolean isValidEmail = emailValidator.test(request.getUsername());
        log.error("is valid " + isValidEmail);
        if (!isValidEmail) {
            throw new IllegalStateException(ResponseMessage.EMAIL_NOT_VALID);
        }
        var customer = customerMapper.fromDTO(request);
        var address = addressMapper.fromDTO(request.getAddress());
        log.error("address {}", address);
        customer.setAddress(address);
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setPassword(request.getPassword());
        customer.setUsername(request.getUsername());
        customer.setBirthDate(request.getBirthDate());
        customer.setAccountNonExpired(true);
        customer.setAccountNonLocked(true);
        customer.setCredentialsNonExpired(true);
        log.error("customer {}", customer);
        var authority = new UserAuthority();
        authority.setAuthority(Roles.ADMIN);
        authority.setCustomer(customer);
        log.error("authority {}", authority);
        customer.addAuthority(authority);

        String token = userDetailsServiceImp.signUpUser(customer);
        log.error("token " + token);
        String link = "http://localhost:2020/api/v1/auth/confirm?token=" + token;
        emailSender.send(request.getUsername(), buildEmail(request.getName() + " " + request.getSurname(), link));

    }

    @Override
    public void register(CustomerRequest request) {
        boolean isValidEmail = emailValidator.test(request.getUsername());
        log.error("is valid" + isValidEmail);
        if (!isValidEmail) {
            throw new IllegalStateException(ResponseMessage.EMAIL_NOT_VALID);
        }
        var customer = customerMapper.fromDTO(request);
        var address = addressMapper.fromDTO(request.getAddress());
        log.error("address {}", address);
        customer.setAddress(address);
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setPassword(request.getPassword());
        customer.setUsername(request.getUsername());
        customer.setBirthDate(request.getBirthDate());
        customer.setAccountNonExpired(true);
        customer.setAccountNonLocked(true);
        customer.setCredentialsNonExpired(true);
        log.error("customer {}", customer);
        var authority = new UserAuthority();
        authority.setAuthority(Roles.USER);
        authority.setCustomer(customer);
        log.error("authority {}", authority);
        customer.addAuthority(authority);


        String token = userDetailsServiceImp.signUpUser(customer);
        log.error("token" + token);
        String link = "http://localhost:2020/api/v1/auth/confirm?token=" + token;
        emailSender.send(request.getUsername(), buildEmail(request.getName() + " " + request.getSurname(), link));

    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException(ResponseMessage.TOKEN_NOT_FOUND));
        log.error("confirm token" + confirmationToken);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException(ResponseMessage.EMAIL_ALREADY_CONFIRMED);
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        log.error("expiredAt" + expiredAt);
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException(ResponseMessage.TOKEN_EXPIRED);
        }
        confirmationTokenService.setConfirmedAt(token);
        userDetailsServiceImp.enableUser(
                confirmationToken.getCustomer().getUsername());
        return ResponseMessage.CONFIRMED;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    private void updateToken(Customer customer, String token) {
        var tokens = customer.getToken();
        log.error("tokens {}", tokens);
        for (ConfirmationToken tokenEntity : tokens
        ) {
            tokenEntity.setToken(token);
            log.error("tokenEntity {}", tokenEntity);
            confirmationTokenRepository.save(tokenEntity);
        }
    }

}


