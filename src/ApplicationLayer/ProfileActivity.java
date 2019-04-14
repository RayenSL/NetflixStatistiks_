package ApplicationLayer;

import DataStorageLayer.DAOProfile;
import DomainLayer.Profile;
import PresentationLayer.ActionListener.DropdownPID2Listener;
import PresentationLayer.ActionListener.DropdownPIDListener;
import PresentationLayer.ActionListener.ProfileDeleteButtonListener;
import PresentationLayer.ActionListener.ProfileUpdateButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ProfileActivity extends Activity {

    // Getting al methods of its superclass
    private Activity activityinformation;

    // Instantiates all the elements for the page
    private JComboBox<Integer> jComboBoxAccount = new JComboBox<>();
    private JLabel jLabelAccountID = new JLabel("Select Account ID:");
    private JComboBox<Integer> jComboBoxProfile = new JComboBox<>();
    private JLabel jLabelProfileID = new JLabel("Select Profile ID:");
    private JLabel jLabelProfileName = new JLabel("Profile Name:");
    private JLabel jLabelProfileAge = new JLabel("Profile Age:");
    private JLabel jLabelResult = new JLabel("");
    private JTextField jTextFieldProfileName = new JTextField();
    private JTextField jTextFieldProfileAge = new JTextField();
    private JButton jButtonUpdate = new JButton("Update");
    private JButton jButtonDelete = new JButton("Delete");

    // Constructor
    public ProfileActivity(Activity activity, JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(6, 2));
        this.activityinformation = activity;
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding Listeners to the GUI
    @Override
    protected void addPageGUI() {

        jComboBoxAccount.addActionListener(new DropdownPIDListener(this));
        jComboBoxProfile.addActionListener(new DropdownPID2Listener(this, jComboBoxProfile, jTextFieldProfileName, jTextFieldProfileAge));

        jButtonUpdate.addActionListener(new ProfileUpdateButtonListener(this, jComboBoxAccount, jComboBoxProfile, jTextFieldProfileName, jTextFieldProfileAge, jLabelResult));
        jButtonDelete.addActionListener(new ProfileDeleteButtonListener(this, jComboBoxAccount, jComboBoxProfile, jLabelResult));


        jPanelContent.add(jLabelAccountID);
        jPanelContent.add(jComboBoxAccount);

        jPanelContent.add(jLabelProfileID);
        jPanelContent.add(jComboBoxProfile);

        jPanelContent.add(jLabelProfileName);
        jPanelContent.add(jTextFieldProfileName);

        jPanelContent.add(jLabelProfileAge);
        jPanelContent.add(jTextFieldProfileAge);

        jPanelContent.add(jLabelResult);
        jPanelContent.add(jButtonUpdate);

        jPanelContent.add(new JLabel());
        jPanelContent.add(jButtonDelete);

    }

    @Override
    public void addDataToPageGUI() {

        int startAccountID = 0;

        if (jComboBoxAccount.getItemCount() > 0) {
            startAccountID = Integer.parseInt(jComboBoxAccount.getSelectedItem().toString());

        }

        jComboBoxAccount.removeAllItems();
        jComboBoxProfile.removeAllItems();

        String emptyString = "";

        String profileName = emptyString;
        String profileAge = emptyString;

        if (!activityinformation.accountHashMap.isEmpty()) {
            for (Integer accountID : activityinformation.getAccountHashMap().keySet()) {

                jComboBoxAccount.addItem(accountID);
            }

            int accountID = activityinformation.accountHashMap.keySet().stream().findFirst().get();

            if (startAccountID != 0) {
                accountID = startAccountID;
                jComboBoxAccount.setSelectedItem(accountID);
            }

            for (Integer profileID : getProfileHashMap().keySet()) {

                if (startAccountID == 0) {
                    if (Integer.parseInt(jComboBoxAccount.getSelectedItem().toString()) == profileHashMap.get(profileID).getItemId()) {
                        jComboBoxProfile.addItem(profileHashMap.get(profileID).getProfileId());
                    }
                }

                if (profileHashMap.get(profileID).getItemId() == startAccountID) {

                    jComboBoxProfile.removeItem(profileID);
                    jComboBoxProfile.addItem(profileHashMap.get(profileID).getProfileId());

                }

            }

            if (!profileHashMap.isEmpty()) {

                if (jComboBoxProfile.getItemCount() > 0) {
                    // Refresh the selected Profile
                    int profileID = jComboBoxProfile.getItemAt(jComboBoxProfile.getSelectedIndex());

                    profileName = profileHashMap.get(profileID).getProfileName();
                    profileAge = profileHashMap.get(profileID).getdOB();
                }

            }
        }

        jTextFieldProfileName.setText(profileName);
        jTextFieldProfileAge.setText(profileAge);

    }

    // Loading data
    @Override
    protected void loadData() {

        DAOProfile daoProfile = new DAOProfile();

        HashMap<Integer, HashMap<String, String>> allProfiles = daoProfile.getAllProfilesInHashmap();

        for (Integer accountID : activityinformation.accountHashMap.keySet()) {

            for (Integer profileID : allProfiles.keySet()) {

                if (accountID.equals(Integer.parseInt(allProfiles.get(profileID).get(DAOProfile.ACCOUNT_ID)))) {
                    Profile profile = new Profile(
                            accountID,
                            profileID,
                            allProfiles.get(profileID).get(DAOProfile.NAME),
                            allProfiles.get(profileID).get(DAOProfile.DATE_OF_BIRTH)
                    );
                    profileHashMap.put(profileID, profile);
                }
            }
        }
    }
}
