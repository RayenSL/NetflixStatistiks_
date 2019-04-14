package ApplicationLayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class AccountProfileActivity extends Activity {

    // Initializing attributes from its superclass
    private Activity accountActivity;
    private Activity profileActivity;

    // Initializing GUI components
    private JComboBox<String> jComboBoxAccountNames = new JComboBox<>();
    private JLabel jLabelTitle = new JLabel("Accounts with 1 profile:");

    // Constructor
    public AccountProfileActivity(Activity accountActivity, Activity profileActivity, JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(1, 2));
        this.accountActivity = accountActivity;
        this.profileActivity = profileActivity;
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding the Listeners to GUI
    @Override
    protected void addPageGUI() {

        jPanelContent.add(jLabelTitle);
        jPanelContent.add(jComboBoxAccountNames);

    }
    // Adding data to the GUI
    @Override
    public void addDataToPageGUI() {

        ArrayList<Integer> arrayListProfileID = new ArrayList<>();

        for (int profileID : profileActivity.getProfileHashMap().keySet()) {
            arrayListProfileID.add(profileActivity.getProfileHashMap().get(profileID).getItemId());
        }
        for (int x : arrayListProfileID) {
            int occurrences = Collections.frequency(arrayListProfileID, x);

            if (occurrences == 1) jComboBoxAccountNames.addItem(accountActivity.getAccountHashMap().get(x).getName());

        }

    }

    // Loading the data
    @Override
    protected void loadData() {

    }
}
