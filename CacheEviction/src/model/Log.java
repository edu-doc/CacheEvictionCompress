package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {

    private static String path = "src/log.txt";

    public void log(String acao, No no, int cod, ArrayList<No>[] tabela) {

        StringBuilder tabelaString = new StringBuilder();
        
        // Converte a tabela hash para uma representação de string
        for (ArrayList<No> lista : tabela) {
            for (No node : lista) {
                tabelaString.append("[").append(node.order.codigoServico).append("]");
            }
        }

        String tabelaF = tabelaString.toString();

        String message = String.format(
                "Ação: %s, Próximo Nó: %s, Código da Ordem de Serviço: %d, Tabela de Hash: %s%n", 
                acao, no != null ? no.toString() : "null", cod, tabelaF);
        
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
