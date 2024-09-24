import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private static String path = "log.txt";

    // Método para registrar ações na cache
    public void log(String acao, No no, int codigo, No[] tabela) {
        StringBuilder tabelaString = new StringBuilder();
        
        // Converte a tabela cache para uma representação de string
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                tabelaString.append("[").append(tabela[i].order.codigoServico).append("]");
            } else {
                tabelaString.append("[null]");
            }
        }

        String message = String.format("Ação: %s, Nó: %s, Código da Ordem de Serviço: %d, Tabela de Cache: %s%n",
                acao,
                no != null ? no.toString() : "null",
                no != null ? no.order.codigoServico : -1,
                tabelaString.toString());

        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
