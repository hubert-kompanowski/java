

public class Punkt2D {
    private double x;
    private double y;
    public String name;

    public Punkt2D(double _x, double _y, String _n){
        x = _x;
        y = _y;
        name = _n;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double parm_x){
        x = parm_x;
    }

    public void setY(double parm_y){
        y = parm_y;
    }

    public double distance(Punkt2D pkt){
        return Math.sqrt(Math.pow(x-pkt.x,2) + Math.pow(y-pkt.y,2));
    }

}



