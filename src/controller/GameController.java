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
import java.util.Collections;

public class GameController {
    private ZombieDice zombieDice;
    private GraphicsContext gc;

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
    private Text desVert;

    @FXML
    private Text desJaune;

    @FXML
    private Text desRouge;

    @FXML
    private Canvas canvasGame;

    public void initialize() {
        gc = canvasGame.getGraphicsContext2D();
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
        Difficulté(difficulty);
    }

    private void CanvasCouleur() {
        Image green = new Image(getClass().getResource("/Ressources/dice_green.png").toString());
        Image yellow = new Image(getClass().getResource("/Ressources/dice_yellow.png").toString());
        Image red = new Image(getClass().getResource("/Ressources/dice_red.png").toString());
        gc.clearRect(0, 0, canvasGame.getHeight(), canvasGame.getWidth());
        double offset_x = green.getWidth() + 40;

        for (int i = 2; i >= 0; i--) {
            switch (zombieDice.getDesDansLaMain().get(i).getColor()) {
                case "yellow" -> gc.drawImage(yellow, i * offset_x, 0, 64, 64);
                case "green" -> gc.drawImage(green, i * offset_x, 0, 64, 64);
                case "red" -> gc.drawImage(red, i * offset_x, 0, 64, 64);
            }
        }
    }

    private void CanvasFaces() {
        Image shotgun = new Image(getClass().getResource("/Ressources/shotgun.png").toString());
        Image brain = new Image(getClass().getResource("/Ressources/brain.png").toString());
        Image pas = new Image(getClass().getResource("/Ressources/pas.png").toString());
        double offset_x = 64 + 40;

        Collections.reverse(zombieDice.getGenFaces());

        for (int i = 0; i < 3; i++) {
            switch (zombieDice.getGenFaces().get(i)) {
                case cerveau -> gc.drawImage(brain, i * offset_x, 0, 64, 64);
                case fusil -> gc.drawImage(shotgun, i * offset_x, 0, 64, 64);
                case pas -> gc.drawImage(pas, i * offset_x, 0, 64, 64);
            }
        }
    }


    private void setPlayerText() {
        int id = zombieDice.getTourJoueur();
        int TotalPoint = zombieDice.getPlayersList().get(id).getScore();
        String nom = zombieDice.getPlayersList().get(id).getName();

        String status = "Current player : " + nom;
        String score = "Score : " + TotalPoint;
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
        CanvasCouleur();
        zombieDice.RollDice();
        CanvasFaces();
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

            alert.setContentText("BOOM, trois shotgun, t'es dead \nJoueur suivant");

            alert.showAndWait();
            gc.clearRect(0, 0, canvasGame.getWidth(), canvasGame.getHeight());
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
    public void stopTurn() throws IOException {
        gc.clearRect(0, 0, canvasGame.getWidth(), canvasGame.getHeight());
        tempShotgun.setText("0");
        tempScore.setText("0");
        zombieDice.nextTurn();
        finPartie();
        finScore();
        setPlayerText();
    }

    public void Difficulté(Enum.Difficulty difficulty) {
        switch (difficulty) {
            case easy :
                desVert.setText("8");
                desJaune.setText("3");
                desRouge.setText("2");
                break;
            case medium:
                desVert.setText("6");
                desJaune.setText("4");
                desRouge.setText("3");
                break;
            case hard:
                desVert.setText("4");
                desJaune.setText("5");
                desRouge.setText("4");
                break;
        }
    }


    public void score() throws IOException {
        AnchorPane root = Main.getEndFXML().load();
        Scene scene = new Scene(root);

        ScoreController scoreController = Main.getEndFXML().getController();
        scoreController.affichageScore(zombieDice);

        Stage primaryStage = (Stage) canvasGame.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Util.deplacementFenetre(root, primaryStage);
    }
}
