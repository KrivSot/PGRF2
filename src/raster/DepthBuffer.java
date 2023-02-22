package raster;

public class DepthBuffer implements Raster<Double> {
    private final int width, height;
    private final double[][] buffer;

    public DepthBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new double[width][height];
    }

    @Override
    public void clear() {
        // TODO: nastavit všechny prvky bufferu na default value
        for(int x = 0 ;x < getWidth();x++)
        {
            for(int y = 0; y < getHeight();y++)
            {
                buffer[x][y] = 1;
            }
        }
    }

    @Override
    public void setClearValue(Double value) {
        // TODO: setter default value
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Double getValue(int x, int y) {
        // TODO: Optional
        return buffer[x][y];
    }

    @Override
    public void setValue(int x, int y, Double value) {
        if(isValid(x,y)) {
            buffer[x][y] = value;
        }
    }
}
