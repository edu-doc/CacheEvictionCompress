import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceOrder {
    static int count = 0;  // Começa de 0
    int codigoServico;
    String nome;
    String descricao;
    
    LocalDateTime data = LocalDateTime.now();
    static DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");

    String hora;

    // Construtor
    public ServiceOrder(String nome, String descricao) {
        this.codigoServico = getNextCodigo();  // Gera o próximo código formatado
        this.nome = nome;
        this.descricao = descricao;
        this.hora = horaFormatada.format(data);
    }

    public ServiceOrder(int codigoServico, String descricao, String hora, String nome) {
        this.codigoServico = codigoServico;
        this.descricao = descricao;
        this.hora = hora;
        this.nome = nome;
    }

    public ServiceOrder(){

    }

    // Incrementa o contador e retorna o código de serviço formatado
    public static int getNextCodigo() {
        return ++count;  // Incrementa e retorna
    }

    // Retorna o código do serviço com zeros à esquerda
    public String getCodigoServicoFormatado() {
        return String.format("%04d", codigoServico);  // Formata com 4 dígitos (0000)
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHora() {
        return hora;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "ServiceOrder [Codigo de Serviço=" + getCodigoServicoFormatado() + ", Nome=" + nome + ", Descrição="
                + descricao + ", Hora=" + hora + "]";
    }

}