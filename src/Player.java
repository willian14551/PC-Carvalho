public abstract class Player {
    private String nome;

    // Construtores
    public Player(){}
    public Player(String nome){ this.nome=nome; }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}