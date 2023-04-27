package user;

import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import util.InputValidator;

public class User {
    private final int id;
    private final String username;
    private final String password;
    private final String email;

    public User(int id, String username, String password, String email)
            throws InvalidPasswordException, InvalidUsernameException, InvalidEmailException {
        validateInput(username, password, email);
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private void validateInput(String username, String password, String email)
            throws InvalidUsernameException, InvalidPasswordException, InvalidEmailException {
        InputValidator inputValidator = new InputValidator();
        inputValidator.validateUsername(username);
        inputValidator.validatePassword(password);
        inputValidator.validateEmail(email);

    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "| " +
                "id=" + id +
                " | username='" + username+
                " | password='" + password+
                " | email='" + email+
                " |";
    }
}
