package org.esei.moneygest.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String email;
    private String login;
    private String password;
    private static final String PATTERN_EMAIL ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public User(String email, String login_user, String password) {

        this.email = email;
        this.login = login_user;
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login_user) {

        this.login = login_user;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public static boolean validateEmail(String email) {

        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
