package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Enum;
import model.Main;
import model.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private ListView playerList;

    @FXML
    private TextField addPlayer;

    @FXML
    private RadioButton r1;

    @FXML
    private RadioButton r2;

    @FXML
    private RadioButton r3;

    ToggleGroup toggle;
    private Enum.Difficulty difficulty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggle = new ToggleGroup();
        r1.setToggleGroup(toggle);
        r2.setToggleGroup(toggle);
        r3.setToggleGroup(toggle);
        toggle.selectToggle(r1);
    }

    @FXML
    private void Ajout() {
        if(!addPlayer.getText().equals("")) {
            playerList.getItems().add(addPlayer.getText());
            addPlayer.setText("");
        }
    }

    @FXML
    private void Easy() {
        difficulty = Enum.Difficulty.easy;
    }

    @FXML
    private void Medium() {
        difficulty = Enum.Difficulty.medium;
    }

    @FXML
    private void Hard() {
        difficulty = Enum.Difficulty.hard;
    }

    @FXML
    private void close() {
        Stage primaryStage = (Stage) playerList.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void Lancement() throws IOException {
        if (playerList.getItems().size() > 1) {
            AnchorPane root = (AnchorPane) Main.getGameFXML().load();
            Scene scene = new Scene(root);

            GameController gController = Main.getGameFXML().getController();
            gController.setParameters(difficulty, playerList.getItems());

            Stage primaryStage = (Stage) playerList.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Util.deplacementFenetre(root, primaryStage);
        }
    }
}
