package PresentationLayer;

import ApplicationLayer.*;

import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface implements Runnable {

    private JFrame frame;
    private JPanel jPanelContent;
    private CardLayout cardLayout;

    @Override
    public void run() {

        frame = new JFrame("Netflix Statistiks");

        cardLayout = new CardLayout();

        jPanelContent = new JPanel();
        jPanelContent.setLayout(cardLayout);

        // Adding all the pages to the content panel
        addPages();

        // Show on Start up
        cardLayout.show(jPanelContent, "1");

        // When window is closed this must activate
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Size
        frame.setSize(new Dimension(1280, 720));
        frame.setMinimumSize(new Dimension(700, 650));
        frame.setResizable(true);

        // frame sizes
        frame.pack();

        // Center the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

    private void addPages() {

        // Initializing all pages
        Activity jPanelHome = new HomeActivity(jPanelContent, cardLayout);
        Activity jPanelMovie = new MovieActivity(jPanelContent, cardLayout);
        Activity jPanelMovieLongestDurationAboveSixteen = new CustomMovieActivity(jPanelContent, cardLayout);
        Activity jPanelSerie = new SerieActivity(jPanelContent, cardLayout);
        Activity jPanelAccount = new AccountActivity(jPanelContent, cardLayout);
        Activity jPanelProfile = new ProfileActivity(jPanelAccount, jPanelContent, cardLayout);
        Activity jPanelCreateAccount = new AccountCreateActivity(jPanelAccount, jPanelContent, cardLayout);
        Activity jPanelCreateProfile = new ProfileCreateActivity(jPanelAccount, jPanelProfile, jPanelContent, cardLayout);
        Activity jPanelAccountsWithOneProfilePage = new AccountProfileActivity(jPanelAccount, jPanelProfile, jPanelContent, cardLayout);

        // Adding all the panels
        jPanelContent.add(jPanelHome.getjPanel(), "1");
        jPanelContent.add(jPanelMovie.getjPanel(), "2");
        jPanelContent.add(jPanelMovieLongestDurationAboveSixteen.getjPanel(), "3");
        jPanelContent.add(jPanelSerie.getjPanel(), "4");
        jPanelContent.add(jPanelAccount.getjPanel(), "5");
        jPanelContent.add(jPanelProfile.getjPanel(), "6");
        jPanelContent.add(jPanelCreateAccount.getjPanel(), "7");
        jPanelContent.add(jPanelCreateProfile.getjPanel(), "8");
        jPanelContent.add(jPanelAccountsWithOneProfilePage.getjPanel(), "9");

        // Adding the main panel
        frame.add(jPanelContent);
    }
}

