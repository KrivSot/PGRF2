package raster;

import transforms.Col;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ZBuffer {

    ImageBuffer raster;
    private DepthBuffer zBuffer;
    public ZBuffer(ImageBuffer img)
    {
        raster = img;
        zBuffer = new DepthBuffer(getWidth(),getHeight());
        zBuffer.clear();
    }

    public void compareZ(int x, int y, double z, Col c)
    {
        if (z < zBuffer.getValue(x,y)) {
            zBuffer.setValue(x,y,z);
            raster.setValue(x, y, c);
        }
    }

    public int getWidth(){
        return raster.getWidth();
    }

    public int getHeight()
    {
        return raster.getHeight();
    }

    public ImageBuffer getRaster() {
        return raster;
    }
}
