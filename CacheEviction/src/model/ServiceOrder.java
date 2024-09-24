package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceOrder {
    static int count = 0000;
    int codigoServico;
    String nome;
    String descricao;

    LocalDateTime data = LocalDateTime.now();
    DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");

    String hora;

    public ServiceOrder(String nome, String descricao){
        this.codigoServico = count;
        this.nome = nome;
        this.descricao = descricao;
        this.hora = horaFormatada.format(data);
        increCount();
    }

    public static void increCount(){
        count++;
    }

    public static int getCount() {
        int c = count;
        ServiceOrder.increCount();
        return c;
    }

    public int getCodigoServico(){
        return codigoServico;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getHora(){
        return hora;
    }

    @Override
    public String toString() {
        return "ServiceOrder [Codigo de Serviço=" + codigoServico + ", Nome=" + nome + ", Descrição=" + descricao
                + ", Hora=" + hora + "]";
    }

    

}
