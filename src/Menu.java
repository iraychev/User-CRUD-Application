import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void displayMenuOptions(){
        System.out.println("Choose one:");
        System.out.println("1 to create a User;");
        System.out.println("2 to read a User;");
        System.out.println("3 to read all Users;");
        System.out.println("4 to update a User;");
        System.out.println("5 to delete a User;");
        System.out.println("0 to exit the program.");
    }

    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        displayMenuOptions();
        int choice = scanner.nextInt();

        while(choice!= 0){
            switch (choice) {
                case (1) -> promptUserCreation();
                case (2) -> promptUserRead();
                case (3) -> promptAllUsersRead();
                case (4) -> promptUserUpdate();
                case (5) -> promptUserDeletion();
                default -> System.err.println("Invalid choice.");
            }
            System.out.println();
            displayMenuOptions();
            choice = scanner.nextInt();
        }
    }

    private static void promptUserCreation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username(no special symbols): ");
        String username = scanner.nextLine();

        System.out.println("Enter password(At least one uppercase, one lowercase letter and a number):");
        String password = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        new UserDAO().createUser(User.createUser(username, password, email));
        System.out.println("User created.");
    }

    private static void promptUserRead() {
        int id = getInputForUserId();
        User user = new UserDAO().readUser(id);
        System.out.println(user.toString());
    }

    private static int getInputForUserId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of User:");
        return scanner.nextInt();
    }

    private static void promptAllUsersRead() {
        List<User> allUsers = new UserDAO().readAllUsers();
        for (User i : allUsers){
            System.out.println(i);
        }
    }

    private static void promptUserUpdate() {
        User userToUpdate = getInputForUpdatedUser();

        User resultUser = new UserDAO().updateUser(userToUpdate);
        System.out.println("User changed to: \n"+resultUser);
    }
    private static User getInputForUpdatedUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of User:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new username(no special symbols): ");
        String username = scanner.nextLine();
        System.out.println("Enter new password(At least one uppercase, one lowercase letter and a number): ");
        String password = scanner.nextLine();
        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        return User.createUser(id, username,password,email);
    }
    private static void promptUserDeletion() {
        int id = getInputForUserId();
        if(new UserDAO().deleteUser(id)){
            System.out.println("User deleted successfully");
        }
        else {
            System.out.println("No such user");
        }
    }
}

