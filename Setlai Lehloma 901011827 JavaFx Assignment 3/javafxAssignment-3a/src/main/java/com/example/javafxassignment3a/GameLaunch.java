package com.example.javafxassignment3a;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

public class GameLaunch extends Application {

    Label gmvrLabel = new Label();
    TranslateTransition trans2 = new TranslateTransition();
    TranslateTransition trans3 = new TranslateTransition();
    TranslateTransition trans4 = new TranslateTransition();

    Rectangle top = new Rectangle(1100, 3);
    Rectangle left = new Rectangle(3, 700);
    Rectangle bottom = new Rectangle(1100, 110);
    Rectangle right = new Rectangle(3, 700);

    Label counterLabel = new Label();
    Label minutes = new Label();

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();

        Scene scene = new Scene(root, 1100, 700);



        //ImageViews of all the pictures
        ImageView military = createMilitary(scene);

        ImageView clouds2 = createClouds2(scene);
        ImageView clouds3 = createClouds3(scene);
        ImageView clouds4 = createClouds4(scene);

        Rectangle top1 = Top(scene);
        Rectangle bottom1 = Bottom(scene);
        Rectangle left1 = Left(scene);
        Rectangle right1 = Right(scene);
        root.getChildren().addAll(top1, bottom1, left1, right1 ,military, clouds2, clouds3, clouds4, gmvrLabel);


        AnimationTimer crash = new AnimationTimer() {
            @Override
            public void handle(long l) {

                CheckCollision(military, clouds2, clouds3, clouds4);
            }
        };

        crash.start();
        //Accessing the stylesheet
        scene.getStylesheets().add("Style.css");
        //Titling of the Pane
        stage.setTitle("Glide Plane");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    //Method to set and position the plane
    private ImageView createMilitary(Scene scene){

        //Setting the path to the Military image
        ImageView military2 = new ImageView(new Image("Images/Military2.png"));
        military2.setFitHeight(50);
        military2.setFitWidth(100);
        military2.setY(100);
        military2.setX(100);

        final int[] key = {0};
        final int[] keyH = {0};

        scene.setOnKeyPressed(e ->{
            switch (e.getCode()){
                case UP:
                    key[0] = key[0] - 10;
                    military2.setTranslateY(key[0]);
                    break;
                case DOWN:
                    key[0] = key[0] + 10;
                    military2.setTranslateY(key[0]);
                    break;
                case RIGHT:
                    keyH[0] = keyH[0] + 10;
                    military2.setTranslateX(keyH[0]);
                    break;
                case LEFT:
                    keyH[0] = keyH[0] - 10;
                    military2.setTranslateX(keyH[0]);
                    break;
            }
        });


        return  military2;
    }



    //Method to setting and positioning the second image of clouds
    private ImageView createClouds2(Scene scene){

        //Setting the path and placements of clouds2 image
        ImageView clouds2 = new ImageView(new Image("Images/clouds2.png"));
        clouds2.setFitHeight(120);
        clouds2.setFitWidth(-120);


        //Setting clouds2 motion

        trans2.setNode(clouds2);
        trans2.setDuration(Duration.millis(6000));
        trans2.setCycleCount(TranslateTransition.INDEFINITE);
        trans2.setFromX(1100 + 400);
        trans2.setToX(-600);
        trans2.setByX(150);
        trans2.setByY(0);
        trans2.setByX(scene.getWidth());
        trans2.setCycleCount(Integer.MAX_VALUE);
        trans2.setAutoReverse(false);
        trans2.play();

        return clouds2;
    }


    //Method for setting and positioning the third image of the clouds
    private ImageView createClouds3(Scene scene){

        //Setting the path and placements of clouds3 image
        ImageView clouds3 = new ImageView(new Image("Images/clouds3.png"));
        clouds3.setFitHeight(140);
        clouds3.setFitWidth(-140);
        clouds3.setY(210);

        //Setting clouds3 motion

        trans3.setNode(clouds3);
        trans3.setDuration(Duration.millis(5000));
        trans3.setCycleCount(TranslateTransition.INDEFINITE);
        trans3.setFromX(1100 + 400);
        trans3.setToX(-600);
        trans3.setByX(200);
        trans3.setByY(0);
        trans3.setByX(scene.getWidth());
        trans3.setCycleCount(Integer.MAX_VALUE);
        trans3.setAutoReverse(false);
        trans3.play();

        return clouds3;
    }

    private ImageView createClouds4(Scene scene){
        ImageView clouds4 = new ImageView(new Image("Images/clouds4.png"));
        clouds4.setFitHeight(140);
        clouds4.setFitWidth(-140);
        clouds4.setY(410);

        //Setting the motion

        trans4.setNode(clouds4);
        trans4.setDuration(Duration.millis(5000));
        trans4.setDelay(Duration.seconds(0.5));
        trans4.setCycleCount(TranslateTransition.INDEFINITE);
        trans4.setFromX(1100 + 400);
        trans4.setToX(-600);
        trans4.setByX(200);
        trans4.setByY(0);
        trans4.setByX(scene.getWidth());
        trans4.setCycleCount(Integer.MAX_VALUE);
        trans4.setAutoReverse(false);
        trans4.play();


        return clouds4;


    }

    private Rectangle Top(Scene scene){
        top.setX(0);
        top.setY(0);
        top.setFill(Color.TRANSPARENT);
        return top;
    }

    private Rectangle Bottom(Scene scene){
        bottom.setX(0);
        bottom.setY(582);
        bottom.setFill(Color.TRANSPARENT);
        return bottom;
    }

    private Rectangle Left(Scene scene){
        left.setX(0);
        left.setY(0);
        left.setFill(Color.TRANSPARENT);
        return left;
    }

    private Rectangle Right(Scene scene){
        right.setX(1097);
        right.setY(0);
        right.setFill(Color.TRANSPARENT);
        return right;
    }


    private void CheckCollision(ImageView military, ImageView clouds2, ImageView clouds3, ImageView clouds4){
        if (
            military.getBoundsInParent().intersects(clouds2.getBoundsInParent()) ||
            military.getBoundsInParent().intersects(clouds3.getBoundsInParent()) ||
            military.getBoundsInParent().intersects(clouds4.getBoundsInParent()) ||
            military.getBoundsInParent().intersects(top.getBoundsInParent()) ||
            military.getBoundsInParent().intersects(bottom.getBoundsInParent()) ||
            military.getBoundsInParent().intersects(left.getBoundsInParent()) ||
            military.getBoundsInParent().intersects(right.getBoundsInParent())){

            trans2.stop();
            trans3.stop();
            trans4.stop();
            gmvrLabel.setText("Game Over");
            gmvrLabel.setFont(new Font("Tahoma", 100));
            gmvrLabel.setTextFill(Color.web("#DDA15E"));
            gmvrLabel.setLayoutX(285);
            gmvrLabel.setLayoutY(250);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
