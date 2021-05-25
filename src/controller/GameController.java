package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GameController {
    private GraphicsContext gc;
    private HashMap<String, Image> resources;

    @FXML
    private Text current_player_info;
    @FXML
    private Canvas canvas_game;

    public void initialize() {
        gc = canvas_game.getGraphicsContext2D();
        resources = new HashMap<String, Image>();
        resources.put("dice_green", new Image("file:resources/dice_green.png"));
        resources.put("dice_yellow", new Image("file:resources/dice_yellow.png"));
        resources.put("dice_red", new Image("file:resources/dice_red.png"));
        resources.put("shotgun", new Image("file:resources/shotgun.png"));
        resources.put("brain", new Image("file:resources/brain.png"));
    }


    public void goToScore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fin.fmxl"));
        Parent root = loader.load();
        
        Stage primaryStage = (Stage) canvas_game.getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
