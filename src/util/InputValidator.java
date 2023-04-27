package util;

import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import exception.InvalidUsernameException;

import java.util.regex.Pattern;

public class InputValidator {
    private final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
    private final String EMAIL_PATTERN = "^[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+[a-zA-Z]+$";
    private final String USERNAME_PATTERN = "^[a-zA-Z0-9_.]+$";


    public void validateUsername(String username) throws InvalidUsernameException {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        if (!pattern.matcher(username).matches()) {
            throw new InvalidUsernameException();
        }
    }

    public void validatePassword(String password) throws InvalidPasswordException {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (!pattern.matcher(password).matches()) {
            throw new InvalidPasswordException();
        }
    }

    public void validateEmail(String email) throws InvalidEmailException {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(email).matches()) {
            throw new InvalidEmailException();
        }
    }
}
