package exception;

public class InvalidUsernameException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Invalid username.";
    }
}
