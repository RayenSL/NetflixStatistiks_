package ApplicationLayer;

import DataStorageLayer.DAOEpisode;
import DataStorageLayer.DAOMovie;
import DataStorageLayer.DAOSerie;
import DomainLayer.Genre;
import DomainLayer.Language;
import DomainLayer.Serie;
import DomainLayer.SerieEpisode;
import PresentationLayer.ActionListener.DropdownEIDListener;
import PresentationLayer.ActionListener.DropdownSIDListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SerieActivity extends Activity {

    // Adding Attributes11
    private JComboBox<Integer> jComboBox = new JComboBox<>();
    private JLabel jLabelSerieID = new JLabel("Select Serie ID:");
    private JLabel jLabelTitle = new JLabel("Title:");
    private JLabel jLabelGenre = new JLabel("Genre:");
    private JLabel jLabelLanguage = new JLabel("Language:");
    private JLabel jLabelAgeIndication = new JLabel("Age Indication:");
    private JTextField jTextFieldTitle = new JTextField();
    private JTextField jTextFieldGenre = new JTextField();
    private JTextField jTextFieldLanguage = new JTextField();
    private JTextField jTextFieldAgeIndication = new JTextField();
    private JComboBox<Integer> jComboBoxEpisode = new JComboBox<>();
    private JLabel jLabelEpisodeID = new JLabel("Select Episode ID:");
    private JLabel jLabelEpisodeTitle = new JLabel("Episode Title:");
    private JLabel jLabelEpisodeDuration = new JLabel("Duration:");
    private JTextField jTextFieldEpisodeTitle = new JTextField();
    private JTextField jTextFieldEpisodeDuration = new JTextField();

    // Constructor
    public SerieActivity(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(9, 2));
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding Listeners
    @Override
    protected void addPageGUI() {

        jComboBox.addActionListener(new DropdownSIDListener(
                this,
                jComboBox,
                jTextFieldTitle,
                jTextFieldGenre,
                jTextFieldLanguage,
                jTextFieldAgeIndication
        ));

        jComboBoxEpisode.addActionListener(new DropdownEIDListener(
                this,
                jComboBoxEpisode,
                jTextFieldEpisodeTitle,
                jTextFieldEpisodeDuration
        ));

        jPanelContent.add(jLabelSerieID);
        jPanelContent.add(jComboBox);

        jPanelContent.add(jLabelTitle);
        jPanelContent.add(jTextFieldTitle);

        jPanelContent.add(jLabelGenre);
        jPanelContent.add(jTextFieldGenre);

        jPanelContent.add(jLabelLanguage);
        jPanelContent.add(jTextFieldLanguage);

        jPanelContent.add(jLabelAgeIndication);
        jPanelContent.add(jTextFieldAgeIndication);

        jPanelContent.add(jLabelEpisodeID);
        jPanelContent.add(jComboBoxEpisode);

        jPanelContent.add(jLabelEpisodeTitle);
        jPanelContent.add(jTextFieldEpisodeTitle);

        jPanelContent.add(jLabelEpisodeDuration);
        jPanelContent.add(jTextFieldEpisodeDuration);

    }

    @Override
    public void addDataToPageGUI() {

        int startSerie = 0;

        if (jComboBox.getItemCount() > 0) {
            startSerie = Integer.parseInt(jComboBox.getSelectedItem().toString());
        }

        jComboBox.removeAllItems();
        jComboBoxEpisode.removeAllItems();

        String emptyString = "";

        String title = emptyString;
        String genre = emptyString;
        String language = emptyString;
        String ageIndication = emptyString;

        String episodeTitle = emptyString;
        String episodeDuration = emptyString;

        if (!serieHashMap.isEmpty()) {
            for (Integer serieID : getSerieHashMap().keySet()) {
                jComboBox.addItem(serieID);

            }

            int serieID = serieHashMap.keySet().stream().findFirst().get();

            if (startSerie != 0) {
                serieID = startSerie;
                jComboBox.setSelectedItem(serieID);
            }

            title = serieHashMap.get(serieID).getTitle();
            genre = serieHashMap.get(serieID).getGenreString();
            language = serieHashMap.get(serieID).getLanguageString();
            ageIndication = String.valueOf(serieHashMap.get(serieID).getAgeChecker());

            for (Integer episodeID : getSerieEpisodeHashMap().keySet()) {

                if (startSerie == 0) {
                    if (Integer.parseInt(jComboBox.getSelectedItem().toString()) == serieEpisodeHashMap.get(episodeID).getItemId()) {
                        jComboBoxEpisode.addItem(serieEpisodeHashMap.get(episodeID).getEpisodeId());
                    }
                }

                if (serieEpisodeHashMap.get(episodeID).getItemId() == startSerie) {
                    jComboBoxEpisode.addItem(serieEpisodeHashMap.get(episodeID).getEpisodeId());

                }

            }

            if (!serieEpisodeHashMap.isEmpty()) {

                // Refresh the selected Episode
                int episodeID = jComboBoxEpisode.getItemAt(jComboBoxEpisode.getSelectedIndex());

                episodeTitle = serieEpisodeHashMap.get(episodeID).getTitle();
                episodeDuration = serieEpisodeHashMap.get(episodeID).getDuration();
            }
        }

        jTextFieldTitle.setText(title);
        jTextFieldGenre.setText(genre);
        jTextFieldLanguage.setText(language);
        jTextFieldAgeIndication.setText(ageIndication);

        jTextFieldEpisodeTitle.setText(episodeTitle);
        jTextFieldEpisodeDuration.setText(episodeDuration);

    }

    @Override
    protected void loadData() {

        DAOSerie daoSerie = new DAOSerie();
        DAOEpisode daoEpisode = new DAOEpisode();

        HashMap<Integer, HashMap<String, String>> allSeries = daoSerie.getAllSeriesHashmap();
        HashMap<Integer, HashMap<String, String>> allEpisodes = daoEpisode.hashMapforAllEpisodes();
        Genre serieGenre = null;
        Language serieLanguage = null;

        for (Integer key : allSeries.keySet()) {

            for (Genre genre : Genre.values()) {
                if (genre.toString().equals(allSeries.get(key).get(DAOSerie.GENRE))) {
                    serieGenre = genre;
                }
            }

            for (Language language : Language.values()) {
                if (language.toString().equals(allSeries.get(key).get(DAOSerie.LANGUAGE))) {
                    serieLanguage = language;
                }
            }

            Serie serie = new Serie(
                    key,
                    allSeries.get(key).get(DAOMovie.TITLE),
                    serieGenre,
                    serieLanguage,
                    Integer.parseInt(allSeries.get(key).get(DAOMovie.Age_Indication))
            );
            serieHashMap.put(key, serie);

            for (Integer episodeID : allEpisodes.keySet()) {

                int serieID = Integer.parseInt(allEpisodes.get(episodeID).get(DAOEpisode.SERIE_ID));

                if (key.equals(serieID)) {
                    SerieEpisode episode = new SerieEpisode(
                            key,
                            episodeID,
                            allEpisodes.get(episodeID).get(DAOEpisode.TITLE),
                            allEpisodes.get(episodeID).get(DAOEpisode.DURATION)
                    );
                    serieEpisodeHashMap.put(episodeID, episode);

                }
            }

        }

    }
}
