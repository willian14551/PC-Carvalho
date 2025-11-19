import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class StorageSystem implements Serializable {
    private ArrayList<Box> boxList;

    // Construtores
    public StorageSystem() {
        this.boxList = new ArrayList<>();
    }

    public StorageSystem(ArrayList<Box> boxList) {
        this.boxList = boxList;
    }

    // Getter
    public ArrayList<Box> getBoxList() {
        return boxList;
    }

    // Adiciona uma box ao sistema
    public void addBox(Box box) {
        boxList.add(box);
        System.out.println("Box '" + box.getBoxName() + "' adicionada ao sistema!");
    }

    // Adiciona pokemon em uma box especifica (por indice)
    public void addPokemon(PokemonBase pokemon, int boxIndex) throws BoxFullException {
        if (boxIndex < 0 || boxIndex >= boxList.size()) {
            System.out.println("Índice de box inválido!");
            return;
        }

        Box targetBox = boxList.get(boxIndex);
        targetBox.addPokemon(pokemon);
    }

    // Adiciona pokemon em uma box especifica (por nome)
    public void addPokemon(PokemonBase pokemon, String boxName) throws BoxFullException {
        for (Box box : boxList) {
            if (box.getBoxName().equals(boxName)) {
                box.addPokemon(pokemon);
                return;
            }
        }
        System.out.println("Box '" + boxName + "' não encontrada!");
    }

    // Adiciona pokemon na primeira box disponivel
    public void addPokemon(PokemonBase pokemon) {
        for (Box box : boxList) {
            if (!box.isFull()) {
                try {
                    box.addPokemon(pokemon);
                    return;
                } catch (BoxFullException e) {
                    continue;
                }
            }
        }
        System.out.println("Todas as boxes estão cheias! Crie uma nova box primeiro.");
    }

    // Remove um pokemon de uma box específica
    public void removePokemon(int boxIndex, int pokemonIndex) {
        if (boxIndex < 0 || boxIndex >= boxList.size()) {
            System.out.println("Índice de box inválido!");
            return;
        }

        Box targetBox = boxList.get(boxIndex);
        targetBox.removePokemon(pokemonIndex);
    }


    // Retorna o numero total de pokemons em todas as boxes
    public int getTotalPokemonCount() {
        int total = 0;
        for (Box box : boxList) {
            total += box.getPokemonCount();
        }
        return total;
    }

    //Salva o StorageSystem em um arquivo
    public boolean salvarStorage(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Storage salvo com sucesso em: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar storage: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static StorageSystem carregarStorage(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            StorageSystem storage = (StorageSystem) ois.readObject();
            System.out.println("Storage carregado com sucesso de: " + filename);
            return storage;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar storage: " + e.getMessage());
            return null;
        }
    }

}