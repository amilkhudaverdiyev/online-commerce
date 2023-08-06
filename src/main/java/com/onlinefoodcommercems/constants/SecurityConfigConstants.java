package com.onlinefoodcommercems.constants;

public class SecurityConfigConstants {
    public static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/v2/api-docs/",
            "/error/**",
            "/swagger-resources/**"

    };
    public static final String REGISTRATION="/registration/**";
    public static final String API_HOME="/api/home/**";
    public static final String PDF_GENERATE="/pdf/generate/**";
    public static final String DELETE_COOKIES="JSESSIONID";
    public static final String LOGIN="/login";
    public static final String LOGOUT="/logout";
    public static final String DO_LOGIN="/do-login";
}
