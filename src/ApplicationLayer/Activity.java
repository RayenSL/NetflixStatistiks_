package ApplicationLayer;

import DomainLayer.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;

public abstract class Activity extends JPanel {

    // Methods for its Child classes
    protected abstract void addPageGUI();
    public abstract void addDataToPageGUI();
    protected abstract void loadData();

    // Initializing GUI components
    private JPanel jPanel;
    protected JPanel jPanelContent;

    // Initializing Hashmaps for the different pages
    HashMap<Integer, Account> accountHashMap = new HashMap<>();
    HashMap<Integer, Profile> profileHashMap = new HashMap<>();
    HashMap<Integer, Movie> movieHashMap = new HashMap<>();
    HashMap<Integer, Serie> serieHashMap = new HashMap<>();
    HashMap<Integer, SerieEpisode> serieEpisodeHashMap = new HashMap<>();

    // Constructor
    public Activity(JPanel mainPanel, CardLayout cardLayout, GridLayout gridLayout) {


        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        jPanelContent = new JPanel();
        jPanelContent.setLayout(gridLayout);

        jPanelContent.setBackground(Color.LIGHT_GRAY);
        jPanelContent.setBorder(new LineBorder(Color.RED));

        jPanel.add(new NavigationActivity(mainPanel, cardLayout).getjPanelNavigation(), BorderLayout.WEST);
        jPanel.add(new FooterActivity().getjPanelFooter(), BorderLayout.SOUTH);
        jPanel.add(jPanelContent, BorderLayout.CENTER);

    }

    // Hashmap Getters
    public HashMap<Integer, Account> getAccountHashMap() {
        return accountHashMap;
    }
    public HashMap<Integer, Profile> getProfileHashMap() {
        return profileHashMap;
    }
    public HashMap<Integer, Movie> getMovieHashMap() {
        return movieHashMap;
    }
    public HashMap<Integer, Serie> getSerieHashMap() {
        return serieHashMap;
    }
    public HashMap<Integer, SerieEpisode> getSerieEpisodeHashMap() {
        return serieEpisodeHashMap;
    }


    // Adding components to the Activity
    @Override
    public void add(Component comp, Object constraints, int index) {
        jPanel.add(comp, constraints, index);
    }

    @Override
    public void add(Component comp, Object constraints) {
        jPanel.add(comp, constraints);
    }

    @Override
    public Component add(Component comp) {
        return jPanel.add(comp);
    }

    public JPanel getjPanel() {
        return jPanel;
    }

}
