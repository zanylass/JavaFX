package p2210;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.shape.Cylinder;

import java.security.SecureRandom;

public class Cylinder10 extends Cylinder implements ShapeAnimated {
    public Cylinder10(double v, double v1) {
        super(v, v1);
    }

    @Override
    public void animate(Parent parent) {
        SecureRandom random = new SecureRandom();

        AnimationTimer animationTimer = new AnimationTimer() {
            int dx = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dy = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dz = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);

            int velocity = 50; // used to scale distance changes
            long previousTime = System.nanoTime(); // time since app launch
            Bounds bounds = parent.getBoundsInLocal();
            @Override
            public void handle(long l) {
                double elapsedTime = Math.abs(l - previousTime) / 1000000000.0;
                previousTime = l;
                double scale = elapsedTime * velocity;

                setTranslateX(getTranslateX() + dx * scale);
                setTranslateY(getTranslateY() + dy * scale);
                setTranslateZ(getTranslateZ() + dz * scale);

                double x_t = getTranslateX();
                double y_t = getTranslateY();
                double z_t = getTranslateZ();
                double r = getRadius();
                double h = getHeight();

                if ((x_t <= (bounds.getMinX() + r)) ||
                        (x_t >= (bounds.getMaxX() - r))) {
                    dx *= -1;
                }

                if ((y_t <= (bounds.getMinY() + h)) ||
                        (y_t >= (bounds.getMaxY() - h))) {
                    dy *= -1;
                }

                if ((z_t <= (bounds.getMinZ() + r)) ||
                        (z_t >= (bounds.getMaxZ() - r))) {
                    dz *= -1;
                }
            }
        };
        animationTimer.start();
    }
}
