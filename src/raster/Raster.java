package raster;

public interface Raster<E> {

    void clear();

    void setClearValue(E value);

    int getWidth();

    int getHeight();

    E getValue(int x, int y);

    void setValue(int x, int y, E value);

    default boolean isValid(int x, int y){
        if((x < 0 || x > getWidth()) || (y < 0 || y > getHeight())) return false;
        else return true;
    }

}
