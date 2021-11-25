package org.esei.moneygest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private int id; // Primary Key
    private String email;
    private String login_user;
    private String password;
    private static final String PATTERN_EMAIL ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getlogin_user() {
        return login_user;
    }

    public void setlogin_user(String login_user) {
        this.login_user = login_user;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public static boolean validateEmail(String email) {

        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
