package model;

import java.util.ArrayList;

public class Dice {
        private final String color;
        private final ArrayList<Enum.DiceFaces> faces;

        public Dice(String color) {
                this.color = color;
                this.faces = new ArrayList<>();
                switch (color.toLowerCase()) {
                        case "green":
                                this.faces.add(Enum.DiceFaces.cerveau);
                                this.faces.add(Enum.DiceFaces.cerveau);
                                this.faces.add(Enum.DiceFaces.cerveau);
                                this.faces.add(Enum.DiceFaces.pas);
                                this.faces.add(Enum.DiceFaces.pas);
                                this.faces.add(Enum.DiceFaces.fusil);
                                break;
                        case "yellow":
                                this.faces.add(Enum.DiceFaces.cerveau);
                                this.faces.add(Enum.DiceFaces.cerveau);
                                this.faces.add(Enum.DiceFaces.pas);
                                this.faces.add(Enum.DiceFaces.pas);
                                this.faces.add(Enum.DiceFaces.fusil);
                                this.faces.add(Enum.DiceFaces.fusil);
                                break;
                        case "red":
                                this.faces.add(Enum.DiceFaces.cerveau);
                                this.faces.add(Enum.DiceFaces.pas);
                                this.faces.add(Enum.DiceFaces.pas);
                                this.faces.add(Enum.DiceFaces.fusil);
                                this.faces.add(Enum.DiceFaces.fusil);
                                this.faces.add(Enum.DiceFaces.fusil);
                                break;
                }
        }


        public String getColor() {
                return color;
        }

        public ArrayList<Enum.DiceFaces> getFaces() {
                return faces;
        }
}