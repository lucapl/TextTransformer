package pl.put.poznan.transformer.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private TextArea textToEdit;

    @FXML
    private TextArea textTransforms;

    @FXML
    private Text outputTextTransformer;

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            // Save the text area content to the file
            // Code to save the text area content to the file goes here
        }
    }

    public void saveFileAs(ActionEvent e) {}

    private void transformText(String input, List<String> transforms){
        TextTransformer textTransformer = new TextTransformer(transforms);
        outputTextTransformer.setText(textTransformer.transform(input));
    }
    public void applyTransform(ActionEvent e) {
        transformText(textToEdit.getText(), Arrays.asList(textTransforms.getText().split(",")));
    }

    public void createNewFile(ActionEvent e) {}

    public void exit(ActionEvent e) {
        System.exit(0);
    }
}

