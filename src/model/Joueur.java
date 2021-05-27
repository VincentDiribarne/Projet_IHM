package model;

public class Joueur {

    private int score;
    private int score_temp;
    private int shotgun;
    private String name;

    public Joueur(String name) {
        this.name = name;
        this.score = 0;
        this.score_temp = 0;
        this.shotgun = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore_temp() {
        return score_temp;
    }

    public void setScore_temp(int score_temp) {
        this.score_temp = score_temp;
    }

    public int getShotgun() {
        return shotgun;
    }

    public void setShotgun(int shotgun) {
        this.shotgun = shotgun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPoints() {
        return this.score + this.score_temp;
    }

    public void addPoints(int number) {
        this.score += number;
    }

    public void addPointsTemp(int number) {
        this.score_temp += number;
    }

    public boolean addOneShotgun() {
        this.shotgun += 1;
        return false;
    }

    public void validatePoints() {
        this.score += this.score_temp;
        this.score_temp = 0;
        this.shotgun = 0;
    }
}
