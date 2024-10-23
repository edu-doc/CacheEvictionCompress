import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private static String path = "log.txt";

    // Método para registrar ações na cache
    public void log(String acao, No no, Cache cache, Banco bc) throws MyPickException {
        StringBuilder filaString = new StringBuilder();

        // Converte a lista autoajustável para uma representação de string
        for (int i = 0; i < cache.tamanho(); i++) {
            No orderFila = cache.tabela.get(i); // Obtém o elemento diretamente da tabela
            if (orderFila != null) {
                filaString.append("[").append(orderFila.order.codigoServico).append("]"); // Usando código do serviço diretamente
            } else {
                filaString.append("[null]");
            }
        }

        String message = String.format(
                "Ação: %s, Código da Ordem de Serviço: %d, Fila de Cache: %s%n",
                acao,
                no.order != null ? no.order.codigoServico : 0,  // Corrigido para evitar 'null'
                filaString.toString()
        );

        // Grava a mensagem no arquivo de log
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
