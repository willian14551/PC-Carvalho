import java.io.*;

public class MainTesteMove {
    public static void main(String[] args) {
        PokemonCsvLoader csvLoader = new PokemonCsvLoader();

        csvLoader.lerCsvTotal("src/pokedex.csv");


    }

}