package DataStorageLayer;

import DomainLayer.Genre;
import DomainLayer.Language;
import DomainLayer.Movie;

import java.sql.ResultSet;
import java.util.HashMap;

public class DAOMovie {

    private ResultSet resultSet;

    private static final String TABLE = "Movie";

    private static final String TITLE = "Title";
    private static final String MOVIE_ID = "MovieID";
    private static final String DURATION = "Duration";
    private static final String LANGUAGE = "Language";
    private static final String GENRE = "Genre";
    private static final String Age_Indication = "AgeIndication";

    public static String getMovies() {
        return "SELECT * FROM " + TABLE;
    }

    public static String getMoviesWithId(int movieId) {
        return "SELECT * FROM " + TABLE + " WHERE " + movieId + " = " + movieId;
    }

    public static String getLatest() {
        return "SELECT TOP 1 * FROM " + TABLE + " ORDER BY " + MOVIE_ID + " DESC";
    }

    public static String getMovielongandsixteen() {
        return "SELECT TOP 1 * FROM " + TABLE + " WHERE " + Age_Indication + " > '16' ORDER BY " + DURATION + " DESC";
    }

    public HashMap<Integer, HashMap<String, String>> getAllMoviesHashmap() {
        HashMap<Integer, HashMap<String, String>> hashMap = new HashMap<>();

        try {
            resultSet = DatabaseConnection.getStatementResult(getMovies());

            while (resultSet.next()) {

                int MovieID = resultSet.getInt(MOVIE_ID);
                HashMap<String, String> data = new HashMap<>();

                data.put(TITLE, resultSet.getString(TITLE));
                data.put(DURATION, resultSet.getString(DURATION));
                data.put(GENRE, resultSet.getString(GENRE));
                data.put(LANGUAGE, resultSet.getString(LANGUAGE));
                data.put(Age_Indication, resultSet.getString(Age_Indication));

                hashMap.put(MovieID, data);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }

        return hashMap;
    }

    public Movie getMovieWithLongestDurationAboveSixteen() {
        Movie movie = new Movie(
                0,
                "",
                "",
                null,
                null,
                0
        );

        try {
            resultSet = DatabaseConnection.getStatementResult(getMovielongandsixteen());

            while (resultSet.next()) {
                Genre movieGenre = Genre.ACTION;
                Language movieLanguage = Language.ENGLISH;

                for (Genre genre : Genre.values()) {
                    if (genre.toString().equals(resultSet.getString(GENRE))) {
                        movieGenre = genre;
                    }
                }

                for (Language language : Language.values()) {
                    if (language.toString().equals(resultSet.getString(LANGUAGE))) {
                        movieLanguage = language;
                    }
                }

                movie = new Movie(
                        resultSet.getInt(MOVIE_ID),
                        resultSet.getString(TITLE),
                        resultSet.getString(DURATION),
                        movieGenre,
                        movieLanguage,
                        resultSet.getInt(Age_Indication)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Movie(
                    0,
                    "",
                    "",
                    null,
                    null,
                    0
            );
        }

        return movie;

    }



    public boolean updateMovie(String query) {
        boolean isSaved = false;

        try {
            DatabaseConnection.executeSQLCreateStatement(query);
            isSaved = true;
        } catch (Exception ignored) {
        }
        return isSaved;
    }
}
