package raster;

import transforms.Col;
import transforms.Point2D;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;

public class TriangleRasterizer {

    private final ZBuffer zBuffer;

    private int width,height;
    public TriangleRasterizer(ZBuffer zBuffer)
    {
        this.zBuffer = zBuffer;
        this.width = zBuffer.getWidth();
        this.height = zBuffer.getHeight();
    }

    public void rasterize(Point3D p1, Point3D p2, Point3D p3, Col col){
        Vec3D a = transformToWindow(p1);
        Vec3D b = transformToWindow(p2);
        Vec3D c = transformToWindow(p3);

        Graphics g = zBuffer.getRaster().getGraphics();
        g.setColor(new Color(col.getRGB()));
        /*g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
        g.drawLine((int) b.x, (int) b.y, (int) c.x, (int) c.y);
        g.drawLine((int) c.x, (int) c.y, (int) a.x, (int) a.y);*/

        //Seřazení a, b, c podle y
        while(!(a.y <= b.y && b.y <= c.y))
        {
            Vec3D pomocVec;
            if(!(a.y < b.y)) {
                pomocVec = a;
                a = b;
                b = pomocVec;
            }
            if(!(b.y < c.y))
            {
                pomocVec = b;
                b = c;
                c = pomocVec;
            }
        }

        for(int y = (int)a.y; y < b.y;y++)
        {
            //interpolační koeficienty
            double s1=(y-a.y)/(b.y-a.y);
            double s2=(y-a.y)/(c.y-a.y);
            //x1 a x2
            int x1 = (int)(a.x*(1-s1)+b.x*s1);
            int x2= (int)(a.x*(1-s2)+c.x*s2);

            //z1 a z2
            double z1 = (a.z * (1.0 - s1) + b.z * s1);
            double z2 = (a.z * (1.0 - s2) + c.z * s2);

            if(x1 > x2)
            {
                double temp = x1;
                x1 = x2;
                x2 = (int) temp;
                temp = z1;
                z1 = z2;
                z2 = temp;
            }
            for(int x = (int)x1;x<x2;x++)
            {
                double t = (double)(x - x1) / (double)(x2 - x1);
                if (t >= 0) {
                    double z = z1 * (1.0 - t) + z2 * t;
                    zBuffer.compareZ(x,y,z,col);
                    System.out.println(z);
                }

            }
        }
        for(int y = (int)b.y; y < c.y;y++)
        {
            //interpolační koeficienty
            double s1=(y-a.y)/(c.y-a.y);
            double s2=(y-b.y)/(c.y-b.y);
            //x1 a x2
            int x1 = (int)(b.x*(1-s1)+c.x*s1);
            int x2 = (int)(a.x*(1-s2)+c.x*s2);
            //z1 a z2
            double z1 = (b.z * (1.0 - s1) + c.z * s1);
            double z2 = (a.z * (1.0 - s2) + c.z * s2);

            if(x1 > x2)
            {
                double temp = x1;
                x1 = x2;
                x2 = (int) temp;
                temp = z1;
                z1 = z2;
                z2 = temp;
            }

            for(int x = (int)x1;x<x2;x++)
            {
                double t = (double)(x - x1) / (double)(x2 - x1);
                if (t >= 0) {
                    double z = z1*(1.0 - t) + z2 * t;
                    zBuffer.compareZ(x,y,z,col);
                    System.out.println(z);
                }

            }
        }

    }


    //TODO popřemýšlet kde tato metoda bude

    private Vec3D transformToWindow(Point3D p)
    {
       return p.ignoreW()
               .mul(new Vec3D(1,-1,1))
               .add(new Vec3D(1,1,0))
               .mul(new Vec3D((width-1)/2,(height-1)/2,1));
    }
}
