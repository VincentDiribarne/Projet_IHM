package model;

import java.util.ArrayList;

public class Dice {
        private String color;
        private ArrayList<Enum.DiceFaces> faces;

        public Dice(String color) {

                this.color = color;
                this.faces = new ArrayList<>();
                if (color.toLowerCase() == "green") {
                        this.faces.add(Enum.DiceFaces.cerveau);
                        this.faces.add(Enum.DiceFaces.cerveau);
                        this.faces.add(Enum.DiceFaces.cerveau);
                        this.faces.add(Enum.DiceFaces.pas);
                        this.faces.add(Enum.DiceFaces.pas);
                        this.faces.add(Enum.DiceFaces.fusil);
                } else if (color.toLowerCase() == "yellow") {
                        this.faces.add(Enum.DiceFaces.cerveau);
                        this.faces.add(Enum.DiceFaces.cerveau);
                        this.faces.add(Enum.DiceFaces.pas);
                        this.faces.add(Enum.DiceFaces.pas);
                        this.faces.add(Enum.DiceFaces.fusil);
                        this.faces.add(Enum.DiceFaces.fusil);
                } else if (color.toLowerCase() == "red") {
                        this.faces.add(Enum.DiceFaces.cerveau);
                        this.faces.add(Enum.DiceFaces.pas);
                        this.faces.add(Enum.DiceFaces.pas);
                        this.faces.add(Enum.DiceFaces.fusil);
                        this.faces.add(Enum.DiceFaces.fusil);
                        this.faces.add(Enum.DiceFaces.fusil);
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