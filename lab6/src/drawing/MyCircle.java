package drawing;

import com.sun.javafx.geom.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends MyShape {


    @Override public Circle draw(double x, double y , double radius, Color col){
        return new Circle(x,y,radius,col);
    }

    @Override
    public String logo() {
        return "C ";
    }


    @Override
    public Shape impl_configShape() {
        return null;
    }
}
