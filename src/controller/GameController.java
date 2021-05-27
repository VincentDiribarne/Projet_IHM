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
    private Text finShotgun;

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
        System.out.println(id);
        String nom = zombieDice.getPlayersList().get(id).getName();
        System.out.println(nom);
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

    public void lancerDes() {
        clear();
        zombieDice.takeDice();
        zombieDice.RollDice();
        setShotgunCervo();
        if (zombieDice.isShotgun()) {
            finShotgun.setText("Vous avez eu 3 fusil et perdu tous les cerveaux, triste !");
        }

        if (zombieDice.isDernierTour()) {
            dernierTour.setText("Dernier Tour !");
        }
        setPlayerText();
    }

    private void clear() {
        currentScore.setText("0");
        finShotgun.setText(" ");
    }

    @FXML
    public void stopTurn() throws IOException{
        gc.clearRect(0,0, canvasGame.getWidth(), canvasGame.getHeight());
        if (zombieDice.nextTurn() == true) {
            score();
        }
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
