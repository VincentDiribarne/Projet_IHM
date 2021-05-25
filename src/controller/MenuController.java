package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

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



    @FXML
    private void Ajout() {
        if(!addPlayer.getText().equals("")) {
            playerList.getItems().add(addPlayer.getText());
        }
    }

    @FXML
    private void Difficulty() {
        ToggleGroup toggle = new ToggleGroup();
        r1.setToggleGroup(toggle);
        r2.setToggleGroup(toggle);
        r3.setToggleGroup(toggle);
        r1.setSelected(true);
        if

    }

    @FXML
    private void close() {
        Stage primaryStage = (Stage) playerList.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void Lancement() throws IOException {
        if (playerList.getItems().size() > 1) {
            StackPane root = new StackPane();
            root.getChildren().add(FXMLLoader.load(getClass().getResource("../view/game.fxml")));
            Rectangle rect = new Rectangle(800, 500);
            rect.setArcHeight(10); //Permet de regler l'angle
            rect.setArcWidth(10);  //Permet de regler l'angle
            root.setClip(rect);  //Ne pas oublier, très important
            Scene scene = new Scene(root, 800, 500); //Mettre la même taille qu'en haut
            scene.setFill(Color.TRANSPARENT);

            Stage primaryStage = (Stage) playerList.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

}
