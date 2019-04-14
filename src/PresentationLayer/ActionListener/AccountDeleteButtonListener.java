package PresentationLayer.ActionListener;

import DataStorageLayer.DAOAccount;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AccountDeleteButtonListener extends ActionListenerStandard {
    // All Account id's
    private JComboBox<Integer> jComboBoxAccountID;

    public AccountDeleteButtonListener(Activity activity, JComboBox<Integer> comboBox, JLabel Result) {
        super(activity, Result);
        jComboBoxAccountID = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Combobox checker
        if (jComboBoxAccountID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxAccountID.getSelectedItem();

            DAOAccount daoAccount = new DAOAccount();

            boolean result = DAOAccount.deleteAccountExc(selectedId);

            if (result) {
                removeAccountFromHashMap(selectedId);
            }
            showResult(result, selectedId);
        }

    }

    // shows the Result if succesvol.
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

            jComboBoxAccountID.removeItem(itemId);

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

    // This removes AccountID from the list on the AccountPage.
    private void removeAccountFromHashMap(int AccountID) {
        activity.getAccountHashMap().remove(AccountID);
    }

}
