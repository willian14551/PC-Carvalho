import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Pokemon extends PokemonBase {

    private String type1;
    private String type2;

    private List<Move> moveset;


    public Pokemon(int nationalNumber, String speciesName, int level,
                   String gender, String nickname, boolean isShiny,
                   String type1, String type2) {

        super(nationalNumber, speciesName, level, gender, nickname, isShiny);
        this.type1 = type1;
        this.type2 = type2;
        this.moveset = new ArrayList<>();
    }

    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public List<Move> getMoveset() { return moveset; }

    public void setType1(String type1) { this.type1 = type1; }
    public void setType2(String type2) { this.type2 = type2; }

    @Override
    public void calcularStats(Scanner sc) {
        Map<String, Integer> stats = new HashMap<>();

        System.out.println("Digite os stats de " + getNickname()
                + " (Lvl " + getLevel() + "):");

        System.out.print("HP: ");
        stats.put("HP", sc.nextInt());

        System.out.print("Attack: ");
        stats.put("Attack", sc.nextInt());

        System.out.print("Defense: ");
        stats.put("Defense", sc.nextInt());

        System.out.print("Sp. Attack: ");
        stats.put("SpAtk", sc.nextInt());

        System.out.print("Sp. Defense: ");
        stats.put("SpDef", sc.nextInt());

        System.out.print("Speed: ");
        stats.put("Speed", sc.nextInt());

        setStats(stats);
        System.out.println("Stats registrados para " + getNickname() + "!\n");
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

    public void learnMove(Move newMove) {
        if (this.moveset.size() < 3) {
            this.moveset.add(newMove);
            System.out.println(this.getNickname() + " aprendeu " + newMove.getMoveName() + "!");
        } else {
            System.out.println(this.getNickname() + " já sabe 4 ataques!");
        }
    }
}
