import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import logging.MyLogger;
import user.User;
import user.UserDAO;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
    private final Logger LOGGER = MyLogger.getLogger();

    public void startMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do{
                displayMenuOptions();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> promptUserCreation();
                    case 2 -> promptUserRead();
                    case 3 -> promptAllUsersRead();
                    case 4 -> promptUserUpdate();
                    case 5 -> promptUserDeletion();
                    case 0 -> System.exit(0);
                    default -> System.err.println("Invalid choice.");
                }
                System.out.println();
            }
            while (true);
        } catch (NumberFormatException e) {
            System.err.println("Invalid choice. Please enter a valid integer.");
        }
    }

    public void displayMenuOptions(){
        System.out.println("Choose one:");
        System.out.println("1 to create a User;");
        System.out.println("2 to read a User;");
        System.out.println("3 to read all Users;");
        System.out.println("4 to update a User;");
        System.out.println("5 to delete a User;");
        System.out.println("0 to exit the program.");
    }

    private void promptUserCreation() {
        User userToCreate = getInputForUser(0);
        if(userToCreate!=null){
            new UserDAO().createUser(userToCreate);
            System.out.println("User created.");
        }
    }

    private User getInputForUser(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new username(no special symbols): ");
        String username = scanner.nextLine();

        System.out.println("Enter new password(At least one uppercase, one lowercase letter and a number): ");
        String password = scanner.nextLine();

        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        try {
            return new User(id, username, password, email);
        } catch (InvalidUsernameException | InvalidPasswordException | InvalidEmailException e) {
            LOGGER.severe("Create user failed: " + e.getMessage());
            return null;
        }
    }

    private void promptUserRead() {
        int id = getInputForUserId();
        User user = new UserDAO().readUser(id);
        System.out.println(user.toString());
    }

    private int getInputForUserId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of User:");
        return scanner.nextInt();
    }

    private void promptAllUsersRead() {
        List<User> allUsers = new UserDAO().readAllUsers();
        for (User i : allUsers){
            System.out.println(i);
        }
    }

    private void promptUserUpdate() {
        int id = getInputForUserId();
        User userToUpdate = getInputForUser(id);
        if(userToUpdate!=null){
            new UserDAO().updateUser(userToUpdate);
        }
    }

    private void promptUserDeletion() {
        int id = getInputForUserId();
        if(new UserDAO().deleteUser(id)){
            System.out.println("User deleted successfully");
        }
        else {
            System.out.println("No such user");
        }
    }
}