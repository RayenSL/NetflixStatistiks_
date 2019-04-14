package PresentationLayer.ActionListener;

import ApplicationLayer.Activity;
import DataStorageLayer.DAOProfile;
import DomainLayer.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProfileInsertButtonListener extends ActionListenerStandard{

    private JComboBox<Integer> jComboBoxAccountID;
    private JTextField jTextFieldProfileName;
    private JTextField jTextFieldProfileDateOfBirth;

    public ProfileInsertButtonListener(Activity activity,
                                       JComboBox<Integer> jComboBoxAccountID,
                                       JTextField jTextFieldProfileName,
                                       JTextField jTextFieldProfileDateOfBirth,
                                       JLabel jLabelResult) {
        super(activity, jLabelResult);
        this.jComboBoxAccountID = jComboBoxAccountID;
        this.jTextFieldProfileName = jTextFieldProfileName;
        this.jTextFieldProfileDateOfBirth = jTextFieldProfileDateOfBirth;

    }

    // This method gets called once the save button is clicked and it pushes all the data to the page.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Instantiate DBManager object
        DAOProfile daoProfile = new DAOProfile();
        // Check whether a required field is not filled in
        if (jComboBoxAccountID.getItemCount() > 0) {

            if (jTextFieldProfileName.getText().isEmpty() ||
                    jTextFieldProfileDateOfBirth.getText().isEmpty()) {
                jLabelResult.setText("fill in the fields!");

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

            // Creates the INSERT query for given values
            String query = daoProfile.insertProfile(
                    Integer.parseInt(jComboBoxAccountID.getSelectedItem().toString()),
                    jTextFieldProfileName.getText(),
                    jTextFieldProfileDateOfBirth.getText()
            );

            // Runs the query and returns boolean result
            boolean result = daoProfile.updateProfile(query);

            // Check if query was inserted succesfully
            if (result) {

                // get ProfileID from inserted Profile
                int ProfileID = daoProfile.getLatestProfileID();

                // Initialize Profile object with filled in data
                Profile createdProfile = new Profile(
                        Integer.parseInt(jComboBoxAccountID.getSelectedItem().toString()),
                        ProfileID,
                        jTextFieldProfileName.getText(),
                        jTextFieldProfileDateOfBirth.getText()
                );

                // Add initialized Profile object to HashMap
                insertProfileToHashMap(createdProfile);
            }

            showResult(result);
        }

    }

    // Inserts a Profile object to HashMap
    private void insertProfileToHashMap(Profile createdProfile) {
        activity.getProfileHashMap().put(createdProfile.getProfileId(), createdProfile);
        activity.addDataToPageGUI();
    }

}
