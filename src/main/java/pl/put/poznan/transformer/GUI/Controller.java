package pl.put.poznan.transformer.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    @FXML
    private TextArea textToEdit;

    @FXML
    private TextArea textTransforms;

    @FXML
    private Text outputTextTransformer;

    @FXML
    private Button saveButton;

    private File currentFile;

    public void openFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            // Read the file and populate the text area
            // Code to read the file content and set it to the text area goes here
        }
    }

    public void saveFile(ActionEvent e) {
        if (currentFile != null) {
            try {
                FileWriter writer = new FileWriter(currentFile);
                writer.write(textToEdit.getText());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Handle the exception as per your application's requirements
            }
        } else {
            saveFileAs(e);
        }
    }

    public void saveFileAs(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(textToEdit.getText());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Handle the exception as per your application's requirements
            }

            if (currentFile == null) {
                currentFile = file;
            }
        }

    }

    public void applyTransform(ActionEvent e) {}

    public void createNewFile(ActionEvent e) {}

    public void exit(ActionEvent e) {
        System.exit(0);
    }
}

