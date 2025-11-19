import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Serializable;

public abstract class PokemonBase implements Serializable {
    private int nationalNumber;
    private String speciesName;
    private int level;
    private String gender;
    private boolean isShiny;
    private Map<String, Integer> stats;

    public PokemonBase(int nationalNumber, String speciesName, int level, String gender, boolean isShiny) {
        this.nationalNumber = nationalNumber;
        this.speciesName = speciesName;
        this.level = level;
        this.gender = gender;
        this.isShiny = isShiny;
        this.stats = new HashMap<>();
    }

    public int getNationalNumber() { return nationalNumber; }
    public String getSpeciesName() { return speciesName; }
    public int getLevel() { return level; }
    public String getGender() { return gender; }
    public boolean isShiny() { return isShiny; }
    public Map<String, Integer> getStats() { return stats; }

    public void setLevel(int level) { this.level = level; }

    public void setStats(Map<String, Integer> stats) {
        this.stats = (stats != null) ? new HashMap<>(stats) : new HashMap<>();
    }

    public abstract void calcularStats(Scanner sc);

    public String displaySummary() {
        String shinyMarker = isShiny ? " ✨" : "";
        return speciesName + " (Lvl " + level + ")" + shinyMarker;
    }
}
