import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonCsvLoader {

    public static List<Pokemon> carregarDeCSV(String filename) throws IOException {
        List<Pokemon> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linha = br.readLine(); // cabeçalho: nationalNumber;speciesName;...

            while ((linha = br.readLine()) != null) {
                // separador ; conforme o arquivo
                String[] campos = linha.split(";", -1); // -1 pra manter campos vazios

                int nationalNumber = Integer.parseInt(campos[0]);
                String speciesName = campos[1];

                // esses vêm vazios no arquivo, mas já lemos por compatibilidade
                String gender = campos[2];   // geralmente ""
                String nickname = campos[3]; // geralmente igual speciesName
                boolean isShiny = Boolean.parseBoolean(campos[4]);

                String type1 = campos[5];
                String type2 = campos.length > 6 ? campos[6] : "";

                Pokemon p = new Pokemon(
                        nationalNumber,
                        speciesName,
                        1,          // Level padrão, usuário pode mudar na GUI
                        gender,
                        isShiny,
                        type1,
                        type2
                );

                lista.add(p);
            }
        }

        return lista;
    }
}
