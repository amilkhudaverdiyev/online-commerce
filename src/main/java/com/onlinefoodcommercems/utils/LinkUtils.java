package com.onlinefoodcommercems.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class LinkUtils {
    public static String createImageLink(String filename) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/file/db/" + filename)
                .toUriString();
    }

    public static String createPlaceOrderLink(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .replacePath("order/place-order/" + username)
                .toUriString();
    }

    public static String downloadPDFLink(Long userId) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .replacePath("/pdf/generate/" + userId)
                .toUriString();
    }
}
