package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Joueur;
import model.ZombieDice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreController {

    @FXML
    private ListView<String> score;

    @FXML
    private Text premier;

    @FXML
    private Text second;

    @FXML
    private Text troisieme;

    @FXML
    private void close() {
        Stage primaryStage = (Stage) score.getScene().getWindow();
        primaryStage.close();
    }

    public void affichageScore(ZombieDice zombieDice) {
        ArrayList<Joueur> joueur = zombieDice.getPlayersList();

        System.out.println(joueur);
        System.out.println(score);
        joueur.sort(Comparator.comparing(Joueur::getScore));
        Collections.reverse(joueur);

        for (Joueur pl : joueur) {
            score.getItems().add(pl.getName() + " : " + pl.getScore());
        }

        premier.setText(joueur.get(0).getName());
        second.setText(joueur.get(1).getName());
        troisieme.setVisible(false);

        if(joueur.size() > 2) {
            troisieme.setVisible(true);
            troisieme.setText(joueur.get(2).getName());
        }
    }
}
