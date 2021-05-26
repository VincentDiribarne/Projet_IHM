package model;

public class Joueur {

    private int score;
    private int score_temp;
    private int shotgun;
    private String name;
    private boolean hasFinished;

    public Joueur(String name) {
        this.name = name;
        this.score = 0;
        this.score_temp = 0;
        this.shotgun = 0;
        this.hasFinished = false;
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

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean addOneShotgun() {
        this.shotgun += 1;

        if (this.shotgun > 2) {
            this.shotgun = 0;
            this.score_temp = 0;
            return true;
        }
        return false;
    }

    public void validatePoints() {
        this.score += this.score_temp;
        this.score_temp = 0;
        this.shotgun = 0;
    }
}
