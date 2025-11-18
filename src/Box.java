import java.util.ArrayList;

public class Box {
    private String boxName;
    private ArrayList<PokemonBase> pokemonInBox;
    private final int TAMANHO_MAX = 30;

    // Construtores
    public Box() {
        this.pokemonInBox = new ArrayList<>();
    }
    
    public Box(String boxName) {
        this.boxName = boxName;
        this.pokemonInBox = new ArrayList<>();
    }

    // Getters
    public String getBoxName() { 
        return boxName; 
    }
    
    public ArrayList<PokemonBase> getPokemonInBox() { 
        return pokemonInBox; 
    }
    
    public int getPokemonCount() { 
        return pokemonInBox.size(); 
    }

    // Setters
    public void setBoxName(String boxName) { 
        this.boxName = boxName; 
    }

    // Mostra tudo da lista
    public void mostrarNaBox() {
        System.out.println("==============");
        System.out.println("Box: " + getBoxName());
        System.out.println("Pokémons: " + pokemonInBox.size() + "/" + TAMANHO_MAX);
        
        if (pokemonInBox.isEmpty()) {
            System.out.println("A Box está vazia!");
        } else {
            for (int i = 0; i < pokemonInBox.size(); i++) {
                PokemonBase PB = pokemonInBox.get(i);
                System.out.println(i + ". " + PB.getSpeciesName());
            }
        }
        System.out.println("==============");
    }

    // Lista pokémons 
    public void listPokemons() {
        System.out.println("==============");
        System.out.println("Box: " + getBoxName());
        System.out.println("Pokémons: " + pokemonInBox.size() + "/" + TAMANHO_MAX);
        
        if (pokemonInBox.isEmpty()) {
            System.out.println("A Box está vazia!");
        } else {
            for (int i = 0; i < pokemonInBox.size(); i++) {
                PokemonBase PB = pokemonInBox.get(i);
                System.out.println(i + ". " + PB.displaySummary());
            }
        }
        System.out.println("==============");
    }

    // Adiciona pokémon na box
    public void addPokemon(PokemonBase pokemon) throws BoxFullException {
        if (pokemonInBox.size() >= TAMANHO_MAX) {
            throw new BoxFullException(getBoxName(), pokemonInBox.size(), TAMANHO_MAX);
        }
        pokemonInBox.add(pokemon);
        System.out.println(pokemon.getSpeciesName() + " adicionado à box '" + getBoxName() + "'!");
    }

    // Remove pokémon da box por índice
    public void removePokemon(int index) {
        if (index < 0 || index >= pokemonInBox.size()) {
            System.out.println("Índice inválido!");
        } else {
            PokemonBase removed = pokemonInBox.remove(index);
            System.out.println(removed.getSpeciesName() + " removido da box '" + getBoxName() + "'!");
        }
    }

    // Busca pokémon por nome
    public PokemonBase buscarPokemon(String speciesName) {
        for (PokemonBase PB : pokemonInBox) {
            if (PB.getSpeciesName().equalsIgnoreCase(speciesName)) {
                return PB;
            }
        }
        return null;
    }

    // Verifica se a box está cheia
    public boolean isFull() {
        return pokemonInBox.size() >= TAMANHO_MAX;
    }

    // Verifica se a box está vazia
    public boolean isEmpty() {
        return pokemonInBox.isEmpty();
    }
}