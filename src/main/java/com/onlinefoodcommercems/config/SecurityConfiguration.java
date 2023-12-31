package com.onlinefoodcommercems.config;

import com.onlinefoodcommercems.constants.SecurityConfigConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final FilterConfig filterConfig;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .apply(filterConfig)
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(SecurityConfigConstants.API_AUTH, SecurityConfigConstants.API_ADMIN, SecurityConfigConstants.API_HOME, SecurityConfigConstants.PDF_GENERATE,/*"/swagger-ui.html"*/ "/")
                .permitAll()
                .requestMatchers(SecurityConfigConstants.AUTH_WHITE_LIST).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .logout()
                .logoutUrl(SecurityConfigConstants.LOGOUT)
                .invalidateHttpSession(true)
                .deleteCookies(SecurityConfigConstants.DELETE_COOKIES)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        return http.build();

    }
}
