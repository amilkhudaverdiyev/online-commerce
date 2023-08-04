package com.onlinefoodcommercems.jwt;

import com.onlinefoodcommercems.property.JwtProperty;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final JwtProperty jwtProperty;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Request {}", request);
        log.info("Our JWT is in action ");
        log.info("request.getServletPath() {}", request.getServletPath());
        if (request.getServletPath().contains("/v2/api-docs") || request.getServletPath().contains("swagger")
                || request.getServletPath().contains("registration")
                || request.getServletPath().contains("csrf") || request.getServletPath().contains("get-all")
                || request.getServletPath().equals("/")) {
            filterChain.doFilter(request, response);
            log.info("Our filter stop ");
            return;
        }


        String header = request.getHeader(jwtProperty.getHeader());
        if (header == null || !header.startsWith(jwtProperty.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("Header {}", header);
        String token = getToken(header);
        log.info("Token ===={}", token);
        Claims claims = jwtService.verifyJwt(token);
        var auth = getRoles(claims, token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    public String getToken(String header) {
        if (header.startsWith(jwtProperty.getPrefix())) {
            return header.substring((jwtProperty.getPrefix() + " ").length());
        }
        throw new RuntimeException("Invalid token");
    }

    private Authentication getRoles(Claims claims, String token) {
        List<String> roles = claims.get("roles", List.class);
        List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        var jwtUser = JwtUser.builder()
                .id(Long.parseLong(claims.getId()))
                .username(claims.getSubject())
                .roles(authorities)
                .build();
        log.info("jwtUser {}", jwtUser);

        return new UsernamePasswordAuthenticationToken(jwtUser, token, authorities);

    }
}
