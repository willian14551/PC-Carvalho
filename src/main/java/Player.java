import java.io.Serializable;

public abstract class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;

    // Construtores
    public Player(){}
    public Player(String nome){ this.nome=nome; }

    // Getters
    public String getNome() { return nome; }
    // Setters
}