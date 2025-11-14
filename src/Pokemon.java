import java.util.ArrayList;
import java.util.List;
import java.util.Map; // Importar o Map da interface

// Classe filha da classe PokemonBase
public class Pokemon extends PokemonBase {

    // --- Atributos Específicos ---
    private String type1;
    private String type2;
    private List<Move> moveset;

    // -- CONSTRUTOR --
    public Pokemon(
            // Parâmetros para a classe pai
            int nationalNumber, String speciesName, int level, String gender,
            String nickname, boolean isShiny,

            // Parâmetros para 'Pokemon' (Esta classe)
            String type1, String type2,

            // O Map de Stats vindo da Interface Gráfica (Futuro)
            Map<String, Integer> stats
    ) {

        // 1. Chamar o construtor do Pai (super)
        // Passa o Map de stats para o construtor do pai.
        super(nationalNumber, speciesName, level, gender, nickname, isShiny, stats); // <-- MUDANÇA AQUI

        // 2. Definir os atributos desta classe para o pai
        this.type1 = type1;
        this.type2 = type2;
        this.moveset = new ArrayList<>();
    }

     // -- POLIMORFISMO --
    // Do metodo DisplaySummary
    @Override
    public String displaySummary() {
        // Chame o método do pai para pegar a base
        String baseSummary = super.displaySummary();

        // Adicione as novas informações
        String fullSummary = baseSummary + "\n";
        fullSummary += "  Tipo: " + this.type1;
        if (this.type2 != null && !this.type2.isEmpty()) {
            fullSummary += " / " + this.type2;
        }

        // getStats é herdado e pega o Map que foi salvo
        fullSummary += "\n  Stats: " + getStats().toString();

        return fullSummary;
    }

    // --- Métodos Específicos ---
    public void learnMove(Move newMove) {
        if (this.moveset.size() < 4) {
            this.moveset.add(newMove);
        } else {
            System.out.println("Moveset está cheio!");
        }
    }
}