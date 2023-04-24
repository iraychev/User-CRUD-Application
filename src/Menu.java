import java.util.List;
import java.util.Scanner;

public class Menu {



    public static void printMenuOptions(){
        System.out.println("Choose one:");
        System.out.println("1 to create a User;");
        System.out.println("2 to read a User;");
        System.out.println("3 to read all Users;");
        System.out.println("4 to update a User;");
        System.out.println("5 to delete a User;");
        System.out.println("0 to exit the program.");
    }

    public static void chooseMenuOption() {
        Scanner scanner = new Scanner(System.in);
        printMenuOptions();
        int choice = scanner.nextInt();

        while(choice!= 0){
            switch (choice) {
                case (1) -> createUserChosen();
                case (2) -> readUserChosen();
                case (3) -> readAllUsersChosen();
                case (4) -> updateUserChosen();
                case (5) -> deleteUserChosen();
                default -> System.err.println("Invalid choice.");
            }
            System.out.println();
            printMenuOptions();
            choice = scanner.nextInt();
        }
    }

    private static void createUserChosen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        new UserDAO().createUser(new User(username, password, email));
        System.out.println("User created.");
    }

    private static void readUserChosen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of User:");
        int id = scanner.nextInt();

        User user = new UserDAO().readUser(id);

        System.out.println(user.toString());
    }

    private static void readAllUsersChosen() {
        List<User> allUsers = new UserDAO().readAllUsers();
        for (User i : allUsers){
            System.out.println(i);
        }
    }

    private static void updateUserChosen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id of User:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new username");
        String username = scanner.nextLine();
        System.out.println("Enter new password");
        String password = scanner.nextLine();
        System.out.println("Enter new email");
        String email = scanner.nextLine();

        User userToChange = new User(id, username,password,email);

        User resultUser = new UserDAO().updateUser(userToChange);
        System.out.println("User changed to: \n"+resultUser);
    }


    private static void deleteUserChosen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of User:");
        int id = scanner.nextInt();
        if(new UserDAO().deleteUser(id)){
            System.out.println("User deleted successfully");
        }
        else {
            System.out.println("No such user");
        }
    }
}

