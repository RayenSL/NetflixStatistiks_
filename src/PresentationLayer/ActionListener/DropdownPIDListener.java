package PresentationLayer.ActionListener;

import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropdownPIDListener extends ActionListenerStandard {

    public DropdownPIDListener(Activity activity) {

        super(activity, new JLabel());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        activity.addDataToPageGUI();
    }
}


