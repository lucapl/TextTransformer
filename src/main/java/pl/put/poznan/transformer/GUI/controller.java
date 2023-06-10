import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    @FXML
    private TextArea textArea;

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            // Read the file and populate the text area
            // Code to read the file content and set it to the text area goes here
        }
    }

    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            // Save the text area content to the file
            // Code to save the text area content to the file goes here
        }
    }

    public void exit() {
        System.exit(0);
    }
}

