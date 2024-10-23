import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class LogProcessor {

    private static final String LOG_PATH = "log.txt";

    public static void buscarOperacoes(String padrao) {
        try {
            // Ler o log
            BufferedReader br = new BufferedReader(new FileReader(LOG_PATH));
            String linha;
            int numeroLinha = 0;

            while ((linha = br.readLine()) != null) {
                numeroLinha++;
                // Executar a busca usando KMP
                int[] ocorrencias = KMP.search(linha, padrao);

                // Exibir as ocorrências encontradas
                for (int index : ocorrencias) {
                    if (index != 0) { // Ignorar zeros do array
                        System.out.println("Encontrado na linha " + numeroLinha);
                        System.out.println("Linha: " + linha);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String padrao = "Buscado e inserido na cache"; // Exemplo de padrão
        buscarOperacoes(padrao);
    }
}
