import java.util.Map;
import java.util.HashMap;

public abstract class PokemonBase {

    // -- ATRIBUTOS --
    private int nationalNumber;
    private String speciesName;
    private int level;
    private String gender;
    private String nickname;
    private boolean isShiny;
    private Map<String, Integer> stats;

    // -- CONSTRUTOR --
    public PokemonBase(int nationalNumber, String speciesName, int level, String gender, String nickname, boolean isShiny){
        this.nationalNumber = nationalNumber;
        this.speciesName = speciesName;
        this.level = level;
        this.gender = gender;
        this.nickname = nickname;
        this.isShiny = isShiny;

        // INICIALIZA O MAP PRIVADO
        this.stats = new HashMap<>();
    }

    // -- METODO ABSTRATO setStat
    // Permite que as classes filhas insiram os valores no atributo Map stats
    // Enquanto a classe pai será quem gerencia seu próprio atributo stats
    protected void setStat(String statName, int value){
        this.stats.put(statName, value);
    }

    // -- METODO ABSTRATO calculateStats
    // Força as classes filhas a implementar os cálculos de stauts
    public abstract void calculateStats();

    // -- METODO levelup
    public void levelUp(){
        if (this.level < 100){
            this.level++;
            this.calculateStats(); // Recalcula os stats quando upar de nível
        }
    }

    // -- METODO setNickname
    // Metodo para permitir a alteração de nickname
    public void setNickname(){
        this.nickname = newNickname;
    }

    public void displaySummary(){
        if(this.isShiny) {
            String shinyMaker = "✨";
            System.out.println(this.nickname + " (Lvl " + this.level + ")" + shinyMaker);
        }
        else{
            System.out.println(this.nickname + " (Lvl " + this.level + ")");
        }
    }
}
