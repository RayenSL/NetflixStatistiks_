package PresentationLayer.ActionListener;

import DataStorageLayer.DAOProfile;
import DomainLayer.Profile;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProfileUpdateButtonListener extends ActionListenerStandard {

    private JComboBox<Integer> jComboBoxAccountID;
    private JComboBox<Integer> JComboBoxProfileID;

    private JTextField jTextFieldProfileName;
    private JTextField jTextFieldProfileDateOfBirth;

    public ProfileUpdateButtonListener(Activity activity, JComboBox<Integer> comboBoxAccountID, JComboBox<Integer> jComboBoxProfileID,
                                       JTextField profileName, JTextField profileDateOfBirth, JLabel Result) {

        super(activity, Result);

        this.jComboBoxAccountID = comboBoxAccountID;
        this.JComboBoxProfileID = jComboBoxProfileID;
        this.jTextFieldProfileName = profileName;
        this.jTextFieldProfileDateOfBirth = profileDateOfBirth;

    }

    // This method gets called once the update button is clicked and it pushes all the data to the page.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Checks if the AccountID and ProfileID exist in the list.
        if (jComboBoxAccountID.getItemCount() > 0 && JComboBoxProfileID.getItemCount() > 0) {

            // Check whether a required field is not filled in.
            if (jTextFieldProfileName.getText().isEmpty() ||
                    jTextFieldProfileDateOfBirth.getText().isEmpty()) {
                jLabelResult.setText("Please fill in all fields!");

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                jLabelResult.setText("");
                            }
                        },
                        3000
                );
                return;
            }

            int accountID = (int) jComboBoxAccountID.getSelectedItem();
            int profileID = (int) JComboBoxProfileID.getSelectedItem();

            // Instantiate DBManager object
            DAOProfile daoProfile = new DAOProfile();

            // Creates the UPDATE query for given values
            String query = daoProfile.updateProfile(
                    accountID,
                    profileID,
                    jTextFieldProfileName.getText(),
                    jTextFieldProfileDateOfBirth.getText());

            // Initialize Profile object with filled in data
            Profile updatedProfile = new Profile(accountID,
                    profileID,
                    jTextFieldProfileName.getText(),
                    jTextFieldProfileDateOfBirth.getText()
            );

            // Runs the query and returns boolean result
            boolean result = daoProfile.updateProfile(query);

            // Check if query was inserted succesfully
            if (result) {
                updateAccountToHashMap(updatedProfile);
            }

            showResult(result);
        }
    }

    // Inserts updated Profile object to HashMap
    private void updateAccountToHashMap(Profile updatedProfile) {
        activity.getProfileHashMap().put(updatedProfile.getProfileId(), updatedProfile);
    }
}
