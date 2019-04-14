package ApplicationLayer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class FooterActivity extends JPanel {
    // Initializing GUI components
    private JPanel jPanelFooter;
    private String Space = "                                                                                                                                                                                         ";

    // Constructor
    public FooterActivity() {
        this.jPanelFooter = new JPanel();

        // designing Panel style
        jPanelFooter.setBorder(new BevelBorder(BevelBorder.LOWERED));
        jPanelFooter.setPreferredSize(new Dimension(1000, 16));
        jPanelFooter.setLayout(new BoxLayout(jPanelFooter, BoxLayout.X_AXIS));

        // Footer label text
        JLabel statusLabel = new JLabel("Netflix Statistiks" + Space + "Informatica          23IVT1C2        Rayen Syal          kelvin van Dam");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        jPanelFooter.add(statusLabel);
    }

    public JPanel getjPanelFooter() {
        return jPanelFooter;
    }
}
