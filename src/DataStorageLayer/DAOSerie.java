package DataStorageLayer;

import java.sql.ResultSet;
import java.util.HashMap;

public class DAOSerie {

        private ResultSet resultSet;

        private static final String TABLE = "Serie";

        public static final String TITLE = "Title";
        public static final String SERIE_ID = "SerieID";
        public static final String DURATION = "Duration";
        public static final String LANGUAGE = "Language";
        public static final String GENRE = "Genre";
        public static final String AGE_INDICATION = "AgeIndication";

        public static String getAllSeries() {
            return "SELECT * FROM " + TABLE;
        }

        public static String getSeriesForSerieID(int SerieID) {
            return "SELECT * FROM " + TABLE +
                    " WHERE "
                    + SerieID + " = " + SerieID;
        }

        public static String getLatestSerie() {
            return "SELECT TOP 1 * FROM " + TABLE + " ORDER BY " + SERIE_ID + " DESC";
        }

        public boolean updateSerie(String query) {
        boolean isSaved = false;

        try {
            DatabaseConnection.executeSQLCreateStatement(query);
            isSaved = true;
        } catch (Exception ignored) {
        }
        return isSaved;
    }

    public HashMap<Integer, HashMap<String, String>> getAllSeriesHashmap() {
        HashMap<Integer, HashMap<String, String>> hashMapHashMap = new HashMap<>();

        try {
            resultSet = DatabaseConnection.getStatementResult(getAllSeries());

            while (resultSet.next()) {

                int SerieID = resultSet.getInt(SERIE_ID);
                HashMap<String, String> stringStringHashMap = new HashMap<>();

                stringStringHashMap.put(TITLE, resultSet.getString(TITLE));
                stringStringHashMap.put(GENRE, resultSet.getString(GENRE));
                stringStringHashMap.put(LANGUAGE, resultSet.getString(LANGUAGE));
                stringStringHashMap.put(AGE_INDICATION, resultSet.getString(AGE_INDICATION));

                hashMapHashMap.put(SerieID, stringStringHashMap);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }

        return hashMapHashMap;
    }

    }
