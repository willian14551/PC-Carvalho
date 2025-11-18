public class Trainer extends Player {
    private int badges;
    private StorageSystem storage;

    // Construtores
    public Trainer() { super(); }
    public Trainer(String nome, int badges) {
        super(nome);
        this.badges = badges;
        this.storage=storage;
    }

    // Getters
    public int getBadges() { return badges; }
    public StorageSystem getStorage() { return storage; }

    // Setters
    public void setBadges(int badges) { this.badges = badges; }
    public void setStorage (StorageSystem storage){ this.storage=storage; }

    // Métodos
    public void addPokemonToBox(PokemonBase pokemon, int boxIndex) throws BoxFullException {
        if (storage == null) {
            System.out.println("O(A) treinador(a) "+ getNome() +"não tem StorageSystem configurado!");
            return;
        }
        
        storage.addPokemon(pokemon, boxIndex);
    }
    
}
