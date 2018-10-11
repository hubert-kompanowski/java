

public class Punkt3D extends Punkt2D {

    private double z;

    public Punkt3D(double _x, double _y, double _z, String _n){
        super(_x, _y, _n);
        z = _z;
    }

    public double getZ(){
        return z;
    }

    public void setZ(double parm_z){
        z = parm_z;
    }

    public double distance(Punkt3D pkt){
        return Math.sqrt(Math.pow(pkt.getX()-this.getX(),2) + Math.pow(pkt.getY()-this.getY(),2) + Math.pow(pkt.z-z,2));
    }


}
