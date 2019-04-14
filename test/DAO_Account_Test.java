import DataStorageLayer.DAOAccount;
import DataStorageLayer.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static DataStorageLayer.DAOAccount.ACCOUNT_ID;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAO_Account_Test {

    private static ResultSet resultSet;
    @Test
    void getLatestAccount() {
        int DAOaccountid = 0;
        DatabaseConnection.connect();
        
        try {

            resultSet = DatabaseConnection.getStatementResult(DAOAccount.getMostRecentAccount());

            if (resultSet.next()) {
                DAOaccountid = resultSet.getInt(ACCOUNT_ID);
            }
        } catch (SQLException e) {
        }

        assertTrue(isExecuted(), "The Method failed to pull the data");

    }

    private static boolean isExecuted() {
        if (resultSet == null){
            return false;
        } else return true;
    }
}
