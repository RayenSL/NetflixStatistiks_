package PresentationLayer.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavButtonListener implements ActionListener {
    private JPanel jPanel;
    private CardLayout cardLayout;
    private String page;

    public NavButtonListener(JPanel jPanel, CardLayout cardLayout, String page) {
        this.jPanel = jPanel;
        this.cardLayout = cardLayout;
        this.page = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.cardLayout.show(this.jPanel, this.page);

    }
}
