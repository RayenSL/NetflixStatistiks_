package DataStorageLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DAOEpisode {

    ResultSet resultSet;

    // Query's Defined
    private static final String TABLE = "Episode";

    public static final String TITLE = "Title";
    public static final String DURATION = "Duration";
    public static final String SERIE_ID = "SerieID";
    public static final String EPIDODE_ID = "EpisodeID";

    // Returns all Episodes
    public static String getEpisodes() {
        return "SELECT * FROM " + TABLE;
    }

    // Returns Episode with the Serie - ID
    public static String getEpisodeSerieId(int serieId) {
        return "SELECT * FROM " + TABLE + " WHERE " + serieId + " = " + serieId;
    }

    public HashMap<Integer, HashMap<String, String>> hashMapforAllEpisodes() {
        HashMap<Integer, HashMap<String, String>> hashMapHashMap = new HashMap<>();

        try {


            resultSet = DatabaseConnection.getStatementResult(getEpisodes());
            int episodeId;
            while (resultSet.next()) {
                episodeId = resultSet.getInt(EPIDODE_ID);
                HashMap<String, String> stringStringHashMap = new HashMap<>();

                stringStringHashMap.put(TITLE, resultSet.getString(TITLE));
                stringStringHashMap.put(SERIE_ID, resultSet.getString(SERIE_ID));
                stringStringHashMap.put(DURATION, resultSet.getString(DURATION));

                hashMapHashMap.put(episodeId, stringStringHashMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        return hashMapHashMap;
    }


    public boolean update_Episode(String query) {
        boolean isSaved = false;

        try {
            DatabaseConnection.executeSQLCreateStatement(query);
            isSaved = true;
        } catch (Exception ignored) {
        }
        return isSaved;
    }

    
}