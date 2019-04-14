package ApplicationLayer;

import DataStorageLayer.DAOAccount;
import DomainLayer.Account;
import PresentationLayer.ActionListener.AccountDeleteButtonListener;
import PresentationLayer.ActionListener.AccountUpdateButtonListener;
import PresentationLayer.ActionListener.DropdownAIDListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AccountActivity extends Activity{
    // Initializing the components for the Account Page
    private JComboBox<Integer> jComboBox = new JComboBox<>();

    private JLabel jLabelAccountID = new JLabel("Select Account ID:");
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

    private JButton jButtonUpdate = new JButton("Update");
    private JButton jButtonDelete = new JButton("Delete");

    // Constructor for the Activity
    public AccountActivity(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(9, 2));
        loadData();
        addDataToPageGUI();
        addPageGUI();
    }

    // Adding the Listeners to the Activity
    @Override
    protected void addPageGUI() {

        jComboBox.addActionListener(new DropdownAIDListener(
                this,
                jComboBox,
                jTextFieldName,
                jTextFieldStreetname,
                jTextFieldHousenumber,
                jTextFieldHNAddition,
                jTextFieldPostalcode,
                jTextFieldCity)
        );

        jButtonUpdate.addActionListener(new AccountUpdateButtonListener(
                this,
                jComboBox,
                jTextFieldName,
                jTextFieldStreetname,
                jTextFieldHousenumber,
                jTextFieldHNAddition,
                jTextFieldPostalcode,
                jTextFieldCity,
                jLabelResult)
        );

        jButtonDelete.addActionListener(new AccountDeleteButtonListener(
                this,
                jComboBox,
                jLabelResult)
        );

        jPanelContent.add(jLabelAccountID);
        jPanelContent.add(jComboBox);

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
        jPanelContent.add(jButtonUpdate);

        jPanelContent.add(new JLabel());
        jPanelContent.add(jButtonDelete);

    }

    // Adding the data to the GUI
    @Override
    public void addDataToPageGUI() {

        jComboBox.removeAllItems();
        String name = "";
        String streetname = "";
        String housenumber = "";
        String hnaddition = "";
        String postalcode = "";
        String city = "";

        if (!accountHashMap.isEmpty()) {
            for (Integer accountID : getAccountHashMap().keySet()) {
                jComboBox.addItem(accountID);
            }

            int AccountID = accountHashMap.keySet().stream().findFirst().get();
            name = accountHashMap.get(AccountID).getName();
            streetname = accountHashMap.get(AccountID).getStreet();
            housenumber = accountHashMap.get(AccountID).getHousenumber();
            hnaddition = accountHashMap.get(AccountID).getHousenumberadd();
            postalcode = accountHashMap.get(AccountID).getPostalcode();
            city = accountHashMap.get(AccountID).getCity();
        }

        jTextFieldName.setText(name);
        jTextFieldStreetname.setText(streetname);
        jTextFieldHousenumber.setText(String.valueOf(housenumber));
        jTextFieldHNAddition.setText(hnaddition);
        jTextFieldPostalcode.setText(postalcode);
        jTextFieldCity.setText(city);
    }

    // Loading the data in Hashmaps
    @Override
    protected void loadData() {

        DAOAccount daoAccount = new DAOAccount();

        HashMap<Integer, HashMap<String, String>> allAccounts = daoAccount.getTheAccounts();

        for (Integer key : allAccounts.keySet()) {
            Account account = new Account(
                    key,
                    allAccounts.get(key).get(DAOAccount.NAME),
                    allAccounts.get(key).get(DAOAccount.STREETNAME),
                    allAccounts.get(key).get(DAOAccount.HOUSENUMBER),
                    allAccounts.get(key).get(DAOAccount.HOUSENUMBERADDITION),
                    allAccounts.get(key).get(DAOAccount.POSTALCODE),
                    allAccounts.get(key).get(DAOAccount.CITY)
            );
            accountHashMap.put(key, account);
        }

    }
}
