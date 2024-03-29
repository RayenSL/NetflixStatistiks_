package DataStorageLayer;

import java.sql.*;

public class DatabaseConnection {

    private static Connection databaseConnection;
    private static Statement databaseStatement;
    private static ResultSet databaseResultSet;
    private static DatabaseConnection instance;



    public static Connection connect(){
        String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=NetflixStatistics;integratedSecurity=true;";
//        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetflixStatistics;integratedSecurity=true;";

        try {
        // Importing SQL driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // cleaning Database Connection
        databaseConnection = null;

        // Making a Database Connection
        databaseConnection = DriverManager.getConnection(connectionUrl);
    } catch(Exception exception) {
        exception.printStackTrace();
        }
        return databaseConnection;
    }

    // Disconnecting from the database
    public static void disconnect() {
        // If there is any connection with the database the connection gets shut down
        if (databaseConnection != null) {
            try {
                databaseConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Checks if there is any connection with the database
    public static boolean connectionCheck() {
        // if there isn't any connection the output is false
        if (databaseConnection == null) return false;
        // Sending a test to the connection
        try {
            return databaseConnection.isValid(1000);
        } catch (SQLException e) {
            return false;
        }
    }
    // Making a new statement to send to the database
    public static void executeSQLCreateStatement(String statement) throws SQLException {
        // Cleaning the statement
        databaseStatement = databaseConnection.createStatement();
        // Creating new statement
        databaseStatement.executeUpdate(statement);
    }

    // inserting new statement for the database
    public static void executeSQLInsertStatement(String statement){

        try {
            databaseStatement = databaseConnection.createStatement();

            databaseStatement.execute(statement, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Creating new statement. The result is returned afterwarths
    public static ResultSet getStatementResult(String providedStatement) {
        Statement statement;
        ResultSet resultSet;

        try {
            statement = databaseConnection.createStatement();
            // Parse statement to SQL
            resultSet = statement.executeQuery(providedStatement);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}