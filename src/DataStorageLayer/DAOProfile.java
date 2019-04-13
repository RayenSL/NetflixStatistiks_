package DataStorageLayer;

import java.sql.ResultSet;
import java.util.HashMap;

public class DAOProfile {
    private ResultSet resultSet;

    private static final String TABLE = "Profile";

    public static final String ACCOUNT_ID = "AccountID";
    public static final String PROFILE_ID = "ProfileID";
    public static final String NAME = "ProfileName";
    public static final String DATE_OF_BIRTH = "DateOfBirth";

    public static String getAllProfiles() {
        return "SELECT * FROM " + TABLE;
    }

    public static String getLatestProfile() {
        return "SELECT TOP 1 * FROM " + TABLE + " ORDER BY " + PROFILE_ID + " DESC";
    }

    public static String addProfile(int AccountID, String ProfileName, String DateOfBirth) {
        return "INSERT INTO " + TABLE +
                " VALUES("
                + "'" + ProfileName + "', "
                + "'" + DateOfBirth + "', "
                + "'" + AccountID + "')" + ";";
    }

    public static String updateProfile(int AccountID, int ProfileID, String ProfileName, String DateOfBirth) {
        return "UPDATE " + TABLE +
                " SET "
                + NAME + " = '" + ProfileName + "', "
                + DATE_OF_BIRTH + " = '" + DateOfBirth + "' "
                + "WHERE " + ACCOUNT_ID + " = " + AccountID + ""
                + "AND " + PROFILE_ID + " = " + ProfileID + "" + ";";
    }

    public static String deleteProfile(int AccountID, int ProfileID) {
        return "DELETE FROM " + TABLE
                + " WHERE " + ACCOUNT_ID + " = " + AccountID
                + " AND " + PROFILE_ID + " = " + ProfileID + ";";
    }

    public HashMap<Integer, HashMap<String, String>> getAllProfilesInHashmap() {
        HashMap<Integer, HashMap<String, String>> hashMap = new HashMap<>();

        try {
            resultSet = DatabaseConnection.getStatementResult(getAllProfiles());

            while (resultSet.next()) {

                int ProfileID = resultSet.getInt(PROFILE_ID);
                HashMap<String, String> data = new HashMap<>();

                data.put(ACCOUNT_ID, resultSet.getString(ACCOUNT_ID));
                data.put(PROFILE_ID, resultSet.getString(PROFILE_ID));
                data.put(NAME, resultSet.getString(NAME));
                data.put(DATE_OF_BIRTH, resultSet.getString(DATE_OF_BIRTH));


                hashMap.put(ProfileID, data);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }

        return hashMap;
    }

    public boolean updateProfile(String query) {
        boolean isSaved = false;

        try {
            DatabaseConnection.executeSQLCreateStatement(query);
            isSaved = true;
        } catch (Exception ignored) {
        }
        return isSaved;
    }

}
