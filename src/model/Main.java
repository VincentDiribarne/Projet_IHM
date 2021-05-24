package model;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
        Rectangle rect = new Rectangle(800,500);
        rect.setArcHeight(10); //Permet de regler l'angle
        rect.setArcWidth(10);  //Permet de regler l'angle
        root.setClip(rect);  //Ne pas oublier, très important

        Scene scene = new Scene(root, 800, 500); //Mettre la même taille qu'en haut
        scene.setFill(Color.TRANSPARENT);

        primaryStage.getIcons().add(new Image("./Ressources/Zombie.png"));
        primaryStage.setTitle("Zombie Dice");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();

        // allow the clock background to be used to drag the clock around.

        class Delta { double x, y; }

        final Delta dragDelta = new Delta();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = primaryStage.getX() - mouseEvent.getScreenX();
                dragDelta.y = primaryStage.getY() - mouseEvent.getScreenY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() + dragDelta.x);
                primaryStage.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}