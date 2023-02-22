package model;

import java.util.ArrayList;

public class Solid {
    private ArrayList<Vertex> vertexBuffer = new ArrayList<>();
    private ArrayList<Integer> indexBuffer = new ArrayList<>();
    private ArrayList<Part> partBuffer = new ArrayList<>();

    public Solid() {

    }

    public ArrayList<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public ArrayList<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public ArrayList<Part> getPartBuffer() {
        return partBuffer;
    }
}
