package dao;

public class Constants {
    protected static final String DB_NAME = "Management";
    protected static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DB_NAME;
    protected static final String TIME_ZONE_ERROR = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    // Users
    protected static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
            + " (?, ?, ?);";
    protected static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id = ?";
    protected static final String SELECT_ALL_USERS = "select * from users";
    protected static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    protected static final String UPDATE_USERS_SQL = "update users set name = ?, email= ?, country = ? where id = ?;";
}
