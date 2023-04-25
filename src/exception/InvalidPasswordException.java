package exception;

public class InvalidPasswordException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Invalid Password.";
    }
}
