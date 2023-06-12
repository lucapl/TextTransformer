package pl.put.poznan.transformer.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Logger;

public class Controller {
    @FXML
    private TextArea textToEdit;

    @FXML
    private TextArea textTransforms;

    @FXML
    private Text outputTextTransformer;

    public void openFile(ActionEvent e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
                // Read the file and populate the text area
                // Code to read the file content and set it to the text area goes here
            textToEdit.clear();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    textToEdit.appendText(line+'\n');
                }
                reader.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public void saveFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            // Save the text area content to the file
            // Code to save the text area content to the file goes here
        }
    }

    public void saveFileAs(ActionEvent e) {}

    public void applyTransform(ActionEvent e) {}

    public void createNewFile(ActionEvent e) {}

    public void exit(ActionEvent e) {
        System.exit(0);
    }
}

