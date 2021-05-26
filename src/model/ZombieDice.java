package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ZombieDice {

    private ArrayList<Dice> de_dispo;
    private ArrayList<Dice> deMain;
    private ArrayList<Dice> deRetirer;
    private ArrayList<Dice> Cerveau;


    private Enum.Difficulty difficulty;
    private ArrayList<Joueur> playersList;
    private int current_player_turn;
    private boolean finScore;
    private boolean finPartie;


    public ZombieDice(Enum.Difficulty difficulty) {
        this.difficulty = difficulty;
        this.current_player_turn = 0;
        this.playersList = new ArrayList<>();
        this.de_dispo = new ArrayList<>();
        this.deRetirer = new ArrayList<>();
        this.Cerveau = new ArrayList<>();
        this.deMain = new ArrayList<>();

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
                this.de_dispo.add(new Dice("green"));
            if (i <= thresholds[1])
                this.de_dispo.add(new Dice("yellow"));
            if (i <= thresholds[2])
                this.de_dispo.add(new Dice("red"));
        }
    }

    public boolean nextTurn() {
        int tour = this.current_player_turn;
        Joueur list = this.playersList.get(tour);
        list.validatePoints();
        if (list.getScore() >= 13) {
            finScore = true;
        }

        if (finScore && (this.playersList.size() - 1 == tour)) {
            finPartie = true;
        }

        tour++;

        if (tour >= this.playersList.size()) {
            tour = 0;
        }

        for (int i = 0; i < this.deRetirer.size(); i++)
            this.de_dispo.add(this.deRetirer.remove(i));

        for (int i = 0; i < this.deRetirer.size(); i++)
            this.de_dispo.add(this.deRetirer.remove(i));

        return finPartie;
    }

    public void takeDice() {
        int aPrendre = -1;
        int dices_to_take = 3 - this.deMain.size();

        if (de_dispo.size() < dices_to_take) {
            for (int i = Cerveau.size() - 1; i >= 0; i--) {
                de_dispo.add(Cerveau.remove(i));
            }
        }

        Random random = new Random();
        for (int i = 0; i < dices_to_take; i++) {
            aPrendre = random.nextInt(de_dispo.size());
            Dice aMettre = de_dispo.remove(aPrendre);
            deMain.add(aMettre);
        }
    }

    public void RollDice() {
        Random random = new Random();
        int id = this.current_player_turn;
        ArrayList<Enum.DiceFaces> faces = new ArrayList<Enum.DiceFaces>();
        ArrayList<Integer> a_enlever = new ArrayList<Integer>();

        for (int i = 0; i < 3; i++) {
            faces.add(this.deMain.get(i).getFaces().get(random.nextInt(6)));
            System.out.println(faces);

            if (faces.get(i) != Enum.DiceFaces.steps) {
                a_enlever.add(i);
            }

            if (faces.get(i) == Enum.DiceFaces.brain) {
                this.playersList.get(id).addPointsTemp(1);
                System.out.println(playersList.get(id).getScore_temp());
                Cerveau.add(deMain.remove(i));
            } else if (faces.get(i) == Enum.DiceFaces.shotgun) {
                System.out.println(playersList.get(id).getShotgun());
                this.playersList.get(id).addOneShotgun();
                if(playersList.get(id).getShotgun() > 2) {
                    playersList.get(id).setScore_temp(0);
                    nextTurn();
                }
            }
        }
        Collections.reverse(a_enlever);

        System.out.println("Removing " + a_enlever.size() + " dices...");
        for (Integer a_en: a_enlever) {
            this.deRetirer.add(this.deMain.remove((int)a_en));
        }
    }


    //Getters
    public ArrayList<Dice> getDe_dispo() {
        return de_dispo;
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
