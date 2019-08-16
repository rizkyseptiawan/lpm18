/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author rizky
 */
public class apa extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle(50, Color.BLUE);
        Circle circle2 = new Circle(50, Color.BLUE);
//        Pane canvas = new Pane();
//        canvas.setStyle("-fx-background-color: black;");
//        canvas.setVisible(true);
//        canvas.setPrefSize(200, 200);
//        canvas.setLayoutX(123);
//        canvas.setLayoutY(300);
        
        
        VBox box = new VBox(10);
        box.getChildren().addAll(circle,circle2);
        box.setLayoutX(123);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
