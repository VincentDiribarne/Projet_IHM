package model;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Util {

    public static void deplacementFenetre(Pane root, Stage primaryStage){
        class Delta { double x, y; }

        final Delta dragDelta = new Delta();
        root.setOnMousePressed(mouseEvent -> {
            dragDelta.x = primaryStage.getX() - mouseEvent.getScreenX();
            dragDelta.y = primaryStage.getY() - mouseEvent.getScreenY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() + dragDelta.x);
            primaryStage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
    }

    public static class Utilities {
        public static int getMax(int[] array) {
            int max = 0;
            for (int i : array) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        }
    }
}
