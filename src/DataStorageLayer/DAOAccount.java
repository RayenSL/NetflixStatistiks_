package DataStorageLayer;

import DomainLayer.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DAOAccount {

    private ResultSet resultSet;

    // Query's defined
    private static final String TABLE = "Account";
    private static final String ACCOUNT_ID = "AccountID";
    private static final String NAME = "Name";
    private static final String STREETNAME = "StreetName";
    private static final String HOUSENUMBER = "HouseNumber";
    private static final String HOUSENUMBERADDITION = "HouseNumberAddition";
    private static final String POSTALCODE = "PostalCode";
    private static final String CITY = "City";

//    public static String createAccount(String name, String streetname, String housenumber, String housenumberadd, String postalcode, String city){
//        return "INSERT INTO " + TABLE +
//                " (" + name + ","
//                + streetname + ","
//                + housenumber + ","
//                + housenumberadd + ","
//                + postalcode + ","
//                + city + ");";
//    }
//
//    public static String updateAccount(int accountId, String name, String streetname, String housenumber, String housenumberadd, String postalcode, String city){
//        return "UPDATE " + TABLE + "SET"
//                + NAME + " = '" + name + "', "
//                + STREETNAME + " = '" + streetname + "', "
//                + HOUSENUMBER + " = '" + housenumber + "', "
//                + HOUSENUMBERADDITION + " = '" + housenumberadd + "', "
//                + POSTALCODE + " = '" + postalcode + "', "
//                + CITY + " = '" + city + "', "
//                + "WHERE " + ACCOUNT_ID + " = " + accountId + ";";
//    }
//
//    public static String deleteAccount(int accountId){
//        return "DELETE FROM " + TABLE + " WHERE " + ACCOUNT_ID + " = " + accountId + ";";
//    }
//
    public static String getAccounts() {
        return "SELECT * FROM " + TABLE;
    }
//
//    public static String getAccountId(int accountId){
//        return "SELECT * FROM " + TABLE + " WHERE " + ACCOUNT_ID + " = " + accountId + ";"; }
//
        public static String getMostRecentAccount(){
        return "SELECT TOP 1 * FROM " + TABLE + " ORDER BY " + ACCOUNT_ID + " DESC";
        }

    // Creates new Account
    public void createAccount(int accountId, String name, String streetname, String housenumber, String housenumberadd, String postalcode, String city){
        Connection connection = DatabaseConnection.getInstance().connect();

        try {
            Statement statement = connection.createStatement();
            String Query = "INSERT INTO " + TABLE +
                " (" + name + ","
                + streetname + ","
                + housenumber + ","
                + housenumberadd + ","
                + postalcode + ","
                + city + ");";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Update the Account with new information
    public void updateAccount(int accountId, String name, String streetname, String housenumber, String housenumberadd, String postalcode, String city){
        Connection connection = DatabaseConnection.getInstance().connect();

        try {
            Statement statement = connection.createStatement();
            String Query = "UPDATE " + TABLE + "SET"
                + NAME + " = '" + name + "', "
                + STREETNAME + " = '" + streetname + "', "
                + HOUSENUMBER + " = '" + housenumber + "', "
                + HOUSENUMBERADDITION + " = '" + housenumberadd + "', "
                + POSTALCODE + " = '" + postalcode + "', "
                + CITY + " = '" + city + "', "
                + "WHERE " + ACCOUNT_ID + " = " + accountId + ";";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Deletes the Account with the given Account ID
    public void deleteAccount(int accountId){
        Connection connection = DatabaseConnection.getInstance().connect();

        try {
            Statement statement = connection.createStatement();
            String Query = "DELETE FROM " + TABLE + " WHERE " + ACCOUNT_ID + " = " + accountId + ";";
            statement.execute(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public HashMap<Integer, HashMap<String, String>> getTheAccounts() {
        HashMap<Integer, HashMap<String, String>> hashMapHashMap = new HashMap<>();

        resultSet = DatabaseConnection.getStatementResult(getAccounts());
        try {
            while (resultSet.next()) {
                int AccountId = resultSet.getInt(DAOAccount.ACCOUNT_ID);
                HashMap<String, String> stringStringHashMap= new HashMap<>();

                stringStringHashMap.put(DAOAccount.NAME, resultSet.getString(DAOAccount.NAME));
                stringStringHashMap.put(DAOAccount.STREETNAME, resultSet.getString(DAOAccount.STREETNAME));
                stringStringHashMap.put(DAOAccount.HOUSENUMBER, resultSet.getString(DAOAccount.HOUSENUMBERADDITION));
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


   public HashMap<Integer, HashMap<String, String>> getAccountId(int AccountId){
        HashMap<Integer, HashMap<String, String>> hashMapHashMap = new HashMap<>();

        resultSet = DatabaseConnection.getStatementResult(ACCOUNT_ID);

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

   public int getLatestAccountId(){
        int DAOaccountid = 0;

       try {

           resultSet = DatabaseConnection.getStatementResult(DAOAccount.getMostRecentAccount());

           if(resultSet.next()) {
               DAOaccountid = resultSet.getInt(ACCOUNT_ID);
           }
       } catch (SQLException e) {
           e.printStackTrace();
           return DAOaccountid;
       }

       return DAOaccountid;
   }


    public boolean updateAccount(String query) {
        boolean isSaved = false;

        try {
            DatabaseConnection.executeSQLCreateStatement(query);
            isSaved = true;
        } catch (Exception ignored) {
        }
        return isSaved;
    }

}