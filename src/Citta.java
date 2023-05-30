import java.util.List;

public class Citta {
    
    private String id;
    private String nome;
    private int x;
    private int y;
    private int h;
    private List<String> link;

    public Citta(String id, String nome, int x, int y, int h, List<String> link) {
        this.id = id;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.h = h;
        this.link = link;
    }
    
    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getH() {
        return this.h;
    }

    public List<String> getLink() {
        return this.link;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Citta)obj).getId() == this.getId();
    }

    @Override
    public String toString() {
        return this.id + " " + this.nome;
    }
}
