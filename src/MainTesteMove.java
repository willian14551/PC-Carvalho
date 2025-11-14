public class MainTesteMove {
    public static void main(String[] args) {
        Move move = new Move();

        move.movesPoke();

        move.addMoveToMovesPoke("Bola de fogo", "Fogo", 30, 10);
        move.addMoveToMovesPoke("Jato de água", "Água", 25, 10);
        move.movesPoke();

    }
}
