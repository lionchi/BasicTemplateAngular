package com.gavrilov.webapi.commons;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SessionFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        Cookie[] allCookies = req.getCookies();
        if (allCookies != null) {
            List<Cookie> cookies = Arrays.stream(allCookies)
                    .filter(x -> x.getName().equals("JSESSIONID") && x.getName().equals("REMEMBER"))
                    .collect(Collectors.toList());
            cookies.forEach(cookie -> {
                cookie.setHttpOnly(true);// Не даем браузеру использовать куки
                cookie.setSecure(true);// Кук файл будет отправлен только через https
                res.addCookie(cookie);
            });
        }
        // filterChain список фильтров предназначенных для обработки
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
