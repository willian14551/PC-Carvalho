import java.util.ArrayList;

public class Move {
    private String moveName;
    private String moveType;
    private int power;
    private int pp;

    // Construtores
    public Move(){}
    public Move(String moveName, String moveType, int power, int pp) {}
    public void Move(String moveName, String moveType, int power, int pp){
        this.moveName=moveName;
        this.moveType=moveType;
        this.power=power;
        this.pp=pp;
    }

    // Setters
    public void setMoveName(String moveName) { this.moveName = moveName; }
    public void setMoveType(String moveType) { this.moveType = moveType; }
    public void setPower(int power) { this.power = power; }
    public void setPp(int pp) { this.pp = pp; }

    // Getters
    public String getMoveName() { return moveName; }
    public String getMoveType() { return moveType; }
    public int getPower() { return power; }
    public int getPp() { return pp; }

    // Lista dos moves disponíveis para o pokemon
    int tamanhoMaxMove = 4;
    ArrayList<Move> movesPoke = new ArrayList<>(tamanhoMaxMove);

    // Mostra a lista dos moves
    int i = 1;
    public void movesPoke(){
        System.out.println("--------------");
        if (movesPoke.isEmpty()){
            System.out.println("O pokémon é muito novo para saber fazer alguma coisa . . .");
        } else {
            for (Move move : movesPoke){
                System.out.println((i++)+". "+getMoveName());
                System.out.println(getMoveType());
                System.out.println(getPower());
                System.out.println(getPp());
                System.out.println("--------------");
            }
        }
    }

    // Métodos
    public void alterMove(String moveName, String moveType, int power, int pp){
        setMoveName(moveName);
        setMoveType(moveType);
        setPower(power);
        setPp(pp);
    }

    public void addMoveToMovesPoke(String moveName, String moveType, int power, int pp){
        setMoveName(moveName);
        setMoveType(moveType);
        setPower(power);
        setPp(pp);
        movesPoke.add(new Move(moveName, moveType, power, pp));
    }
}
