public class Trainer extends Player {
    private int badges;
    //private StorageSystem storage;

    // Construtores
    public Trainer() {
    }

    public Trainer(String nome, int badges) {
        super(nome);
        this.badges = badges;
        //this.storage=storage;
    }

    // Getters
    public int getBadges() {
        return badges;
    }
    //public StorageSystem getStorage() { return storage; }

    // Setters
    public void setBadges(int badges) {
        this.badges = badges;
    }
    //public void setStorage (storageSystem storage){ this.storage=storage; }
}
