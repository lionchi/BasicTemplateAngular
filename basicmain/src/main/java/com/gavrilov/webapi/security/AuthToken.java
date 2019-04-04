package com.gavrilov.webapi.security;

public class AuthToken {
    private String token;
    private String login;

    public AuthToken(){

    }

    public AuthToken(String token, String login){
        this.token = token;
        this.login = login;
    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
