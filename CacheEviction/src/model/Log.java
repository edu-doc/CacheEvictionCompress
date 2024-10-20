import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private static String path = "log.txt";

    // Método para registrar ações na cache
    public void log(String acao, No no, int codigo, FilaPrioridade<No> filaPrioridade) {
        StringBuilder filaString = new StringBuilder();
        
        // Converte a fila de prioridade para uma representação de string
        for (int i = 0; i < filaPrioridade.size(); i++) {
            No noFila = filaPrioridade.peek(); // Obtém o elemento da fila
            if (noFila != null && noFila.getServiceOrder() != null) {
                filaString.append("[").append(noFila.getServiceOrder().getCodigoServicoFormatado()).append("]");
            } else {
                filaString.append("[null]");
            }
            filaPrioridade.enqueue(filaPrioridade.dequeue()); // Reordena a fila
        }

        String message = String.format(
                "Ação: %s, Nó: %s, Código da Ordem de Serviço: %d, Fila de Cache: %s%n",
                acao,
                no != null ? no.toString() : "null",
                no != null && no.getServiceOrder() != null ? no.getServiceOrder().getCodigoServico() : -1,
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
