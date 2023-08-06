package com.onlinefoodcommercems.jwt;

import com.onlinefoodcommercems.property.JwtProperty;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JwtProperty jwtProperty;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Request {}", request);
        log.info("Our JWT is in action ");
        log.info("request.getServletPath() {}", request.getServletPath());
//        if (request.getServletPath().contains("/v2/api-docs") || request.getServletPath().contains("swagger")
//                || request.getServletPath().contains("registration")
//                || request.getServletPath().contains("csrf") || request.getServletPath().contains("get-all")
//                || request.getServletPath().equals("/")) {
//            filterChain.doFilter(request, response);
//            log.info("Our filter stop ");
//            return;
//        }
        String header = request.getHeader(jwtProperty.getHeader());
        final String token;
        final String userEmail;
        if (header == null || !header.startsWith(jwtProperty.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("Header {}", header);
         token = header.substring(7);
        log.info("Token ===={}", token);
        userEmail = jwtService.extractUsername(token);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);


    }
}
