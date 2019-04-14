package ApplicationLayer;

import DataStorageLayer.DAOMovie;
import DomainLayer.Genre;
import DomainLayer.Language;
import DomainLayer.Movie;
import PresentationLayer.ActionListener.DropdownMIDListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MovieActivity extends Activity {

    // Adding Components to the GUI
    private JComboBox<Integer> jComboBox = new JComboBox<>();
    private JLabel jLabelMovieID = new JLabel("Select Movie ID:");
    private JLabel jLabelTitle = new JLabel("Title:");
    private JLabel jLabelDuration = new JLabel("Duration:");
    private JLabel jLabelGenre = new JLabel("Genre:");
    private JLabel jLabelLanguage = new JLabel("Language:");
    private JLabel jLabelAgeIndication = new JLabel("Age Indication:");
    private JTextField jTextFieldTitle = new JTextField();
    private JTextField jTextFieldDuration = new JTextField();
    private JTextField jTextFieldGenre = new JTextField();
    private JTextField jTextFieldLanguage = new JTextField();
    private JTextField jTextFieldAgeIndication = new JTextField();

    // Constructor
    public MovieActivity(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(6, 2));
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding Listeners to the GUI
    @Override
    protected void addPageGUI() {

        jComboBox.addActionListener(new DropdownMIDListener(
                this,
                jComboBox,
                jTextFieldTitle,
                jTextFieldDuration,
                jTextFieldGenre,
                jTextFieldLanguage,
                jTextFieldAgeIndication
        ));

        jPanelContent.add(jLabelMovieID);
        jPanelContent.add(jComboBox);

        jPanelContent.add(jLabelTitle);
        jPanelContent.add(jTextFieldTitle);

        jPanelContent.add(jLabelDuration);
        jPanelContent.add(jTextFieldDuration);

        jPanelContent.add(jLabelGenre);
        jPanelContent.add(jTextFieldGenre);

        jPanelContent.add(jLabelLanguage);
        jPanelContent.add(jTextFieldLanguage);

        jPanelContent.add(jLabelAgeIndication);
        jPanelContent.add(jTextFieldAgeIndication);

    }

    @Override
    public void addDataToPageGUI() {

        jComboBox.removeAllItems();
        String emptyString = "";

        String title = emptyString;
        String duration = emptyString;
        String genre = emptyString;
        String language = emptyString;
        String ageIndication = emptyString;

        if (!movieHashMap.isEmpty()) {
            for (Integer movieID : getMovieHashMap().keySet()) {
                jComboBox.addItem(movieID);
            }

            int movieID = movieHashMap.keySet().stream().findFirst().get();
            title = movieHashMap.get(movieID).getTitle();
            duration = movieHashMap.get(movieID).getDuration();
            genre = movieHashMap.get(movieID).getGenreString();
            language = movieHashMap.get(movieID).getLanguageString();
            ageIndication = String.valueOf(movieHashMap.get(movieID).getAgeChecker());
        }

        jTextFieldTitle.setText(title);
        jTextFieldDuration.setText(String.valueOf(duration));
        jTextFieldGenre.setText(genre);
        jTextFieldLanguage.setText(language);
        jTextFieldAgeIndication.setText(ageIndication);

    }

    @Override
    protected void loadData() {

        DAOMovie daoMovie = new DAOMovie();

        HashMap<Integer, HashMap<String, String>> allMovies = daoMovie.getAllMoviesHashmap();

        Genre movieGenre = null;
        Language movieLanguage = null;

        for (Integer key : allMovies.keySet()) {

            for (Genre genre : Genre.values()) {
                if (genre.toString().equals(allMovies.get(key).get(DAOMovie.GENRE))) {
                    movieGenre = genre;
                }
            }

            for (Language language : Language.values()) {
                if (language.toString().equals(allMovies.get(key).get(DAOMovie.LANGUAGE))) {
                    movieLanguage = language;
                }
            }

            Movie movie = new Movie(
                    key,
                    allMovies.get(key).get(DAOMovie.TITLE),
                    allMovies.get(key).get(DAOMovie.DURATION),
                    movieGenre,
                    movieLanguage,
                    Integer.parseInt(allMovies.get(key).get(DAOMovie.Age_Indication))
            );
            movieHashMap.put(key, movie);
        }

    }
}
