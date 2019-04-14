package ApplicationLayer;

import PresentationLayer.ActionListener.NavButtonListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NavigationActivity {

    // Initializing GUI components
    private JPanel jPanelNavigation;

    // Constructor
    public NavigationActivity(JPanel jPanelContent, CardLayout cardLayout) {

        // Designing navigation panel style
        jPanelNavigation = new JPanel();
        jPanelNavigation.setBorder(new LineBorder(Color.RED));
        jPanelNavigation.setLayout(new BoxLayout(jPanelNavigation, BoxLayout.Y_AXIS));

        // Listeners
        JButton jButtonHome = new JButton("Home");
        jButtonHome.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "1"));

        // Buttons
        JButton jButtonMovie = new JButton("Movies");
        jButtonMovie.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "2"));

        JButton jButtonMovieLongestDurationAboveSixteen = new JButton("Longest Movie");
        jButtonMovieLongestDurationAboveSixteen.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "3"));

        JButton jButtonSerie = new JButton("Series");
        jButtonSerie.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "4"));

        JButton jButtonAccount = new JButton("Account");
        jButtonAccount.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "5"));

        JButton jButtonProfile = new JButton("Profile");
        jButtonProfile.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "6"));

        JButton jButtonCreateAccount = new JButton("Create Account");
        jButtonCreateAccount.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "7"));

        JButton jButtonCreateProfile = new JButton("Create Profile");
        jButtonCreateProfile.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "8"));

        JButton jButtonAccountsWithOneProfile = new JButton("One Profile Accounts");
        jButtonAccountsWithOneProfile.addActionListener(new NavButtonListener(jPanelContent, cardLayout, "9"));

        jPanelNavigation.add(jButtonHome);
        jPanelNavigation.add(jButtonMovie);
        jPanelNavigation.add(jButtonMovieLongestDurationAboveSixteen);
        jPanelNavigation.add(jButtonSerie);
        jPanelNavigation.add(jButtonAccount);
        jPanelNavigation.add(jButtonProfile);
        jPanelNavigation.add(jButtonCreateAccount);
        jPanelNavigation.add(jButtonCreateProfile);
        jPanelNavigation.add(jButtonAccountsWithOneProfile);
    }

    public JPanel getjPanelNavigation() {
        return jPanelNavigation;
    }
}
