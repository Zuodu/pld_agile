package Modele;

/**
 * Created by flavi on 2017/11/18.
 */
public class Noeud {
    private Long id;
    private int x;
    private int y;

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
}
