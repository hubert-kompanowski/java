package drawing;

import com.sun.javafx.geom.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MyRectangle extends MyShape {


    @Override public Polygon draw(double x, double y , double radius, Color col){
        Polygon pol = new Polygon(x-20, y-30, x+20, y-30, x+20, y+30, x-20,y+30);
        pol.setFill(col);
        return pol;
    }


    @Override
    public String logo() {
        return "R ";
    }


    @Override
    public Shape impl_configShape() {
        return null;
    }
}
