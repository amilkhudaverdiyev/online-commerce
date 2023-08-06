package com.onlinefoodcommercems.config;

import com.onlinefoodcommercems.constants.SecurityConfigConstants;
import com.onlinefoodcommercems.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {


    private final FilterConfig filterConfig;


    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

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
                .requestMatchers(SecurityConfigConstants.REGISTRATION,SecurityConfigConstants.API_HOME,SecurityConfigConstants.PDF_GENERATE,/*"/swagger-ui.html"*/ "/")
                .permitAll()
                .requestMatchers(SecurityConfigConstants.AUTH_WHITE_LIST).permitAll()
                //.requestMatchers("/admin/**").hasAuthority(Roles.ADMIN.toString())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(SecurityConfigConstants.LOGIN)
                .loginProcessingUrl(SecurityConfigConstants.DO_LOGIN)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(SecurityConfigConstants.LOGOUT))
                .logoutSuccessUrl(SecurityConfigConstants.LOGIN)
                .invalidateHttpSession(true)
                .deleteCookies(SecurityConfigConstants.DELETE_COOKIES)
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        return http.build();

    }
}
