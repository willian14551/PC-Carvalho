import java.util.Scanner;

public class Pokemon extends PokemonBase {

    private String type1;
    private String type2;


    public Pokemon(int nationalNumber, String speciesName, int level,
                   String gender, boolean isShiny,
                   String type1, String type2) {

        super(nationalNumber, speciesName, level, gender, isShiny);
        this.type1 = type1;
        this.type2 = type2;
    }

    public String getType1() { return type1; }
    public String getType2() { return type2; }

    public void setType1(String type1) { this.type1 = type1; }
    public void setType2(String type2) { this.type2 = type2; }

    @Override
    public void calcularStats(Scanner sc) {
        // Usa stats padrão com base no level (ignora o Scanner)
        int level = getLevel();
    }

    @Override
    public String displaySummary() {
        String base = super.displaySummary();
        String types;

        if (type2 == null || type2.isEmpty()) {
            types = "[" + type1 + "]";
        } else {
            types = "[" + type1 + "/" + type2 + "]";
        }

        return base + " " + types;
    }
}
