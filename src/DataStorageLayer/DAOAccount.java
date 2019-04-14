package DataStorageLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DAOAccount {

    private ResultSet resultSet;

    // Matching the database query's
    public static final String TABLE = "Account";
    public static final String ACCOUNT_ID = "AccountID";
    public static final String NAME = "Name";
    public static final String STREETNAME = "StreetName";
    public static final String HOUSENUMBER = "HouseNumber";
    public static final String HOUSENUMBERADDITION = "HouseNumberAddition";
    public static final String POSTALCODE = "PostalCode";
    public static final String CITY = "City";

    // Creating account returns a String
    public static String createAccount(String name, String streetname, String housenumber, String housenumberadd, String postalcode, String city) {
        return "INSERT INTO " + TABLE +
                " (" + name + ","
                + streetname + ","
                + housenumber + ","
                + housenumberadd + ","
                + postalcode + ","
                + city + ");";
    }

    // Updating account returns a string
    public static String updateAccount(int accountId, String name, String streetname, String housenumber, String housenumberadd, String postalcode, String city) {
        return "UPDATE " + TABLE + "SET"
                + NAME + " = '" + name + "', "
                + STREETNAME + " = '" + streetname + "', "
                + HOUSENUMBER + " = '" + housenumber + "', "
                + HOUSENUMBERADDITION + " = '" + housenumberadd + "', "
                + POSTALCODE + " = '" + postalcode + "', "
                + CITY + " = '" + city + "', "
                + "WHERE " + ACCOUNT_ID + " = " + accountId + ";";
    }

    // Deleting account returns a String
    public static String deleteAccount(int accountId) {
        return "DELETE FROM " + TABLE + " WHERE " + ACCOUNT_ID + " = " + accountId + ";";
    }

    // Getting all accounts returns a String
    public static String getAccounts() {
        return "SELECT * FROM " + TABLE;
    }

    // Getting the id from a given account returns a String
    public static String getAccountId(int accountId) {
        return "SELECT * FROM " + TABLE + " WHERE " + ACCOUNT_ID + " = " + accountId + ";";
    }

    // Getting the most recent account returns a String
    public static String getMostRecentAccount() {
        return "SELECT TOP 1 * FROM " + TABLE + " ORDER BY " + ACCOUNT_ID + " DESC";
    }

    //  Getting through the database connection all the accounts and puts it in the Hashmap
    public HashMap<Integer, HashMap<String, String>> getTheAccounts() {
        HashMap<Integer, HashMap<String, String>> hashMapHashMap = new HashMap<>();

        resultSet = DatabaseConnection.getStatementResult(getAccounts());
        try {
            while (resultSet.next()) {
                int AccountId = resultSet.getInt(DAOAccount.ACCOUNT_ID);
                HashMap<String, String> stringStringHashMap = new HashMap<>();

                stringStringHashMap.put(DAOAccount.NAME, resultSet.getString(DAOAccount.NAME));
                stringStringHashMap.put(DAOAccount.STREETNAME, resultSet.getString(DAOAccount.STREETNAME));
                stringStringHashMap.put(DAOAccount.HOUSENUMBER, resultSet.getString(DAOAccount.HOUSENUMBER));
                stringStringHashMap.put(DAOAccount.HOUSENUMBERADDITION, resultSet.getString(DAOAccount.HOUSENUMBERADDITION));
                stringStringHashMap.put(DAOAccount.POSTALCODE, resultSet.getString(DAOAccount.POSTALCODE));
                stringStringHashMap.put(DAOAccount.CITY, resultSet.getString(DAOAccount.CITY));

                hashMapHashMap.put(AccountId, stringStringHashMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        return hashMapHashMap;
    }

    // Getting the Account iD with the database Connection
    public HashMap<Integer, HashMap<String, String>> getAccountIdHashMap(int AccountId) {
        HashMap<Integer, HashMap<String, String>> hashMapHashMap = new HashMap<>();

        resultSet = DatabaseConnection.getStatementResult(getAccountId(AccountId));

        try {
            int DAOaccountId = resultSet.getInt(DAOAccount.ACCOUNT_ID);
            HashMap<String, String> data = new HashMap<>();

            data.put(DAOAccount.NAME, resultSet.getString(DAOAccount.NAME));
            data.put(DAOAccount.STREETNAME, resultSet.getString(DAOAccount.STREETNAME));
            data.put(DAOAccount.HOUSENUMBER, resultSet.getString(HOUSENUMBER));
            data.put(DAOAccount.HOUSENUMBERADDITION, resultSet.getString(HOUSENUMBERADDITION));
            data.put(DAOAccount.POSTALCODE, resultSet.getString(POSTALCODE));
            data.put(DAOAccount.CITY, resultSet.getString(CITY));

            hashMapHashMap.put(DAOaccountId, data);
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        return hashMapHashMap;
    }

    // Getting the latest Account with ID
    public int getLatestAccountId() {
        int DAOaccountid = 0;

        try {

            resultSet = DatabaseConnection.getStatementResult(DAOAccount.getMostRecentAccount());

            if (resultSet.next()) {
                DAOaccountid = resultSet.getInt(ACCOUNT_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return DAOaccountid;
        }

        return DAOaccountid;
    }

    // Updating the account
    public boolean updateAccount(String query) {
        boolean isSaved = false;

        try {
            DatabaseConnection.executeSQLCreateStatement(query);
            isSaved = true;
        } catch (Exception ignored) {
        }
        return isSaved;
    }

    // Deleting the Account
    public static boolean deleteAccountExc(Integer accountID) {
        // Setting default value to false
        boolean isDeleted = false;

        // Try block which tries to run query from QueryManager
        try {
            DatabaseConnection.executeSQLCreateStatement(deleteAccount(accountID));
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    // Inserting the account
    public static String insertAccount(String name, String streetname, String housenumber, String housenumberaddition,
                                       String postalcode, String city) {
        return "INSERT INTO " + TABLE +
                " VALUES("
                + "'" + name + "', "
                + "'" + streetname + "', "
                + "'" + housenumber + "', "
                + "'" + housenumberaddition + "', "
                + "'" + postalcode + "', "
                + "'" + city + "')" + ";";
    }

}