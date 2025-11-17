import java.util.ArrayList;

public class MainTesteMove {
    public static void main(String[] args) {
        Move move = new Move("Bola de fogo", "Fogo", 30, 10);
        Move move2 = new Move("Jato de água", "Água", 25, 10);

        move.movesPoke();
        move2.movesPoke();

    }
}
