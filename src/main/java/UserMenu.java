
import DAO.DAO;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import logging.MyLogger;
import util.InputValidator;
import user.User;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserMenu {
    private final Logger LOGGER = MyLogger.getLogger();
    private final InputValidator inputValidator = new InputValidator();
    private final DAO<User> userDao;

    public UserMenu(DAO<User> userDao){
        this.userDao = userDao;
    }
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try{
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
            System.err.println("Invalid choice. That is not a valid integer.");
            startMenu();
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
            userDao.create(userToCreate);
        }
    }

    private User getInputForUser(int id) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter username(no special symbols): ");
            String username = scanner.nextLine();
            inputValidator.validateUsername(username);

            System.out.println("Enter password(At least one uppercase, one lowercase letter and a number): ");
            String password = scanner.nextLine();
            inputValidator.validatePassword(password);

            System.out.println("Enter email:");
            String email = scanner.nextLine();
            inputValidator.validateEmail(email);

            return new User(id, username, password, email);
        } catch (InvalidUsernameException | InvalidPasswordException | InvalidEmailException e) {
            LOGGER.severe("User creation failed: " + e.getMessage());
            return null;
        }
    }

    private void promptUserRead() {
        int id = getInputForUserId();
        User user = userDao.read(id);
        if(user != null) {
            System.out.println(user);
        } else {
            System.err.println("No user with id " + id);
        }
    }

    private int getInputForUserId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of User:");
        return scanner.nextInt();
    }

    private void promptAllUsersRead() {
        List<User> allUsers = userDao.readAll();
        for (User i : allUsers){
            System.out.println(i);
        }
    }

    private void promptUserUpdate() {
        int id = getInputForUserId();
        User userToUpdate = getInputForUser(id);
        if(userToUpdate!=null){
            userDao.update(userToUpdate);
        }
    }

    private void promptUserDeletion() {
        int id = getInputForUserId();
        if(userDao.delete(id)){
            System.out.println("User deleted successfully");
        }
        else {
            System.err.println("No such user");
        }
    }
}