package ApplicationLayer;

import javax.swing.*;
import java.awt.*;

public class HomeActivity extends Activity{

    // Constructor
    public HomeActivity(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, new GridLayout(4, 1));
        addPageGUI();
    }

    // Adding Text to the GUI
    @Override
    protected void addPageGUI() {
        JLabel welcomeLabel = new JLabel("Welcome to the Netflix Statistix Application!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel secondLabel = new JLabel();
        secondLabel.setHorizontalAlignment(JLabel.CENTER);

        jPanelContent.add(welcomeLabel);
        jPanelContent.add(secondLabel);
    }

    @Override
    public void addDataToPageGUI() {
    }

    @Override
    protected void loadData() {
    }
}
