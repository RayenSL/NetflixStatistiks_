package PresentationLayer.ActionListener;

import ApplicationLayer.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerStandard implements ActionListener {


    // This variable contains all data that is required for the ActionListener to manipulate data.
    protected Activity activity;

    // The result is being returned to this label.
    JLabel jLabelResult;

    public ActionListenerStandard(Activity activity, JLabel jLabelResult) {
        this.activity = activity;
        this.jLabelResult = jLabelResult;
    }

    // This result is returned to the page once the user instantiates an action.
    void showResult(boolean result) {
        String resultText;

        if (result) {
            resultText = "Action was handled succesfully!";
        } else {
            resultText = "Action was NOT handled succesfully!";
        }
        jLabelResult.setText(resultText);

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
