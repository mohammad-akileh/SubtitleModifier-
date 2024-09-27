package application;
import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Dir {

    public File chooseDirectory(Stage primaryStage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a Directory");

        // Set the initial directory
        // directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show the directory chooser
        return directoryChooser.showDialog(primaryStage);
    }
}