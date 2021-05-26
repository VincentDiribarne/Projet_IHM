package model;

import java.util.ArrayList;

public class Dice {
        private String color;
        private ArrayList<Enum.DiceFaces> faces;

        public Dice(String color) {

                this.color = color;
                this.faces = new ArrayList<>();
                if (color.toLowerCase() == "green") {
                        this.faces.add(Enum.DiceFaces.brain);
                        this.faces.add(Enum.DiceFaces.brain);
                        this.faces.add(Enum.DiceFaces.brain);
                        this.faces.add(Enum.DiceFaces.steps);
                        this.faces.add(Enum.DiceFaces.steps);
                        this.faces.add(Enum.DiceFaces.shotgun);
                } else if (color.toLowerCase() == "yellow") {
                        this.faces.add(Enum.DiceFaces.brain);
                        this.faces.add(Enum.DiceFaces.brain);
                        this.faces.add(Enum.DiceFaces.steps);
                        this.faces.add(Enum.DiceFaces.steps);
                        this.faces.add(Enum.DiceFaces.shotgun);
                        this.faces.add(Enum.DiceFaces.shotgun);
                } else if (color.toLowerCase() == "red") {
                        this.faces.add(Enum.DiceFaces.brain);
                        this.faces.add(Enum.DiceFaces.steps);
                        this.faces.add(Enum.DiceFaces.steps);
                        this.faces.add(Enum.DiceFaces.shotgun);
                        this.faces.add(Enum.DiceFaces.shotgun);
                        this.faces.add(Enum.DiceFaces.shotgun);
                }
        }


        public String getColor() {
                return color;
        }

        public void setColor(String color) {
                this.color = color;
        }

        public ArrayList<Enum.DiceFaces> getFaces() {
                return faces;
        }

        public void setFaces(ArrayList<Enum.DiceFaces> faces) {
                this.faces = faces;
        }
}