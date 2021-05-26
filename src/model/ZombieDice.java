package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieDice {

    private ArrayList<Dice> de_valide;
    private ArrayList<Dice> deRetirer;

    private Enum.Difficulty difficulty;
    private ArrayList<Joueur.Player> playersList;
    private int current_player_turn;

    public ZombieDice(Enum.Difficulty difficulty) {
        this.difficulty = difficulty;
        this.current_player_turn = 0;
        this.playersList = new ArrayList<>();
        this.de_valide = new ArrayList<>();
        this.deRetirer = new ArrayList<>();

        int[] thresholds = {8,3,2};

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

    public void nextTurn() {
        this.playersList.get(this.current_player_turn).validatePoints();
        this.current_player_turn++;

        if (this.current_player_turn > this.playersList.size() - 1) {
            this.current_player_turn = 0;
        }

        for (int i = 0; i < this.deRetirer.size(); i++)
            this.de_valide.add(this.deRetirer.remove(i));

        for (int i = 0; i < this.deRetirer.size(); i++)
            this.de_valide.add(this.deRetirer.remove(i));
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

    public ArrayList<Joueur.Player> getPlayersList() {
        return playersList;
    }

    public int getCurrent_player_turn() {
        return current_player_turn;
    }

    public void addPlayers(List<String> names) {
        for (String name: names)
            this.playersList.add(new Joueur.Player(name));
    }
}
