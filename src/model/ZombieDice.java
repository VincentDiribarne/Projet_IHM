package model;

import java.util.ArrayList;
import java.util.List;

public class ZombieDice {

    private ArrayList<Dice> available_dice;
    private ArrayList<Dice> dice_to_throw;
    private ArrayList<Dice> thrown_dices;

    private Enum.Difficulty difficulty;
    private ArrayList<Joueur.Player> players_list;
    private int current_player_turn;
    private boolean game_end;

    public ZombieDice(Enum.Difficulty difficulty) {
        this.game_end = false;
        this.difficulty = difficulty;
        this.current_player_turn = 0;
        this.players_list = new ArrayList<Joueur.Player>();
        this.available_dice = new ArrayList<Dice>();
        this.dice_to_throw = new ArrayList<Dice>();
        this.thrown_dices = new ArrayList<Dice>();

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
                this.available_dice.add(new Dice("green"));
            if (i <= thresholds[1])
                this.available_dice.add(new Dice("yellow"));
            if (i <= thresholds[2])
                this.available_dice.add(new Dice("red"));
        }
    }

    public ArrayList<Dice> getAvailable_dice() {
        return available_dice;
    }

    public void setAvailable_dice(ArrayList<Dice> available_dice) {
        this.available_dice = available_dice;
    }

    public ArrayList<Dice> getDice_to_throw() {
        return dice_to_throw;
    }

    public void setDice_to_throw(ArrayList<Dice> dice_to_throw) {
        this.dice_to_throw = dice_to_throw;
    }

    public ArrayList<Dice> getThrown_dices() {
        return thrown_dices;
    }

    public void setThrown_dices(ArrayList<Dice> thrown_dices) {
        this.thrown_dices = thrown_dices;
    }

    public Enum.Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Enum.Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Joueur.Player> getPlayers_list() {
        return players_list;
    }

    public void setPlayers_list(ArrayList<Joueur.Player> players_list) {
        this.players_list = players_list;
    }

    public int getCurrent_player_turn() {
        return current_player_turn;
    }

    public void setCurrent_player_turn(int current_player_turn) {
        this.current_player_turn = current_player_turn;
    }

    public void addPlayers(List<String> names) {
        for (String name: names)
            this.players_list.add(new Joueur.Player(name));
    }
}
