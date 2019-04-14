package ApplicationLayer;

import DataStorageLayer.DAOMovie;
import DomainLayer.Movie;

import javax.swing.*;
import java.awt.*;

public class CustomMovieActivity extends Activity {

    // Initializing GUI components with text in the Labels
    private JLabel jLabelTitle = new JLabel("Movie with the longest duration under Sixteen:");
    private JLabel jLabelMovieID = new JLabel("Movie ID:");
    private JLabel jLabelMovieTitle = new JLabel("Movie Title:");
    private JLabel jLabelDuration = new JLabel("Duration:");
    private JLabel jLabelGenre = new JLabel("Genre:");
    private JLabel jLabelLanguage = new JLabel("Language:");
    private JLabel jLabelAgeIndication = new JLabel("Age Indication:");
    private JTextField jTextFieldMovieID = new JTextField();
    private JTextField jTextFieldMovieTitle = new JTextField();
    private JTextField jTextFieldDuration = new JTextField();
    private JTextField jTextFieldGenre = new JTextField();
    private JTextField jTextFieldLanguage = new JTextField();
    private JTextField jTextFieldAgeIndication = new JTextField();

    // Constructor
    public CustomMovieActivity(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(7, 2));
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding components to the GUI
    @Override
    protected void addPageGUI() {

        jPanelContent.add(jLabelTitle);
        jPanelContent.add(new JLabel());

        jPanelContent.add(jLabelMovieID);
        jPanelContent.add(jTextFieldMovieID);

        jPanelContent.add(jLabelMovieTitle);
        jPanelContent.add(jTextFieldMovieTitle);

        jPanelContent.add(jLabelDuration);
        jPanelContent.add(jTextFieldDuration);

        jPanelContent.add(jLabelGenre);
        jPanelContent.add(jTextFieldGenre);

        jPanelContent.add(jLabelLanguage);
        jPanelContent.add(jTextFieldLanguage);

        jPanelContent.add(jLabelAgeIndication);
        jPanelContent.add(jTextFieldAgeIndication);

    }

    // Adding Data to the GUI
    @Override
    public void addDataToPageGUI() {

        DAOMovie daoMovie = new DAOMovie();

        Movie movie = daoMovie.getMovieWithLongestDurationAboveSixteen();

        jTextFieldMovieID.setText(String.valueOf(movie.getItemId()));
        jTextFieldMovieTitle.setText(movie.getTitle());
        jTextFieldDuration.setText(movie.getDuration());
        jTextFieldGenre.setText(movie.getGenreString());
        jTextFieldLanguage.setText(movie.getLanguageString());
        jTextFieldAgeIndication.setText(String.valueOf(movie.getAgeChecker()));

    }

    // Loading Data
    @Override
    protected void loadData() {

    }
}
