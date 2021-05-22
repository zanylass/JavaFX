package p2210;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.shape.Box;

import java.security.SecureRandom;

public class Box10 extends Box implements ShapeAnimated {
    public Box10(double v, double v1, double v2) {
        super(v, v1, v2);
    }

    @Override
    public void animate(Parent parent) {
        SecureRandom random = new SecureRandom();

        AnimationTimer animationTimer = new AnimationTimer() {
            int dx = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dy = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dz = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);

            int velocity = 40; // used to scale distance changes
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
                double w = getWidth();
                double h = getHeight();
                double d = getDepth();

                if ((x_t <= (bounds.getMinX() + w)) ||
                        (x_t >= (bounds.getMaxX() - w))) {
                    dx *= -1;
                }

                if ((y_t <= (bounds.getMinY() + h)) ||
                        (y_t >= (bounds.getMaxY() - h))) {
                    dy *= -1;
                }

                if ((z_t <= (bounds.getMinZ() + d)) ||
                        (z_t >= (bounds.getMaxZ() - d))) {
                    dz *= -1;
                }
            }
        };
    }
}