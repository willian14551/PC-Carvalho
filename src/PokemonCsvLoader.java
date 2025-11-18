import java.io.*;

public class PokemonCsvLoader {
    String linha;
    String separador = ";";

    // Construtores
    public PokemonCsvLoader(){}
    public PokemonCsvLoader(String linha, String separador){
        this.linha=linha;
        this.separador=separador;
    }

    // Getters
    public String getLinha() { return linha; }
    public String getSeparador() { return separador; }

    // Setters
    public void setLinha(String linha) { this.linha = linha; }
    public void setSeparador(String separador) { this.separador = separador; }

    // Métodos
    public void lerCsvTotal(String pathArquivo){
        try (BufferedReader br = new BufferedReader(new FileReader(pathArquivo))) {
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
                System.out.println("| " + String.join(" | ", dados) + " |"); // Organiza as infos dos pokes
            }

        } catch (
                IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

}
