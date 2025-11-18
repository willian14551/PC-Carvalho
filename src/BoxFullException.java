public class BoxFullException extends Exception {
    private String boxName;
    private int currentSize;
    private int maxSize;

    // Construtores
    public BoxFullException(String boxName) {
        super("A Box '" + boxName + "' está cheia!");
        this.boxName = boxName;
    }

    public BoxFullException(String boxName, int currentSize, int maxSize) {
        super("A Box '" + boxName + "' está cheia! (" + currentSize + "/" + maxSize + ")");
        this.boxName = boxName;
        this.currentSize = currentSize;
        this.maxSize = maxSize;
    }

    public BoxFullException(String boxName, String message) {
        super(message);
        this.boxName = boxName;
    }

    // Getters
    public String getBoxName() {
        return boxName;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getMaxSize() {
        return maxSize;
    }
}