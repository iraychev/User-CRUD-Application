package DAO;

import logging.MyLogger;
import user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class UserDAO implements DAO<User> {
    private final String DB_URL;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;
    private final Logger LOGGER = MyLogger.getLogger();
    public UserDAO(){
        try(FileInputStream fileInputStream = new FileInputStream("database_properties.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            DB_URL = properties.getProperty("DB_URL");
            DB_USERNAME = properties.getProperty("DB_USERNAME");
            DB_PASSWORD = properties.getProperty("DB_PASSWORD");
        } catch (IOException e){
            LOGGER.severe("Couldn't load database properties: "+ e.getMessage());
            throw new RuntimeException();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void create(User user) {
        String SqlQuery = "INSERT INTO user (username, password,email) VALUES(?, ?, ?)";

        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement(SqlQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        } catch(SQLException e){
            LOGGER.severe("User creation failed: "+e.getMessage());
        }
    }

    public User read(int id) {
        String SqlQuery = "SELECT * from user WHERE id = ?";
        User user = null;
        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement(SqlQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));
            }
        } catch(SQLException e){
            LOGGER.severe("User read failed: "+e.getMessage());
        }
        return user;
    }

    public List<User> readAll() {
        String SqlQuery = "SELECT * from user";
        List<User> users = new ArrayList<>();

        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement(SqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(id, username, password, email);
                users.add(user);
            }
        } catch(SQLException e){
            LOGGER.severe("All users read failed: "+e.getMessage());
        }
        return users;
    }


    public void update(User user) {
        String SqlQuery = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?";

        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SqlQuery);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("User with id " + user.getId() + "not found" );
            }
        }catch (SQLException e){
            LOGGER.severe("User update failed: "+e.getMessage());
        }
    }


    public boolean delete(int id) {
        String SQLQuery = "DELETE FROM user WHERE user.id = ?";
        boolean rowDeleted = false;

        try(Connection conn = getConnection()){
            PreparedStatement statement = conn.prepareStatement(SQLQuery);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e){
            LOGGER.severe("User deletion failed: "+e.getMessage());
        }
        return rowDeleted;
    }
}
