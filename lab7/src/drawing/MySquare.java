package drawing;

import com.sun.javafx.geom.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MySquare extends MyShape {


    @Override public Polygon draw(double x, double y , double radius, Color col){
        Polygon pol = new Polygon(x-30, y-30, x+30, y-30, x+30, y+30, x-30,y+30);
        pol.setFill(col);
        return pol;
    }

    @Override
    public String logo() {
        return "S ";
    }


    @Override
    public Shape impl_configShape() {
        return null;
    }
}
