package ApplicationLayer;

import PresentationLayer.ActionListener.ProfileInsertButtonListener;

import javax.swing.*;
import java.awt.*;

public class ProfileCreateActivity extends Activity{

    // Getting methods from its superclass
    private Activity activityinformation;
    private Activity getActivityinformation;

    // Initializing Components
    private JLabel jLabelAccountID = new JLabel("Select Account ID:");
    private JComboBox<Integer> jComboBoxAccountID = new JComboBox<>();
    private JLabel jLabelName = new JLabel("Name:");
    private JLabel jLabelDateOfBirth = new JLabel("Date Of Birth (Year-Month-Day):");
    private JLabel jLabelResult = new JLabel("");
    private JTextField jTextFieldName = new JTextField();
    private JTextField jTextFieldDateOfBirth = new JTextField();
    private JButton jButtonSave = new JButton("Save");

    // Constructor
    public ProfileCreateActivity(Activity activityinformation, Activity getActivityinformation, JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(4, 2));
        this.activityinformation = activityinformation;
        this.getActivityinformation = getActivityinformation;
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding Listeners to the GUI
    @Override
    protected void addPageGUI() {

        jButtonSave.addActionListener(new ProfileInsertButtonListener(
                getActivityinformation,
                jComboBoxAccountID,
                jTextFieldName,
                jTextFieldDateOfBirth,
                jLabelResult)
        );

        jPanelContent.add(jLabelAccountID);
        jPanelContent.add(jComboBoxAccountID);

        jPanelContent.add(jLabelName);
        jPanelContent.add(jTextFieldName);

        jPanelContent.add(jLabelDateOfBirth);
        jPanelContent.add(jTextFieldDateOfBirth);

        jPanelContent.add(jLabelResult);
        jPanelContent.add(jButtonSave);

    }

    @Override
    public void addDataToPageGUI() {

        for (int accountID : activityinformation.accountHashMap.keySet()) {
            jComboBoxAccountID.addItem(accountID);
        }

    }

    @Override
    protected void loadData() {

    }
}
