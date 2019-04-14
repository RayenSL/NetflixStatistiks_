package PresentationLayer.ActionListener;

import DataStorageLayer.DAOAccount;
import DomainLayer.Account;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AccountInsertButtonListener extends  ActionListenerStandard {

    private JTextField jTextFieldName;
    private JTextField jTextFieldStreetname;
    private JTextField jTextFieldHousenumber;
    private JTextField jTextFieldHNAddition;
    private JTextField jTextFieldPostalcode;
    private JTextField jTextFieldCity;

    public AccountInsertButtonListener(Activity activity,
                                       JTextField Name, JTextField Streetname, JTextField HouseNumber,
                                       JTextField HNAddition, JTextField PostalCode, JTextField City, JLabel Result) {
        super(activity, Result);
        jTextFieldName = Name;
        jTextFieldStreetname = Streetname;
        jTextFieldHousenumber = HouseNumber;
        jTextFieldHNAddition = HNAddition;
        jTextFieldPostalcode = PostalCode;
        jTextFieldCity = City;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

        try {
            int houseNumber = Integer.parseInt(jTextFieldHousenumber.getText());
        } catch (NumberFormatException ignored) {
            // Notify user that given houseNumber is not a valid Integer
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

        String query = daoAccount.insertAccount(
                jTextFieldName.getText(),
                jTextFieldStreetname.getText(),
                jTextFieldHousenumber.getText(),
                jTextFieldHNAddition.getText(),
                jTextFieldPostalcode.getText(),
                jTextFieldCity.getText());

        boolean result = daoAccount.updateAccount(query);

        if (result) {

            int AccountID = daoAccount.getLatestAccountId();

            Account createdAccount = new Account(AccountID,
                    jTextFieldName.getText(),
                    jTextFieldStreetname.getText(),
                    jTextFieldHousenumber.getText(),
                    jTextFieldHNAddition.getText(),
                    jTextFieldPostalcode.getText(),
                    jTextFieldCity.getText());

            insertAccountToHashMap(createdAccount);
        }

        showResult(result);

    }

    private void insertAccountToHashMap(Account createdAccount) {
        activity.getAccountHashMap().put(createdAccount.getAccountId(), createdAccount);
        activity.addDataToPageGUI();
    }
}
