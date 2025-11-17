public abstract class Player {
    private String nome;

    // Construtores
    public Player(){}
    public Player(String nome){ this.nome=nome; }

    // Getters
    public String getNome() { return nome; }
    // Setters
    public void setNome(String nome) { this.nome = nome; }
}