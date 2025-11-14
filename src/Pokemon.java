import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pokemon extends PokemonBase {

    // -- ATRIBUTOS ESPECIFICOS --
    private String type1;
    private String type2;

    // 'List<Move>' = Lista de moves de um pokemon e o ArrayList para organizar
    private List<Move> moveset;

    // -- CONSTRUTOR --
    // Recebe todos os parametros do pai e os seus proprios
    public Pokemon(
            int nationalNumber, String speciesName, int level, String gender,
            String nickname, boolean isShiny, String type1, String type2, Map<String, Integer> stats) {
        super(nationalNumber, speciesName, level, gender, nickname, isShiny, stats);

        // Definindo atributos dessa classe
        this.type1 = type1;
        this.type2 = type2;

        // Inicializar 'this.moveset' como um novo ArrayList<>()
        this.moveset = new ArrayList<>();
    }

    // --- SOBRESCRITA DE METODO (@Override) ---
    // Modifica o displaySummary do pai

    @Override // Anotação para o java entender que é uma sobrescrita
    public String displaySummary() {
        String baseSummary = super.displaySummary(); // Chama metodo 'displaySummary()' da classe PAI (super).

        String fullSummary = baseSummary + "\n";
        fullSummary += " Tipo: " + this.type1;

        // If para verificar se o type 2 está vazio
        if (this.type2 != null && !this.type2.isEmpty()){
            fullSummary += "/" + this.type2;// Se não for vazio, vai adicionar o type2 no summary
        }

        // Os Stats serão acrescentados
        fullSummary += "\n Stats: " + getStats().toString();

        return fullSummary;
    }

    // --- METODO ESPECIFICO ---
    // Metodo da classe Pokemon
     // Metodo que adiciona um 'Move' ao moveset, com limite de 4

    public void learnMove(Move newMove) {

        if (this.moveset.size() < 4){
            this.moveset.add(newMove);
            System.out.println(this.getNickname() + " aprendeu " + newMove.getMoveName() + "!");
        }
        else {
            System.out.println(this.getNickname() + " já sabe 4 ataques!");
        }
    }
}