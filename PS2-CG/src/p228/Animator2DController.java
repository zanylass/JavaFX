package p228;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.security.SecureRandom;


public class Animator2DController {
    @FXML Pane pane;
    private SecureRandom random = new SecureRandom();
    private int n;
    private int[] dx1;
    private int[] dy1;
    private int[] dx2;
    private int[] dy2;
    public void initialize() {
        n = random.nextInt(25) + 5;
        dx1 = new int[n];
        dy1 = new int[n];
        for (int i = 0; i < n; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(random.nextInt(200) + 50);
            ellipse.setCenterY(random.nextInt(200) + 50);
            ellipse.setRadiusX(random.nextInt(100));
            ellipse.setRadiusY(random.nextInt(100));
            ellipse.setFill(randomColor());
            ellipse.setStrokeWidth(random.nextInt(10));
            ellipse.setStroke(randomColor());
            pane.getChildren().add(ellipse);
            dx1[i] = 1 + random.nextInt(5);
            dy1[i] = 1 + random.nextInt(5);

            /*Rectangle rect = new Rectangle();
            rect.setX(random.nextInt(200)+ 70);
            rect.setY(random.nextInt(200)+ 70);
            rect.setHeight(random.nextInt(200)+ 50);
            rect.setWidth(random.nextInt(200)+ 50);
            rect.setArcHeight(20);
            rect.setArcHeight(20);
            rect.setFill(randomColor());
            ellipse.setStrokeWidth(random.nextInt(10));
            ellipse.setStroke(randomColor());
            pane.getChildren().add(rect);
            dxR[i] = 1 + random.nextInt(5);
            dyR[i] = 1 + random.nextInt(5);*/
        }
        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveCircles())
        );
        timelineAnimation.setCycleCount(Timeline.INDEFINITE);
        timelineAnimation.play();
    }


    private void moveCircles() {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Ellipse e = (Ellipse) pane.getChildren().get(i);
            e.setCenterX(e.getCenterX() + dx1[i]);
            e.setCenterY(e.getCenterY() + dy1[i]);
            if (e.getCenterX() + e.getRadiusX() > pane.getWidth() || e.getCenterX() - e.getRadiusX() < 0) dx1[i] = -dx1[i];
            if (e.getCenterY() + e.getRadiusY() > pane.getHeight() || e.getCenterY() - e.getRadiusY() < 0) dy1[i] = -dy1[i];
        }
    }

    /*private void moveRectangles() {
        for(int i=0; i<pane.getChildren().size();i++){
            Rectangle r = (Rectangle) pane.getChildren().get(i);
            r.setX(r.getX() + dxR[i]);
            r.setX(r.getX() + dyR[i]);
            if (r.getX() + r.getWidth() > pane.getWidth() || r.getX() - r.getWidth() < 0) dxR[i] -= dxR[i];
            if (r.getY() + r.getHeight() > pane.getHeight() || r.getY() - r.getHeight() < 0) dyR[i] -= dyR[i];

        }*/

    private Color randomColor(){
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                (double) random.nextInt(101) / 100);
    }
}