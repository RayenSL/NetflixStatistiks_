package ApplicationLayer;

import PresentationLayer.ActionListener.AccountInsertButtonListener;

import javax.swing.*;
import java.awt.*;

public class AccountCreateActivity extends Activity{

    // Initializing its Superclass Attribute
    private Activity activityinformation;

    // Initializing GUI components
    private JLabel jLabelName = new JLabel("Name:");
    private JLabel jLabelStreetname = new JLabel("Streetname:");
    private JLabel jLabelHousenumber = new JLabel("House number:");
    private JLabel jLabelHNAddition = new JLabel("House number addition:");
    private JLabel jLabelPostalcode = new JLabel("Postalcode:");
    private JLabel jLabelCity = new JLabel("City:");
    private JLabel jLabelResult = new JLabel("");

    private JTextField jTextFieldName = new JTextField();
    private JTextField jTextFieldStreetname = new JTextField();
    private JTextField jTextFieldHousenumber = new JTextField();
    private JTextField jTextFieldHNAddition = new JTextField();
    private JTextField jTextFieldPostalcode = new JTextField();
    private JTextField jTextFieldCity = new JTextField();

    private JButton jButtonSave = new JButton("Save");

    // Constructor
    public AccountCreateActivity(Activity activityinformation, JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(7, 2));
        this.activityinformation = activityinformation;
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding the Listeners to the GUI
    @Override
    protected void addPageGUI() {

        jButtonSave.addActionListener(new AccountInsertButtonListener(
                activityinformation,
                jTextFieldName,
                jTextFieldStreetname,
                jTextFieldHousenumber,
                jTextFieldHNAddition,
                jTextFieldPostalcode,
                jTextFieldCity,
                jLabelResult)
        );

        jPanelContent.add(jLabelName);
        jPanelContent.add(jTextFieldName);

        jPanelContent.add(jLabelStreetname);
        jPanelContent.add(jTextFieldStreetname);

        jPanelContent.add(jLabelHousenumber);
        jPanelContent.add(jTextFieldHousenumber);

        jPanelContent.add(jLabelHNAddition);
        jPanelContent.add(jTextFieldHNAddition);

        jPanelContent.add(jLabelPostalcode);
        jPanelContent.add(jTextFieldPostalcode);

        jPanelContent.add(jLabelCity);
        jPanelContent.add(jTextFieldCity);

        jPanelContent.add(jLabelResult);
        jPanelContent.add(jButtonSave);

    }

    // Adding Data to the GUI
    @Override
    public void addDataToPageGUI() {

    }

    // Loading the data
    @Override
    protected void loadData() {

    }
}
