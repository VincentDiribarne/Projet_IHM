package model;

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
        int aPrendre = -1;
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
        ArrayList<Enum.DiceFaces> faces = new ArrayList<Enum.DiceFaces>();
        ArrayList<Integer> a_enlever = new ArrayList<Integer>();

        for (int i = 0; i < DesDansLaMain.size(); i++) {
            faces.add(DesDansLaMain.get(i).getFaces().get(rd));
            DesAuSol.add(DesDansLaMain.get(i));

            System.out.println("#"+i + " => "+faces.get(i)+ " de couleur " +DesDansLaMain.get(i).getColor());

            if (faces.get(i) != Enum.DiceFaces.pas) {
                a_enlever.add(i);
            }

            if (faces.get(i) == Enum.DiceFaces.cerveau) {
                this.playersList.get(id).addPointsTemp(1);
                System.out.println("Cerveau => " +playersList.get(id).getScore_temp());
                ListeCerveau.add(DesAuSol.get(i));
            }

            if (faces.get(i) == Enum.DiceFaces.fusil) {
                playersList.get(id).addOneShotgun();
                System.out.println("Shotgun =>" +playersList.get(id).getShotgun());
                if(playersList.get(id).getShotgun() >= 3) {
                    playersList.get(id).setScore_temp(0);
                    playersList.get(id).setShotgun(0);
                    nextTurn();
                }
            }
        }
        Collections.reverse(a_enlever);

        if(a_enlever.size() > 0) {
            for (Integer a_en: a_enlever) {
                DesDansLaMain.add(DesAuSol.remove((int)a_en));
            }
        }


        for(int i = 0; i < DesDansLaMain.size(); i++) {
            DesDansLaMain.remove(i);
            for (int v = 0; v < DesAuSol.size(); v++) {
                DesAuSol.remove(v);
            }
        }
    }

    public void reset() {
        for (int a = ListeCerveau.size() - 1; a >= 0; a--) {
            ListeCerveau.remove(a);
        }
        for (int b = DesDansLaMain.size() - 1; b >= 0; b--) {
            DesDansLaMain.remove(b);
        }

        for (int c = DesAuSol.size() - 1; c >= 0; c--) {
            DesAuSol.remove(c);
        }

        for (int d = DesDisponible.size() - 1; d >= 0; d--) {
            DesDisponible.remove(d);
        }
        for (int e =)
        ajoutDes();
        System.out.println(ListeCerveau.size());
        System.out.println(DesDisponible.size());
        System.out.println(DesAuSol.size());
        System.out.println(DesDansLaMain.size());
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
