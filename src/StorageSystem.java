import java.util.ArrayList;

public class StorageSystem {
    private ArrayList<Box> boxList;
    
    public StorageSystem() {
        this.boxList = new ArrayList<>();
    }
    
    public StorageSystem(ArrayList<Box> boxList) {
        this.boxList = boxList;
    }
    
    public ArrayList<Box> getBoxList() {
        return boxList;
    }
    
    public void addBox(Box box) {
        boxList.add(box);
        System.out.println("Box '" + box.getBoxName() + "' adicionada ao sistema!");
    }
    
    public void addPokemon(PokemonBase pokemon, int boxIndex) {
        if (boxIndex < 0 || boxIndex >= boxList.size()) {
            System.out.println("Índice de box inválido!");
            return;
        }
        
        Box targetBox = boxList.get(boxIndex);
        targetBox.addPokemon(pokemon);
    }
    
    public void addPokemon(PokemonBase pokemon, String boxName) {
        for (Box box : boxList) {
            if (box.getBoxName().equals(boxName)) {
                box.addPokemon(pokemon);
                return;
            }
        }
        System.out.println("Box '" + boxName + "' não encontrada!");
    }
    
    public void addPokemon(PokemonBase pokemon) {
        for (Box box : boxList) {
            if (box.getPokemonCount() < 30) {
                box.addPokemon(pokemon);
                return;
            }
        }
        System.out.println("Todas as boxes estão cheias! Crie uma nova box primeiro.");
    }
    
    public void mostrarTodasBoxes() {
        System.out.println("\n=== STORAGE SYSTEM ===");
        if (boxList.isEmpty()) {
            System.out.println("Nenhuma box no sistema.");
        } else {
            for (int i = 0; i < boxList.size(); i++) {
                System.out.println("\nBox " + i + ":");
                boxList.get(i).mostrarNaBox();
            }
        }
        System.out.println("=====================\n");
    }
    
    public int getTotalPokemonCount() {
        int total = 0;
        for (Box box : boxList) {
            total += box.getPokemonCount();
        }
        return total;
    }
}