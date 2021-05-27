package model;

import controller.GameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ZombieDice {

    private ArrayList<Dice> DesDisponible;
    private ArrayList<Dice> DesDansLaMain;
    private ArrayList<Dice> DesAuSol;
    private ArrayList<Dice> ListeCerveau;


    private Enum.Difficulty difficulty;
    private ArrayList<Joueur> playersList;
    private int tourJoueur;
    private boolean finScore;
    private boolean finPartie;

    public ZombieDice(Enum.Difficulty difficulty) {
        this.difficulty = difficulty;
        this.tourJoueur = 0;
        this.playersList = new ArrayList<>();
        this.DesDisponible = new ArrayList<>();
        this.DesAuSol = new ArrayList<>();
        this.ListeCerveau = new ArrayList<>();
        this.DesDansLaMain = new ArrayList<>();
        ajoutDes();
    }

    public void ajoutDes() {
        int[] thresholds = {8, 3, 2};

        if (this.difficulty == Enum.Difficulty.medium) {
            thresholds[0] = 6;
            thresholds[1] = 4;
            thresholds[2] = 3;
        }
        if (this.difficulty == Enum.Difficulty.hard) {
            thresholds[0] = 4;
            thresholds[1] = 5;
            thresholds[2] = 4;
        }

        for (int i = 1; i < Util.Utilities.getMax(thresholds) + 1; i++) {
            if (i <= thresholds[0])
                this.DesDisponible.add(new Dice("green"));
            if (i <= thresholds[1])
                this.DesDisponible.add(new Dice("yellow"));
            if (i <= thresholds[2])
                this.DesDisponible.add(new Dice("red"));
        }
    }

    public boolean nextTurn() {
        reset();
        int tour = this.tourJoueur;
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

        return finPartie;
    }

    public void takeDice() {
        int aPrendre;
        int DesAPrendre = 3 - this.DesDansLaMain.size();

        if (DesDisponible.size() < DesAPrendre) {
            for (int i = ListeCerveau.size() - 1; i >= 0; i--) {
                DesDisponible.add(ListeCerveau.remove(i));
            }
        }

        Random random = new Random();
        for (int i = 0; i < DesAPrendre; i++) {
            aPrendre = random.nextInt(DesDisponible.size());
            Dice aMettre = DesDisponible.remove(aPrendre);
            DesDansLaMain.add(aMettre);
        }
    }

    public void RollDice() {
        Random random = new Random();
        int rd = random.nextInt(6);
        int id = this.tourJoueur;

        for (int i = 2; i >= 0; i--) {
            Enum.DiceFaces GenFaces = DesDansLaMain.get(i).getFaces().get(rd);
            System.out.println("#"+i + " => " +GenFaces+ " de couleur " +DesDansLaMain.get(i).getColor());

            if (GenFaces == Enum.DiceFaces.cerveau) {
                this.playersList.get(id).addPointsTemp(1);
                System.out.println("Cerveau => " +playersList.get(id).getScore_temp());
                ListeCerveau.add(DesDansLaMain.remove(i));
            }

            if (GenFaces == Enum.DiceFaces.fusil) {
                playersList.get(id).addOneShotgun();
                DesDansLaMain.remove(i);
                System.out.println("Shotgun =>" +playersList.get(id).getShotgun());
            }
        }

        if (playersList.get(id).getShotgun() >= 3) {
            playersList.get(id).setScore_temp(0);
            playersList.get(id).setShotgun(0);
            nextTurn();
        }

        if (playersList.get(id).getScore_temp() >= 13) {
            nextTurn();
        }
        System.out.println(DesDansLaMain.size());
    }

    public void reset() {
        ListeCerveau.clear();
        DesAuSol.clear();
        DesDisponible.clear();
        DesDansLaMain.clear();
        ajoutDes();
    }

    //Getters



    public ArrayList<Dice> getDesDisponible() {
        return DesDisponible;
    }

    public ArrayList<Dice> getDesAuSol() {
        return DesAuSol;
    }

    public Enum.Difficulty getDifficulty() {
        return difficulty;
    }

    public ArrayList<Joueur> getPlayersList() {
        return playersList;
    }

    public int getTourJoueur() {
        return tourJoueur;
    }

    public void addPlayers(List<String> names) {
        for (String name : names)
            this.playersList.add(new Joueur(name));
    }
}
