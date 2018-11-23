package drawing;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.awt.*;

public abstract class MyShape extends Shape {

    public String name;
    static final int WIDTH = 800, HEIGHT = 375; // Size of our example

    public abstract Shape draw(double x, double y, double z, Color col);

    public abstract String logo();

}
