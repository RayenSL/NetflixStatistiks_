package DataStorageLayer;

public class DAOAccount {

    // Query's defined
    private static final String TABLE = "Account";
    private static final String ACCOUNT_ID = "AccountID";
    private static final String NAME = "Name";
    private static final String STREETNAME = "StreetName";
    private static final String HOUSENUMBER = "HouseNumber";
    private static final String HOUSENUMBERADDITION = "HouseNumberAddition";
    private static final String POSTALCODE = "PostalCode";
    private static final String CITY = "City";

    public static String getAccounts() {
        return "SELECT * FROM " + TABLE;
    }

//    public static void
}
