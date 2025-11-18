import java.io.*;

public class MainTesteMove {
    public static void main(String[] args) {
        String nomeArquivo = "src/pokedex.csv";
        String linha;
        String separador = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            // Lê a primeira linha do arquivo (cabeçalho)
            String cabecalho = br.readLine();

            if (cabecalho != null) {
                String[] colunas = cabecalho.split(separador);
                System.out.println("| " + String.join(" | ", colunas) + " |"); // Organiza as infos do cabeçalho
            }
            // Lê as linhas restantes do arquivo
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador); //Separa as strings em subStrings
                // Dados da linha em um array de strings, cada elemento é um campo
                System.out.println("| " + String.join(" | ", dados) + " |");
            }

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }


        //        Move move = new Move("Bola de fogo", "Fogo", 30, 10);
        //        Move move2 = new Move("Jato de água", "Água", 25, 10);
        //
        //        move.movesPoke();
        //        move2.movesPoke();

    }

}