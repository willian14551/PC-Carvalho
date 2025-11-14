public class Move {
    private String moveName;
    private String moveType;
    private int power;
    private int pp;

    // Construtores
    public Move(String moveName, String moveType, int power, int pp) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.power = power;
        this.pp = pp;
    }

    // Getters
    public String getMoveName() { return moveName; }
    public String getMoveType() { return moveType; }
    public int getPower() { return power; }
    public int getPp() { return pp; }

    // Setters
    public void setMoveName(String moveName) { this.moveName = moveName; }
    public void setMoveType(String moveType) { this.moveType = moveType; }
    public void setPower(int power) { this.power = power; }
    public void setPp(int pp) { this.pp = pp; }

    // Mostra a lista dos moves
    // int i = 1;
    public void movesPoke() {
        System.out.println("--------------");
        System.out.println("Movimento: " + getMoveName());
        System.out.println("Tipo: " + getMoveType());
        System.out.println("Poder: " + getPower());
        System.out.println("PP: " + getPp());
        System.out.println("--------------");
    }

}
