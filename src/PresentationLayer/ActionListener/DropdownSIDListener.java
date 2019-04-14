package PresentationLayer.ActionListener;

import DomainLayer.Serie;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropdownSIDListener extends ActionListenerStandard {

    private JComboBox<Integer> jComboBoxSerieID;
    private JTextField jTextFieldTitle;
    private JTextField jTextFieldGenre;
    private JTextField jTextFieldLanguage;
    private JTextField jTextFieldAgeIndication;

    public DropdownSIDListener(Activity activity, JComboBox<Integer> comboBox, JTextField Title, JTextField Genre,
                               JTextField Language, JTextField AgeIndication) {

        super(activity, new JLabel());
        jComboBoxSerieID = comboBox;
        jTextFieldTitle = Title;
        jTextFieldGenre = Genre;
        jTextFieldLanguage = Language;
        jTextFieldAgeIndication = AgeIndication;

    }

    // This method gets called once the dropdown is clicked and it pushes all the data to the page.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Check if the dropdown contains any SerieID's
        if (jComboBoxSerieID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxSerieID.getSelectedItem();

            // Retrieve selected Serie from the list.
            Serie serie = activity.getSerieHashMap().get(selectedId);

            jTextFieldTitle.setText(serie.getTitle());
            jTextFieldLanguage.setText(serie.getLanguageString());
            jTextFieldGenre.setText(serie.getGenreString());
            jTextFieldAgeIndication.setText(String.valueOf(serie.getAgeChecker()));
        }

        activity.addDataToPageGUI();

    }
}
