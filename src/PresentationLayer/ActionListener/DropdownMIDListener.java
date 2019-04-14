package PresentationLayer.ActionListener;

import DomainLayer.Movie;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropdownMIDListener extends ActionListenerStandard {
    private JComboBox<Integer> jComboBoxMovieID;
    private JTextField jTextFieldTitle;
    private JTextField jTextFieldDuration;
    private JTextField jTextFieldGenre;
    private JTextField jTextFieldLanguage;
    private JTextField jTextFieldAgeIndication;

    public DropdownMIDListener(Activity activity, JComboBox<Integer> comboBox,
                               JTextField Title, JTextField Duration, JTextField Genre,
                               JTextField Language, JTextField AgeIndication) {

        super(activity, new JLabel());
        jComboBoxMovieID = comboBox;
        jTextFieldTitle = Title;
        jTextFieldDuration = Duration;
        jTextFieldGenre = Genre;
        jTextFieldLanguage = Language;
        jTextFieldAgeIndication = AgeIndication;


    }

    // This method gets called once the dropdown is clicked and it pushes all the data to the page.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Check if the dropdown contains any MovieID's
        if (jComboBoxMovieID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxMovieID.getSelectedItem();

            // Retrieve selected Movie from the list.
            Movie movie = activity.getMovieHashMap().get(selectedId);

            jTextFieldTitle.setText(movie.getTitle());
            jTextFieldDuration.setText(movie.getDuration());
            jTextFieldLanguage.setText(movie.getLanguageString());
            jTextFieldGenre.setText(movie.getGenreString());
            jTextFieldAgeIndication.setText(String.valueOf(movie.getAgeChecker()));

        }

    }
}
