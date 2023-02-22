package model;

public class Arrow extends Solid {
    public Arrow() {
        //Vertex buffer
        getVertexBuffer().add(new Vertex(-1,0,0.5)); //v1
        getVertexBuffer().add(new Vertex(0,0,0.5)); //v2
        getVertexBuffer().add(new Vertex(1,0,0.5)); //v3
        getVertexBuffer().add(new Vertex(-1,0,0.5)); //v4
        getVertexBuffer().add(new Vertex(0,-1,0.5)); //v5

        //Index buffer
        getIndexBuffer().add(0);
        getIndexBuffer().add(1);

        getIndexBuffer().add(2);
        getIndexBuffer().add(3);
        getIndexBuffer().add(4);

        // Part buffer
        getPartBuffer().add(new Part(TopologyType.LINE,0,1));
        getPartBuffer().add(new Part(TopologyType.TRIANGLE,2,1));
    }
}
