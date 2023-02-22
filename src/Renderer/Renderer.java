package Renderer;

import model.Part;
import model.Solid;
import model.Vertex;
import raster.TriangleRasterizer;
import transforms.Col;
import transforms.Point3D;

import java.util.List;

public class Renderer {
    private TriangleRasterizer triangleRasterizer;
    //TODO dalsi rasterizery


    public Renderer(TriangleRasterizer triangleRasterizer) {
        this.triangleRasterizer = triangleRasterizer;
    }

    public void render(Solid solid){
        for(Part part : solid.getPartBuffer()) {
            switch(part.getType())
            {
                case LINE: {
                    System.out.println();
                } break;
                case TRIANGLE: {
                    int start = part.getIndex();
                    for(int i = 0; i< part.getCount();i++) {
                        int indexA = start;
                        int indexB = start+1;
                        int indexC = start+2;
                        start +=3;

                        Vertex a = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexA));
                        Vertex b = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexB));
                        Vertex c = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexC));

                        //TODO: Nastavit vertexy v triangleRasterizeru
                        //triangleRasterizer.rasterize(a,b,c,0xFF0000);
                        triangleRasterizer.rasterize(a.getPosition(),b.getPosition(),c.getPosition(),new Col(0xFF0000));
                    }
                } break;

            }
        }
    }
    public void render(List<Solid> scene){
        for (Solid s:scene) {
            render(s);
        }
    }
}
