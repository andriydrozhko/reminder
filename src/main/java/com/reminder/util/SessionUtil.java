package com.reminder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class SessionUtil {

    private SessionUtil() {}

    private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);

    public static String getLoggedUserEmail() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName)
                .orElse("");
    }

}
