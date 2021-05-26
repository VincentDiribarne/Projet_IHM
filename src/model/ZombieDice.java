package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieDice {

    private ArrayList<Dice> de_valide;
    private ArrayList<Dice> deRetirer;

    private Enum.Difficulty difficulty;
    private ArrayList<Joueur> playersList;
    private int current_player_turn;
    private boolean finScore;
    private boolean finPartie;

    public ZombieDice(Enum.Difficulty difficulty) {
        this.difficulty = difficulty;
        this.current_player_turn = 0;
        this.playersList = new ArrayList<>();
        this.de_valide = new ArrayList<>();
        this.deRetirer = new ArrayList<>();


        int[] thresholds = {8, 3, 2};

        if (this.difficulty == Enum.Difficulty.medium) {
            thresholds[0] = 6;
            thresholds[1] = 4;
            thresholds[2] = 3;
        } else if (this.difficulty == Enum.Difficulty.hard) {
            thresholds[0] = 4;
            thresholds[1] = 5;
            thresholds[2] = 4;
        }


        for (int i = 1; i < Util.Utilities.getMax(thresholds) + 1; i++) {
            if (i <= thresholds[0])
                this.de_valide.add(new Dice("green"));
            if (i <= thresholds[1])
                this.de_valide.add(new Dice("yellow"));
            if (i <= thresholds[2])
                this.de_valide.add(new Dice("red"));
        }
    }

    public boolean nextTurn() {
        int tour = this.current_player_turn;
        Joueur list = this.playersList.get(tour);
        list.validatePoints();
        if(list.getScore() >= 13) {
            finScore = true;
        }

        if(finScore && (this.playersList.size()-1 == tour)) {
            finPartie = true;
        }

        tour++;

        if (tour >= this.playersList.size()) {
            tour = 0;
        }

        for (int i = 0; i < this.deRetirer.size(); i++)
            this.de_valide.add(this.deRetirer.remove(i));

        for (int i = 0; i < this.deRetirer.size(); i++)
            this.de_valide.add(this.deRetirer.remove(i));

        return finPartie;
    }

    public void takeDices() {
        Random random = new Random();

    }

    //Getters
    public ArrayList<Dice> getDe_valide() {
        return de_valide;
    }

    public ArrayList<Dice> getDeRetirer() {
        return deRetirer;
    }

    public Enum.Difficulty getDifficulty() {
        return difficulty;
    }

    public ArrayList<Joueur> getPlayersList() {
        return playersList;
    }

    public int getCurrent_player_turn() {
        return current_player_turn;
    }

    public void addPlayers(List<String> names) {
        for (String name : names)
            this.playersList.add(new Joueur(name));
    }
}
