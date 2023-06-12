package pl.put.poznan.transformer.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.nio.file.Files;

import java.util.Arrays;
import java.util.List;

/**
 * Controls the working of the GUI
 */
public class Controller {
    @FXML
    private TextArea textToEdit;

    @FXML
    private TextArea textTransforms;

    @FXML
    private Text outputTextTransformer;

    @FXML
    private Button saveButton;

    @FXML
    private MenuItem saveAsItem;

    @FXML
    private Button openButton;

    /**
     * Function for setting up things which needs to be set up after launch of application
     */
    public void setup() {
        setAccelerator(saveButton, KeyCode.S);
        setAccelerator(openButton, KeyCode.O);
    }

    /**
     * Function for setting up things which needs to be set up before staring main application
     */
    public void preSetup() {
        saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN));
        saveAsItem.setOnAction(event -> {
            saveFileAs(event);
        });
    }

    private File currentFile;

    /**
     * Opens a files
     * @param e event
     */
    public void openFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            textToEdit.clear();
            try {
                FileInputStream fileReader = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fileReader, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(isr);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    textToEdit.appendText(line+'\n');
                }

                // Close the file reader
                bufferedReader.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Saves current file
     * @param e event
     */
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

    /**
     * Save as new file
     * @param e event
     */
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

    public void copySelectedText() {
        String selectedText = textToEdit.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
            outputTextTransformer.setText("Chosen text: \n" + selectedText);
        }

    }

    private void transformText(String input, List<String> transforms){
        TextTransformer textTransformer = new TextTransformer(transforms);
        outputTextTransformer.setText(textTransformer.transform(input));
    }

    /**
     * Applies transformation on text in textToEdit
     * @param e event
     */
    public void applyTransform(ActionEvent e) {
        transformText(textToEdit.getText(), Arrays.asList(textTransforms.getText().split(",")));
    }

    public void createNewFile(ActionEvent e) {}

    /**
     * Exits from app
     * @param event
     */
    public void exit(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Sets keyboard shortcut for Ctrl+KeyCode(key) to "click" given button
     * @param button button to be pushed
     * @param key key to be pressed in shortcut
     */
    private void setAccelerator(Button button, KeyCode key) {
        if(button==null) {
            System.out.println("Button is null! "); // check that the button was injected properly through your fxml
        }
        Scene scene = button.getScene();
        if (scene == null) {
            throw new IllegalArgumentException("setSaveAccelerator must be called when a button is attached to a scene");
        }

        scene.getAccelerators().put(
                new KeyCodeCombination(key, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @FXML public void run() {

                        button.fire();
                    }
                }
        );
    }
}

