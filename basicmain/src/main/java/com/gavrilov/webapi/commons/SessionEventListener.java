package com.gavrilov.webapi.commons;

import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;

public class SessionEventListener extends HttpSessionEventPublisher {
    public SessionEventListener() {
        super();
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        super.sessionCreated(event);
        event.getSession().setMaxInactiveInterval(60 * 30); //Время действия сеанса. Сенас переходит в expired
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        super.sessionDestroyed(event);
        // Можно реализовать действия, при убивании сесси
    }
}
