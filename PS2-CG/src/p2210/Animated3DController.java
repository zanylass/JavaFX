package p2210;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class Animated3DController{
    @FXML private Sphere sphere;
    @FXML private Pane pane;
    private int counter = 0;

    // set the material for each 3D shape
    public void initialize() {
        SecureRandom random = new SecureRandom();

        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        int randShape = random.nextInt(3);
                        double randWidth = random.nextDouble() * 100;
                        double randHeight = random.nextDouble() * 100;
                        double randDepth = random.nextDouble() * 100;

                        double randRadius=random.nextDouble()*100;

                        Bounds bounds = pane.getBoundsInLocal();

                        double randX = randWidth + random.nextInt((int) bounds.getWidth()/2);
                        double randY = randHeight + random.nextInt((int)(bounds.getHeight() / 2));
                        double randZ = randDepth + random.nextInt((int) (400 - randDepth));

                        double randRotate = random.nextDouble() * 360;

                        PhongMaterial phongMaterial = new PhongMaterial();
                        phongMaterial.setDiffuseColor(
                                Color.rgb(
                                        random.nextInt(256),
                                        random.nextInt(256),
                                        random.nextInt(256),
                                        (double) random.nextInt(80) / 100));
                        Cylinder10 cylinder = new Cylinder10(randWidth, randHeight);
                        cylinder.setTranslateX(randX);
                        cylinder.setTranslateY(randY);
                        cylinder.setTranslateZ(randZ);
                        cylinder.setRotationAxis(new Point3D(1,1,1));
                        cylinder.setRotate(randRotate);
                        cylinder.setMaterial(phongMaterial);

                        Sphere10 sphere = new Sphere10(randRadius);
                        sphere.setTranslateX(randX);
                        sphere.setTranslateY(randY);
                        sphere.setTranslateZ(randZ);
                        sphere.setRotationAxis(new Point3D(1,1,1));
                        sphere.setRotate(randRotate);
                        sphere.setMaterial(phongMaterial);

                        Box10 cube = new Box10(randDepth,randHeight, randWidth);
                        cube.setTranslateX(randX);
                        cube.setTranslateY(randY);
                        cube.setTranslateZ(randZ);
                        cube.setRotationAxis(new Point3D(1,1,1));
                        cube.setRotate(randRotate);
                        cube.setMaterial(phongMaterial);

                        //pane.getChildren().add(cube);
                        pane.getChildren().add(sphere);
                        pane.getChildren().add(cylinder);
                        cube.animate(pane);
                        cylinder.animate(pane);
                        sphere.animate(pane);

                    }
                })
        );

        timelineAnimation.setCycleCount(25);
        timelineAnimation.play();
    }
}


