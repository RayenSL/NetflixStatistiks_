package PresentationLayer.ActionListener;

import DataStorageLayer.DAOProfile;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProfileDeleteButtonListener extends ActionListenerStandard{

    private JComboBox<Integer> jComboBoxAccountID;
    private JComboBox<Integer> jComboBoxProfileID;

    public ProfileDeleteButtonListener(Activity activity, JComboBox<Integer> jComboBoxAccountID,
                                       JComboBox<Integer> jComboBoxProfileID, JLabel Result) {
        super(activity, Result);
        this.jComboBoxAccountID = jComboBoxAccountID;
        this.jComboBoxProfileID = jComboBoxProfileID;
    }

    // This method gets called once the delete button is clicked and starts the deletion process.
    @Override
    public void actionPerformed(ActionEvent e) {

        // This checks if the Account has one profile
        if (jComboBoxProfileID.getItemCount() < 2) {
            // Displays the error that at least one profile is required.
            jLabelResult.setText("Could not delete profile, need more then 1 profile to delete!");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            jLabelResult.setText("");
                        }
                    },
                    3000
            );
        }

        // This checks if an Account and Profile ID exists.
        if (jComboBoxAccountID.getItemCount() > 0 && jComboBoxProfileID.getItemCount() > 1) {
            // Typecasting the ID's to Integers
            int accountID = (int) jComboBoxAccountID.getSelectedItem();
            int profileID = (int) jComboBoxProfileID.getSelectedItem();

            DAOProfile daoProfile = new DAOProfile();

            String query = daoProfile.deleteProfile(
                    accountID,
                    profileID);

            boolean result = daoProfile.updateProfile(query);

            if (result) {
                removeProfileFromHashMap(profileID);
            }

            showResult(result, profileID);
        }
    }

    // This is used to show the result and also remove the ProfileID from the ComboBox (If successful).
    private void showResult(boolean result, int itemId) {
        if (result) {
            jLabelResult.setText("Deleted succesfully!");

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            jLabelResult.setText("");
                        }
                    },
                    2000
            );

            jComboBoxProfileID.removeItem(itemId);

        } else {
            jLabelResult.setText("Couldn't delete!");

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            jLabelResult.setText("");
                        }
                    },
                    2000
            );

        }

    }

    // This removes ProfileID from the list on the AccountPage.
    private void removeProfileFromHashMap(int ProfileID) {
        activity.getProfileHashMap().remove(ProfileID);
    }

}
