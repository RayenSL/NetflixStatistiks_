import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseConnectionTestTest {

    private static  Connection databaseConnection;

    @Test
    void databaseConnection() {

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
            }
            assertTrue(connectionCheck(), "Failed");
        }

    // Checks if there is any connection with the database
    private static boolean connectionCheck() {
        // if there isn't any connection the output is false
        if (databaseConnection == null) return false;
        // Sending a test to the connection
        try {
            return databaseConnection.isValid(1000);
        } catch (SQLException e) {
            return false;
        }
    }
    }
