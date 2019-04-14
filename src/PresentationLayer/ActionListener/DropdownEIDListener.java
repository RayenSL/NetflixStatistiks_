package PresentationLayer.ActionListener;

import DomainLayer.SerieEpisode;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropdownEIDListener extends ActionListenerStandard {
    private JComboBox<Integer> jComboBoxEpisodeID;
    private JTextField jTextFieldTitle;
    private JTextField jTextFieldDuration;

    public DropdownEIDListener(Activity activity, JComboBox<Integer> comboBox, JTextField Title, JTextField jTextFieldDuration) {

        super(activity, new JLabel());
        jComboBoxEpisodeID = comboBox;
        jTextFieldTitle = Title;
        this.jTextFieldDuration = jTextFieldDuration;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (jComboBoxEpisodeID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxEpisodeID.getSelectedItem();

            SerieEpisode episode = activity.getSerieEpisodeHashMap().get(selectedId);

            jTextFieldTitle.setText(episode.getTitle());
            jTextFieldDuration.setText(episode.getDuration());

        }

    }
}
