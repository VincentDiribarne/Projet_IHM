package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import model.Enum;

import java.io.IOException;
import java.util.HashMap;

public class GameController {
    private ZombieDice zombieDice;
    private GraphicsContext gc;
    private HashMap<String, Image> resources;

    @FXML
    private Text currentPlayer;

    @FXML
    private Text currentScore;

    @FXML
    private Text tempScore;

    @FXML
    private Text tempShotgun;

    @FXML
    private Text dernierTour;

    @FXML
    private Canvas canvasGame;

    public void initialize() {
        gc = canvasGame.getGraphicsContext2D();
        resources = new HashMap<>();
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
        zombieDice.addPlayers(players);
        setPlayerText();
    }

    private void setPlayerText() {
        int id = zombieDice.getTourJoueur();
        String nom = zombieDice.getPlayersList().get(id).getName();
        int TotalPoint = zombieDice.getPlayersList().get(id).getScore();

        String status = "Current player : " +nom;
        String score = "Score : " +TotalPoint;
        currentPlayer.setText(status);
        currentScore.setText(score);
    }

    public void setShotgunCervo() {
        int id = zombieDice.getTourJoueur();
        int shotgun = zombieDice.getPlayersList().get(id).getShotgun();
        int cervo = zombieDice.getPlayersList().get(id).getScore_temp();
        tempScore.setText(String.valueOf(cervo));
        tempShotgun.setText(String.valueOf(shotgun));
    }

    public void lancerDes() throws IOException {
        zombieDice.takeDice();
        zombieDice.RollDice();
        setShotgunCervo();
        setPlayerText();
        Alert();
        finScore();
        finPartie();
    }

    public void Alert() {
        if (zombieDice.isShotgun()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("BOOM");

            // alert.setHeaderText("Results:");
            alert.setContentText("BOOM, trois cerveau, t'es dead \nJoueur suivant");

            alert.showAndWait();
        }
    }

    public void finScore() {
        if (zombieDice.isFinScore()) {
            dernierTour.setText("Dernier Tour !");
        }
    }

    public void finPartie() throws IOException {
        if (zombieDice.isFinPartie()) {
            score();
        }
    }


    @FXML
    public void stopTurn() throws IOException{
        gc.clearRect(0,0, canvasGame.getWidth(), canvasGame.getHeight());
        tempShotgun.setText("0");
        tempScore.setText("0");
        zombieDice.nextTurn();
        finPartie();
        finScore();
        setPlayerText();
    }


    public void score() throws IOException{
            AnchorPane root = Main.getEndFXML().load();
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) canvasGame.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Util.deplacementFenetre(root, primaryStage);
    }
}
