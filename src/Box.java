import java.util.ArrayList;

public class Box {
    private String boxName;

    // Construtores
    public Box(){}
    public Box(String boxName){
        this.boxName=boxName;
        this.pokemonInBox=pokemonInBox;
    }

    // Setters
    public void setBoxName(String boxName) { this.boxName = boxName; }

    // Getters
    public String getBoxName() { return boxName; }
    public int getPokemonCount() {
        return pokemonInBox.size();
    }

    // Box com capacidade de 30 pokemons
    int tamanhoMax = 30;
    ArrayList<PokemonBase> pokemonInBox = new ArrayList<PokemonBase>(tamanhoMax);

    // Mostra tudo da lista
    public void mostrarNaBox(){
        System.out.println("==============");
        if (pokemonInBox.isEmpty()){
            System.out.println();
        } else {
            int i =0;
            for (PokemonBase PB : pokemonInBox){
                System.out.println(getBoxName());
                System.out.println((i++)+". "+pIB.getSpeciesName);
            }
        }
        System.out.println("==============");
    }

    // Métodos
    public void addPokemon (PokemonBase pokemon){
        if (pokemonInBox.size() > 30){
            System.out.println("A Box '"+getBoxName()+"' está cheia!");
        } else {
            
        }
    }



}