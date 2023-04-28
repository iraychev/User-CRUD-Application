import DAO.*;
import user.*;
public class Main {
    public static void main(String[] args) {
        DAO<User> userDao = new UserDAO();
        UserMenu userMenu = new UserMenu(userDao);
        userMenu.startMenu();
    }
}
