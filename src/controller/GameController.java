package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    @FXML
    private Text currentPlayer;

    @FXML
    private Text currentScore;

    @FXML
    private Canvas canvasGame;

    public void initialize() {
        gc = canvasGame.getGraphicsContext2D();
        resources = new HashMap<String, Image>();
        resources.put("dice_green", new Image("file:resources/dice_green.png"));
        resources.put("dice_yellow", new Image("file:resources/dice_yellow.png"));
        resources.put("dice_red", new Image("file:resources/dice_red.png"));
        resources.put("shotgun", new Image("file:resources/shotgun.png"));
        resources.put("brain", new Image("file:resources/brain.png"));
    }

    @FXML
    private void close() {
        Stage primaryStage = (Stage) canvasGame.getScene().getWindow();
        primaryStage.close();
    }

    public void setParameters(Enum.Difficulty difficulty, ObservableList<String> players) {
        zombieDice = new ZombieDice(difficulty);
        zombieDice.addPlayers((List<String>) players);
        setPlayerText();
    }

    private void setPlayerText() {
        int id = zombieDice.getCurrent_player_turn();
        String nom = zombieDice.getPlayersList().get(id).getName();
        int TotalPoint = zombieDice.getPlayersList().get(id).getTotalPoints();

        String status = "Current player : " +nom;
        String score = "Score : " +TotalPoint;
        currentPlayer.setText(status);
        currentScore.setText(score);
    }

    @FXML
    public void stopTurn() {
        gc.clearRect(0,0, canvasGame.getWidth(), canvasGame.getHeight());
        if(!zombieDice.winner()) {
            zombieDice.nextTurn();
        }
        setPlayerText();
    }


    public void score() throws IOException {
            AnchorPane root = (AnchorPane) Main.getEndFXML().load();
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) canvasGame.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Util.deplacementFenetre(root, primaryStage);
    }
}
