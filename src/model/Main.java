package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static FXMLLoader menuFXML;
    public static FXMLLoader gameFXML;
    public static FXMLLoader endFXML;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Loader
        endFXML = new FXMLLoader(getClass().getResource("/view/End.fxml"));
        gameFXML = new FXMLLoader(getClass().getResource("/view/Game.fxml"));
        menuFXML = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));

        //Page de Menu
        StackPane root = new StackPane();
        root.getChildren().add(menuFXML.load());

        Rectangle rect = new Rectangle(800,500);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        root.setClip(rect);

        Scene scene = new Scene(root, 800, 500);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.getIcons().add(new Image("/Ressources/Zombie.png"));
        primaryStage.setTitle("Zombie Dice");

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);

        primaryStage.show();
        Util.deplacementFenetre(root, primaryStage);
    }

    public static FXMLLoader getEndFXML() {
        return endFXML;
    }

    public static FXMLLoader getGameFXML() {
        return gameFXML;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
