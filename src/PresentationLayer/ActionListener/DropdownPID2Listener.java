package PresentationLayer.ActionListener;

import DomainLayer.Profile;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropdownPID2Listener extends ActionListenerStandard {

    private JComboBox<Integer> jComboBoxProfileID;
    private JTextField jTextFieldProfileName;
    private JTextField jTextFieldProfileDateOfBirth;

    public DropdownPID2Listener(Activity activity, JComboBox<Integer> comboBox, JTextField jTextFieldProfileName,
                                JTextField jTextFieldProfileDateOfBirth) {

        super(activity, new JLabel());
        jComboBoxProfileID = comboBox;
        this.jTextFieldProfileName = jTextFieldProfileName;
        this.jTextFieldProfileDateOfBirth = jTextFieldProfileDateOfBirth;

    }

    // This method gets called once the dropdown is clicked and it pushes all the data to the page.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Check if the dropdown contains any ProfileID's
        if (jComboBoxProfileID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxProfileID.getSelectedItem();

            // Retrieve selected Profile from the list.
            Profile profile = activity.getProfileHashMap().get(selectedId);

            jTextFieldProfileName.setText(profile.getProfileName());
            jTextFieldProfileDateOfBirth.setText(profile.getdOB());

        }

    }
}
