package PresentationLayer.ActionListener;

import DataStorageLayer.DAOAccount;
import DomainLayer.Account;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AccountUpdateButtonListener extends ActionListenerStandard {
    private JComboBox<Integer> jComboBoxAccountID;
    private JTextField jTextFieldName;
    private JTextField jTextFieldStreetname;
    private JTextField jTextFieldHousenumber;
    private JTextField jTextFieldHNAddition;
    private JTextField jTextFieldPostalcode;
    private JTextField jTextFieldCity;

    public AccountUpdateButtonListener(Activity activity, JComboBox<Integer> comboBox,
                                       JTextField Name, JTextField Streetname, JTextField HouseNumber,
                                       JTextField HNAddition, JTextField PostalCode, JTextField City, JLabel Result) {

        super(activity, Result);

        jComboBoxAccountID = comboBox;
        jTextFieldName = Name;
        jTextFieldStreetname = Streetname;
        jTextFieldHousenumber = HouseNumber;
        jTextFieldHNAddition = HNAddition;
        jTextFieldPostalcode = PostalCode;
        jTextFieldCity = City;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (jComboBoxAccountID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxAccountID.getSelectedItem();

            if (jTextFieldName.getText().isEmpty() ||
                    jTextFieldStreetname.getText().isEmpty() ||
                    jTextFieldHousenumber.getText().isEmpty() ||
                    jTextFieldPostalcode.getText().isEmpty() ||
                    jTextFieldCity.getText().isEmpty()) {
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

            // Check to see if houseNumber is valid Integer
            try {
                int houseNumber = Integer.parseInt(jTextFieldHousenumber.getText());
            } catch (NumberFormatException ignored) {
                jLabelResult.setText("Invalid housenumber!");
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

            DAOAccount daoAccount = new DAOAccount();

            // Creates the UPDATE query for given values
            String query = DAOAccount.updateAccount(selectedId,
                    jTextFieldName.getText(),
                    jTextFieldStreetname.getText(),
                    jTextFieldHousenumber.getText(),
                    jTextFieldHNAddition.getText(),
                    jTextFieldPostalcode.getText(),
                    jTextFieldCity.getText());

            // Initialize Account object with filled in data
            Account updatedAccount = new Account(selectedId,
                    jTextFieldName.getText(),
                    jTextFieldStreetname.getText(),
                    (jTextFieldHousenumber.getText()),
                    jTextFieldHNAddition.getText(),
                    jTextFieldPostalcode.getText(),
                    jTextFieldCity.getText());

            // Runs the query and returns boolean result
            boolean result = daoAccount.updateAccount(query);

            // Check if query was inserted succesfully
            if (result) {
                updateAccountToHashMap(updatedAccount);
            }

            showResult(result);
        }
    }

    // Inserts updated Account object to HashMap
    private void updateAccountToHashMap(Account updatedAccount) {
        activity.getAccountHashMap().put(updatedAccount.getAccountId(), updatedAccount);
    }
}
