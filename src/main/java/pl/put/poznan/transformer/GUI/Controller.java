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
import java.util.Scanner;
import java.util.logging.Logger;


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

    private File currentFile;

    /**
     * Function for setting up things which need to be set up after the launch of the application.
     */
    public void setup() {
        setAccelerator(saveButton, KeyCode.S);
        setAccelerator(openButton, KeyCode.O);
    }

    /**
     * Function for setting up things which need to be set up before starting the main application.
     */
    public void preSetup() {
        saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN));
        saveAsItem.setOnAction(event -> {
            saveFileAs(event);
        });
    }


    private File currentFile;

    /**
     * Opens a text file and loads its content into the textToEdit TextArea.
     * @param e the ActionEvent triggered by the openFile MenuItem or button.
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
     * Saves the current content of the textToEdit TextArea to a file.
     * If there is no currentFile, prompts the user to choose a file to save.
     * @param e the ActionEvent triggered by the saveFile MenuItem or button.
     */
    public void saveFile(ActionEvent e) {
        if (currentFile != null) {
            try {
                FileWriter writer = new FileWriter(currentFile);
                writer.write(outputTextTransformer.getText());
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
     * Prompts the user to choose a file and saves the content of the textToEdit TextArea to that file.
     * @param e the ActionEvent triggered by the saveAsItem MenuItem.
     */
    public void saveFileAs(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(outputTextTransformer.getText());
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

    /**
     * Copies the selected text in the textToEdit TextArea to the system clipboard.
     * Also updates the outputTextTransformer Text with the chosen text.
     */
    public void copySelectedText() {
        String selectedText = textToEdit.getSelectedText();
        if (!selectedText.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            clipboard.setContent(content);
          
            transformText(selectedText);//"Chosen text: \n" + selectedText);
        }
    }

    

    private void transformText(String input){
        transformText(input,Arrays.asList(textTransforms.getText().split(",")));
    }

    /**
     * Transforms the input text using the specified transformations and updates the outputTextTransformer Text.
     * @param input the input text to transform.
     * @param transforms the list of transformations to apply.
     */
    private void transformText(String input, List<String> transforms) {
        TextTransformer textTransformer = new TextTransformer(transforms);
        outputTextTransformer.setText(textTransformer.transform(input));
    }

    /**
     * Transforms the text in the textToEdit TextArea using the specified transformations.
     * @param e the ActionEvent triggered by the applyTransform button.
     */
    public void applyTransform(ActionEvent e) {
        transformText(textToEdit.getText());
    }

    public void createNewFile(ActionEvent e) {
    }

    /**
     * Exits the application.
     * @param e the ActionEvent triggered by the exit MenuItem or button.
     */
    public void exit(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Sets a keyboard shortcut for Ctrl+KeyCode(key) to "click" the given button.
     * @param button the button to be clicked.
     * @param key the key to be pressed in the shortcut.
     */
    private void setAccelerator(Button button, KeyCode key) {
        if (button == null) {
            System.out.println("Button is null!"); // Check that the button was injected properly through your fxml
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


