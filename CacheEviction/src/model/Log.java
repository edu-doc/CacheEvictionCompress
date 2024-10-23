import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private static String path = "log.txt";

    // Método para registrar ações na cache
    public void log(String acao, ServiceOrder order, Cache cache, Banco bc) throws MyPickException {
        StringBuilder filaString = new StringBuilder();
        
        // Converte a lista autoajustável para uma representação de string
        for (int i = 0; i < cache.tamanhoCache(); i++) {
            ServiceOrder orderFila = cache.buscarOrdemServico(i, bc); // Obtém o elemento da lista
            if (orderFila != null) {
                filaString.append("[").append(orderFila.getCodigoServico()).append("]");
            } else {
                filaString.append("[null]");
            }
        }

        String message = String.format(
                "Ação: %s, Código da Ordem de Serviço: %d, Fila de Cache: %s%n",
                acao,
                order != null ? order.getCodigoServico() : -1,  // Corrigido para evitar 'null'
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
