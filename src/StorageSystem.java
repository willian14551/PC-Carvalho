import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StorageSystem {
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

    // Remove uma box pelo indice
    public void removeBox(int index) {
        if (index < 0 || index >= boxList.size()) {
            System.out.println("Índice de box inválido!");
        } else {
            Box removed = boxList.remove(index);
            System.out.println("Box '" + removed.getBoxName() + "' removida do sistema!");
        }
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

    // Mostra todas as boxes e seus pokemons
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

    // Lista todos os pokemons 
    public void listAllPokemons() {
        System.out.println("\n=== STORAGE SYSTEM ===");
        if (boxList.isEmpty()) {
            System.out.println("Nenhuma box no sistema.");
        } else {
            for (int i = 0; i < boxList.size(); i++) {
                System.out.println("\nBox " + i + ":");
                boxList.get(i).listPokemons();
            }
        }
        System.out.println("=====================\n");
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

    // Busca um pokemon em todas as boxes
    public PokemonBase buscarPokemon(String speciesName) {
        for (Box box : boxList) {
            PokemonBase found = box.buscarPokemon(speciesName);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // Retorna o numero total de pokemons em todas as boxes
    public int getTotalPokemonCount() {
        int total = 0;
        for (Box box : boxList) {
            total += box.getPokemonCount();
        }
        return total;
    }

    public int getBoxCount() {
        return boxList.size();
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
            return false;
        }
    }


    // Exporta o StorageSystem para um arquivo de texto 
    public boolean exportarParaTexto(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== STORAGE SYSTEM ===");
            writer.println("Total de Boxes: " + boxList.size());
            writer.println("Total de Pokémons: " + getTotalPokemonCount());
            writer.println();

            for (int i = 0; i < boxList.size(); i++) {
                Box box = boxList.get(i);
                writer.println("Box " + i + ": " + box.getBoxName());
                writer.println("Pokémons: " + box.getPokemonCount() + "/30");
                
                for (int j = 0; j < box.getPokemonInBox().size(); j++) {
                    PokemonBase pokemon = box.getPokemonInBox().get(j);
                    writer.println("  " + j + ". " + pokemon.displaySummary());
                }
                writer.println();
            }

            System.out.println("Storage exportado para: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao exportar storage: " + e.getMessage());
            return false;
        }
    }


    //Exporta o StorageSystem para arquivo CSV 
    public boolean exportarParaCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("\"ID\",\"Name\",\"Form\",\"Type1\",\"Type2\",\"Total\",\"HP\",\"Attack\",\"Defense\",\"Sp. Atk\",\"Sp. Def\",\"Speed\",\"Generation\"");
            
            for (Box box : boxList) {
                for (PokemonBase pokemon : box.getPokemonInBox()) {
                    String linha = formatarPokemonCSV(pokemon);
                    writer.println(linha);
                }
            }
            
            System.out.println("Storage exportado para CSV: " + filename);
            System.out.println("Total de pokémons exportados: " + getTotalPokemonCount());
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao exportar para CSV: " + e.getMessage());
            return false;
        }
    }


    //Formata um PokemonBase para CSV

    private String formatarPokemonCSV(PokemonBase pokemon) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(pokemon.getNationalNumber()).append(",");
        
        sb.append("\"").append(pokemon.getSpeciesName()).append("\",");
        
        sb.append("\" \",");
        
        if (pokemon instanceof Pokemon) {
            Pokemon p = (Pokemon) pokemon;
            sb.append("\"").append(p.getType1() != null ? p.getType1() : "").append("\",");
            sb.append("\"").append(p.getType2() != null ? p.getType2() : " ").append("\",");
        } else {
            sb.append("\"Normal\",\" \",");
        }
        
        int hp = pokemon.getStats().getOrDefault("HP", 0);
        int attack = pokemon.getStats().getOrDefault("Attack", 0);
        int defense = pokemon.getStats().getOrDefault("Defense", 0);
        int spAtk = pokemon.getStats().getOrDefault("Sp. Attack", 0);
        int spDef = pokemon.getStats().getOrDefault("Sp. Defense", 0);
        int speed = pokemon.getStats().getOrDefault("Speed", 0);
        
        int total = hp + attack + defense + spAtk + spDef + speed;
        sb.append(total).append(",");
        
        sb.append(hp).append(",");
        sb.append(attack).append(",");
        sb.append(defense).append(",");
        sb.append(spAtk).append(",");
        sb.append(spDef).append(",");
        sb.append(speed).append(",");
        
        int generation = calcularGeracao(pokemon.getNationalNumber());
        sb.append(generation);
        
        return sb.toString();
    }


    //Calcula a geracao baseada no national number

    private int calcularGeracao(int nationalNumber) {
        if (nationalNumber <= 151) return 1;
        if (nationalNumber <= 251) return 2;
        if (nationalNumber <= 386) return 3;
        if (nationalNumber <= 493) return 4;
        if (nationalNumber <= 649) return 5;
        if (nationalNumber <= 721) return 6;
        if (nationalNumber <= 809) return 7;
        if (nationalNumber <= 905) return 8;
        return 9;
    }
}