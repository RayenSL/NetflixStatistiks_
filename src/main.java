import DataStorageLayer.DatabaseConnection;
import PresentationLayer.GraphicalUserInterface;

import javax.swing.*;

public class main {

    public static void main(String[] args) {
        GraphicalUserInterface graphicalUserInterface = new GraphicalUserInterface();

        // Initializing the database connection
        DatabaseConnection.connect();
        SwingUtilities.invokeLater(graphicalUserInterface);
    }
}

