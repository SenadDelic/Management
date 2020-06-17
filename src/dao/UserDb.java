package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD database operations for table users
 */

public class UserDb {

    public void insertUser(User user) {
        try (PreparedStatement preparedStatement = ConnectionManagement.getInstance().getConnection().prepareStatement(Constants.INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;

        try (PreparedStatement preparedStatement = ConnectionManagement.getInstance().getConnection().prepareStatement(Constants.SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public List<User> printUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionManagement.getInstance().getConnection().prepareStatement(Constants.SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id) {
        boolean update = false;

        try (PreparedStatement preparedStatement = ConnectionManagement.getInstance().getConnection().prepareStatement(Constants.DELETE_USERS_SQL)) {
            preparedStatement.setInt(1, id);
            update = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }

    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try (PreparedStatement statement = ConnectionManagement.getInstance().getConnection().prepareStatement(Constants.UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated;
    }
}
