package PresentationLayer.ActionListener;

import DomainLayer.Account;
import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropdownAIDListener extends ActionListenerStandard{

    private JComboBox<Integer> jComboBoxAccountID;

    private JTextField jTextFieldName;
    private JTextField jTextFieldStreetname;
    private JTextField jTextFieldHousenumber;
    private JTextField jTextFieldHNAddition;
    private JTextField jTextFieldPostalcode;
    private JTextField jTextFieldCity;

    public DropdownAIDListener(Activity activity, JComboBox<Integer> comboBox,
                               JTextField Name, JTextField Streetname, JTextField HouseNumber,
                               JTextField HNAddition, JTextField PostalCode, JTextField City) {

        super(activity, new JLabel());
        jComboBoxAccountID = comboBox;
        jTextFieldName = Name;
        jTextFieldStreetname = Streetname;
        jTextFieldHousenumber = HouseNumber;
        jTextFieldHNAddition = HNAddition;
        jTextFieldPostalcode = PostalCode;
        jTextFieldCity = City;

    }

    // This method gets called once the dropdown is clicked and it pushes all the data to the page.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Check if the dropdown contains any AccountID's
        if (jComboBoxAccountID.getItemCount() > 0) {
            int selectedId = (int) jComboBoxAccountID.getSelectedItem();

            // Retrieve selected Account from the list.
            Account account = activity.getAccountHashMap().get(selectedId);

            jTextFieldName.setText(account.getName());
            jTextFieldStreetname.setText(account.getStreet());
            jTextFieldHousenumber.setText(String.valueOf(account.getHousenumber()));
            jTextFieldHNAddition.setText(account.getHousenumberadd());
            jTextFieldPostalcode.setText(account.getPostalcode());
            jTextFieldCity.setText(account.getCity());

        }

    }
}
