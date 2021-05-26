package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Enum;
import model.Main;
import model.Util;
import model.ZombieDice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GameController {
    private ZombieDice zombieDice;
    private GraphicsContext gc;
    private HashMap<String, Image> resources;
    boolean winner;

    @FXML
    private Text current_player;

    @FXML
    private Canvas canvas_game;

    @FXML
    private Text current_score;

    public void initialize() {
        gc = canvas_game.getGraphicsContext2D();
        resources = new HashMap<String, Image>();
        resources.put("dice_green", new Image("file:resources/dice_green.png"));
        resources.put("dice_yellow", new Image("file:resources/dice_yellow.png"));
        resources.put("dice_red", new Image("file:resources/dice_red.png"));
        resources.put("shotgun", new Image("file:resources/shotgun.png"));
        resources.put("brain", new Image("file:resources/brain.png"));
        //Util.deplacementFenetre(root, primaryStage);
    }

    public void setParameters(Enum.Difficulty diff, ObservableList<String> players) {
        zombieDice = new ZombieDice(diff);
        zombieDice.addPlayers((List<String>) players);
    }

    private void setPlayerText() {
        String status = "Current player: " + zombieDice.getPlayers_list().get(zombieDice.getCurrent_player_turn()).getName();
        String score = " Number of points: " + zombieDice.getPlayers_list().get(zombieDice.getCurrent_player_turn()).getTotalPoints();
        current_player.setText(status);
        current_score.setText(score);
    }


    public void score() throws IOException {
        if (winner = true) {
            AnchorPane root = (AnchorPane) Main.getEndFXML().load();
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) canvas_game.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Util.deplacementFenetre(root, primaryStage);
        }
    }
}
