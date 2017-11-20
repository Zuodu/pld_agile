package Modele;

/**
 * Created by flavi on 2017/11/18.
 */
public class Noeud {
    protected Long id;
    protected int x;
    protected int y;

    public Noeud(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "Noeud" + " " + id + " " + x + " " + y;
    }
}
