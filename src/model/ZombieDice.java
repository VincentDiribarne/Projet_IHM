package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieDice {

    private final ArrayList<Dice> DesDisponible;
    private final ArrayList<Dice> DesDansLaMain;
    private final ArrayList<Dice> ListeCerveau;

    private final Enum.Difficulty difficulty;
    private final ArrayList<Joueur> playersList;
    private int tourJoueur;
    private boolean finScore;
    private boolean finPartie;
    private boolean finShotgun;

    public ZombieDice(Enum.Difficulty difficulty) {
        this.difficulty = difficulty;
        this.tourJoueur = 0;
        this.playersList = new ArrayList<>();
        this.DesDisponible = new ArrayList<>();
        this.ListeCerveau = new ArrayList<>();
        this.DesDansLaMain = new ArrayList<>();
        ajoutDes();
    }

    public void ajoutDes() {
        int[] thresholds = {8, 3, 2};

        if (difficulty == Enum.Difficulty.medium) {
            thresholds[0] = 6;
            thresholds[1] = 4;
            thresholds[2] = 3;
        }
        if (difficulty == Enum.Difficulty.hard) {
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

    public void nextTurn() {
        reset();
        Joueur list = this.playersList.get(tourJoueur);
        list.validatePoints();
        if (list.getTotalPoints() >= 13) {
            finScore = true;
        }

        if (finScore && (this.playersList.size() - 1 == tourJoueur)) {
            finPartie = true;
        }

        tourJoueur++;

        if (tourJoueur >= this.playersList.size()) {
            tourJoueur = 0;
        }
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
        finShotgun = false;
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
            finShotgun = true;
        }

        System.out.println(DesDansLaMain.size());
    }

    public void reset() {
        ListeCerveau.clear();
        DesDisponible.clear();
        DesDansLaMain.clear();
        ajoutDes();
    }

    //Getters
    public ArrayList<Joueur> getPlayersList() {
        return playersList;
    }

    public boolean isFinScore() {
        return finScore;
    }

    public boolean isFinPartie() {
        return finPartie;
    }

    public boolean isShotgun() {
        return finShotgun;
    }

    public int getTourJoueur() {
        return tourJoueur;
    }

    public void addPlayers(List<String> names) {
        for (String name : names)
            this.playersList.add(new Joueur(name));
    }
}
