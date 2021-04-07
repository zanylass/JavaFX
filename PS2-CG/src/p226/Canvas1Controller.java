package p226;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.canvas.Canvas;
        import javafx.scene.canvas.GraphicsContext;
        import javafx.scene.control.*;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Ellipse;
        import javafx.scene.shape.Line;
        import javafx.scene.shape.Rectangle;
        import javafx.scene.shape.Shape;

        import java.text.NumberFormat;
        import java.util.Stack;

public class Canvas1Controller {

    @FXML private ToggleGroup shapeSelect;
    @FXML private RadioButton lineRadioButton;
    @FXML private RadioButton recRadioButton;
    @FXML private RadioButton ovalRadioButton;
    @FXML private RadioButton eraserRadioButton;
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider thicknessSlider;
    @FXML private Rectangle colorSwatch;
    @FXML private Button undoButton;
    @FXML private Canvas drawingAreaCanvas;
    @FXML private Button clearButton;
    @FXML private TextField thicknessTextField;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private GraphicsContext gc;
    Line line = new Line();
    Rectangle rect = new Rectangle();
    Ellipse oval = new Ellipse();
    Stack<Shape> history = new Stack();

    public void initialize() {
        gc = drawingAreaCanvas.getGraphicsContext2D();
        thicknessTextField.textProperty().bindBidirectional(thicknessSlider.valueProperty(), NumberFormat.getNumberInstance());
        thicknessTextField.textProperty().addListener((args, oldValue, newValue )->{
            int val= (int)thicknessSlider.getValue();
            thicknessTextField.setText(String.valueOf(val));
        });
        redSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    red = newValue.intValue();
                    colorSwatch.setFill(Color.rgb(red, green, blue));
                }
        );
        greenSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    green = newValue.intValue();
                    colorSwatch.setFill(Color.rgb(red, green, blue));
                }
        );
        blueSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    blue = newValue.intValue();
                    colorSwatch.setFill(Color.rgb(red, green, blue));
                }
        );
        drawingAreaCanvas.setOnMousePressed(e -> {
            if (lineRadioButton.isSelected()) {
                line.setStartX(e.getX());
                line.setStartY(e.getY());
                line.setEndX(e.getX());
                line.setEndY(e.getY());
            } else if (recRadioButton.isSelected()) {
                rect.setX(e.getX());
                rect.setY(e.getY());
            } else if (ovalRadioButton.isSelected()) {
                oval.setCenterX(e.getX());
                oval.setCenterY(e.getY());
            } else if (eraserRadioButton.isSelected()) erase(e.getX(), e.getY());
        });
        drawingAreaCanvas.setOnMouseDragged(e -> {
            if (eraserRadioButton.isSelected()) erase(e.getX(), e.getY());
        });
        drawingAreaCanvas.setOnMouseReleased(e -> {
            gc.setStroke(Color.rgb(red, green, blue));
            if (lineRadioButton.isSelected()) {
                line.setStrokeWidth(thicknessSlider.getValue());
                line.setFill(Color.rgb(red, green, blue));
                line.setEndX(e.getX());
                line.setEndY(e.getY());
                gc.setLineWidth(thicknessSlider.getValue());
                gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                Line tempLine = new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                tempLine.setStroke(Color.rgb(red, green, blue));
                tempLine.setStrokeWidth(line.getStrokeWidth());
                history.push(tempLine);
            }
            else if (recRadioButton.isSelected()) {
                rect.setWidth(Math.abs((e.getX() - rect.getX())));
                rect.setHeight(Math.abs((e.getY() - rect.getY())));
                rect.setX(Math.min(rect.getX(), e.getX()));
                rect.setY(Math.min(rect.getY(), e.getY()));
                rect.setFill(Color.rgb(red, green, blue));
                gc.setFill(Color.rgb(red, green, blue));
                gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                Rectangle tempRect = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                tempRect.setFill(Color.rgb(red, green, blue));
                history.push(tempRect);
            } else if (ovalRadioButton.isSelected()) {
                oval.setRadiusX(Math.abs(e.getX() - oval.getCenterX()));
                oval.setRadiusY(Math.abs(e.getY() - oval.getCenterY()));
                oval.setCenterX(Math.min(oval.getCenterX(), e.getX()));
                oval.setCenterY(Math.min(oval.getCenterY(), e.getY()));
                oval.setFill(Color.rgb(red, green, blue));
                gc.setFill(Color.rgb(red, green, blue));
                gc.fillOval(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                gc.strokeOval(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                Ellipse tempOval = new Ellipse(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                tempOval.setFill(Color.rgb(red, green, blue));
                history.push(tempOval);
            } else if (eraserRadioButton.isSelected()) erase(e.getX(), e.getY());
        });
    }
    private void erase(double x, double y) {
        double sise = thicknessSlider.getValue();
        gc.clearRect(x - sise / 2, y - sise / 2, sise, sise);
    }
    @FXML
    private void onUndoPressed(ActionEvent event) {
        if (!history.isEmpty()) {
            history.pop();
            gc.clearRect(0, 0, drawingAreaCanvas.getWidth(), drawingAreaCanvas.getHeight());
            drawShapes(history);
        }
    }
    private void drawShapes(Stack<Shape> shapes) {
        for (Shape s: shapes) {
            if(s.getClass() == Line.class) {
                Line temp = (Line) s;
                gc.setLineWidth(temp.getStrokeWidth());
                gc.setStroke(temp.getStroke());
                gc.strokeLine(temp.getStartX(), temp.getStartY(), temp.getEndX(), temp.getEndY());
            }
            else if(s.getClass() == Rectangle.class) {
                Rectangle temp = (Rectangle) s;
                gc.setLineWidth(temp.getStrokeWidth());
                gc.setFill(temp.getFill());
                gc.setStroke(temp.getStroke());
                gc.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
                gc.strokeRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
            }
            else if(s.getClass() == Ellipse.class) {
                Ellipse temp = (Ellipse) s;
                gc.setStroke(temp.getStroke());
                gc.setLineWidth(temp.getStrokeWidth());
                gc.setFill(temp.getFill());
                gc.fillOval(temp.getCenterX(), temp.getCenterY(), temp.getRadiusX(), temp.getRadiusY());
                gc.strokeOval(temp.getCenterX(), temp.getCenterY(), temp.getRadiusX(), temp.getRadiusY());
            }
        }
    }

    @FXML
    private void onClearPressed(ActionEvent event) {
        gc.clearRect(0, 0, drawingAreaCanvas.getWidth(), drawingAreaCanvas.getHeight());
        history.clear();
    }

}
