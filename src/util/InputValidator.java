package util;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+[a-zA-Z]+$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_.]+$";


    public static boolean validateUsername(String username){
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        return pattern.matcher(username).matches();
    }

    public static boolean validatePassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    public static boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
}
