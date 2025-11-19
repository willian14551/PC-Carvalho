public class Trainer extends Player {
    private String nickname;
    private StorageSystem storage;

    // Construtores
    public Trainer() {
        super();
        this.storage = new StorageSystem();
    }

    public Trainer(String nome, String nickname) {
        super(nome);
        this.nickname = nickname;
        this.storage = new StorageSystem();
    }

    // Getters
    public String getNickname() { return nickname; }
    public StorageSystem getStorage() { return storage; }

    // Setters
    public void setStorage(StorageSystem storage) { this.storage = storage; }
}