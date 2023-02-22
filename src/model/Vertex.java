package model;

import transforms.Col;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

public class Vertex implements Vectorizable<Vertex> {
    private Point3D position;
    private Col color;
    private int width;
    private int height;
    //TODO: souřadnice to textury
    //TODO: normála
    //TODO: persp. korektní interpolace

    public Vertex(double x, double y, double z) {
        this.position = new Point3D(x,y,z);
        //TODO nastavit z parametru barvu
        this.color = new Col(0xFF0000);
    }

    public Vertex(Point3D position) {
        this.position = position;
        //TODO nastavit z parametru barvu
        this.color = new Col(0xFF0000);
    }

    public Point3D getPosition() {
        return position;
    }

    public Col getColor() {
        return color;
    }

    @Override
    public Vertex mul(double d) {
       return new Vertex(getPosition()).mul(d);
    }

    @Override
    public Vertex add(Vertex v) {
        return new Vertex(getPosition()).add(v);
    }

    public Vertex dehomog() {
        return new Vertex(getPosition()).dehomog();
    }

    public Vertex transform(Mat4 mat) {
        return new Vertex(getPosition().mul(mat));
    }

    private Vertex transformToWindow(Point3D p)
    {
        return new Vertex(new Point3D(p.ignoreW()
                .mul(new Vec3D(1,-1,1))
                .add(new Vec3D(1,1,0))
                .mul(new Vec3D((width-1)/2,(height-1)/2,1))));
    }
}
