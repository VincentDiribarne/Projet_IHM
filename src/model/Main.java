package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
        Rectangle rect = new Rectangle(600,400);
        rect.setArcHeight(20.0); //Permet de regler l'angle
        rect.setArcWidth(20.0);  //Permet de regler l'angle
        root.setClip(rect);  //Ne pas oublier, très important

        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 600, 400); //Mettre la même taille qu'en haut
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setTitle("Reconstitution - Version Etudiante");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
