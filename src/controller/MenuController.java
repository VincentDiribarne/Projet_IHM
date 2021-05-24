package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Util;

import java.io.IOException;

public class MenuController {

    @FXML
    private void Lancement() throws IOException {
        StackPane root = new StackPane();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("../view/game.fxml")));
        Rectangle rect = new Rectangle(800,500);
        rect.setArcHeight(10); //Permet de regler l'angle
        rect.setArcWidth(10);  //Permet de regler l'angle
        root.setClip(rect);  //Ne pas oublier, très important
        Scene scene = new Scene(root, 800, 500); //Mettre la même taille qu'en haut
        scene.setFill(Color.TRANSPARENT);

        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
