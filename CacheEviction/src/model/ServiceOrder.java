import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceOrder implements Comparable<ServiceOrder> {
    static int count = 0;  // Começa de 0
    int codigoServico;
    String nome;
    String descricao;
    LocalDateTime data = LocalDateTime.now();
    static DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
    String hora;

    // Arrays para armazenar caracteres e códigos de Huffman
    private char[] caracteres; // Array de caracteres
    private String[] codigos;  // Array de códigos Huffman

    public char[] getCaracteres() {
        return caracteres;
    }

    public String[] getCodigos() {
        return codigos;
    }

    // Construtor
    public ServiceOrder(String nome, String descricao) {
        this.codigoServico = getNextCodigo();  // Gera o próximo código formatado
        this.nome = nome;
        this.descricao = descricao;
        this.hora = horaFormatada.format(data);
    }

    public ServiceOrder(){

    }

    // Incrementa o contador e retorna o código de serviço
    public static int getNextCodigo() {
        return count++;  // Incrementa e retorna
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

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getHora() {
        return hora;
    }

    // Método para inicializar os códigos Huffman
    public void inicializarCodigos(char[] caracteres, String[] codigos) {
        this.caracteres = caracteres;
        this.codigos = codigos;
    }

    public FrequenciaResultado obterFrequencias() {
        int[] frequencias = new int[256];
        String todosOsAtributos = "Código de Serviço:" + getCodigoServico() + " Nome:" + nome + " Descrição:" + descricao + " Hora" + hora;
    
        for (char c : todosOsAtributos.toCharArray()) {
            frequencias[c]++;
        }
    
        int uniqueCount = 0;
        for (int freq : frequencias) {
            if (freq > 0) {
                uniqueCount++;
            }
        }
    
        char[] caracteres = new char[uniqueCount];
        int[] freqArray = new int[uniqueCount];
    
        int index = 0;
        for (int i = 0; i < frequencias.length; i++) {
            if (frequencias[i] > 0) {
                caracteres[index] = (char) i;
                freqArray[index] = frequencias[i];
                index++;
            }
        }
    
        return new FrequenciaResultado(caracteres, freqArray); // Retorna a classe personalizada
    }

    public String comprimir() {
        StringBuilder codigoHuffman = new StringBuilder();
    
        // Combina todos os atributos da ServiceOrder em uma única string com etiquetas
        String texto = "Código de Serviço:" + getCodigoServico() + " Nome:" + nome + " Descrição:" + descricao + " Hora:" + hora;
    
        // Usa o método compressor para gerar a string comprimida
        for (int i = 0; i < texto.length(); i++) {
            char caractereAtual = texto.charAt(i);
            boolean encontrado = false;
    
            // Encontra o código Huffman para o caractere atual
            for (int j = 0; j < caracteres.length; j++) {
                if (caracteres[j] == caractereAtual) {
                    codigoHuffman.append(codigos[j]);
                    encontrado = true;
                    break;
                }
            }
    
            // Se não encontrou, adicione uma representação alternativa (opcional)
            if (!encontrado) {
                System.out.println("Caractere não encontrado: " + caractereAtual);
            }
        }
    
        return codigoHuffman.toString();
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public static ServiceOrder fromString(String serviceOrderString) {
        // Divide a string nos diferentes campos baseados nos delimitadores
        String[] partes = serviceOrderString.split(" Nome:| Descrição:| Hora:");
        
        // Extrai os valores de cada campo
        String codigoServicoStr = partes[0].replace("Código de Serviço:", "").trim();
        
        int codigoServico = (int) Double.parseDouble(codigoServicoStr);

        String nome = partes[1].trim();  // Nome
        
        String descricao = partes[2].trim();  // Descrição
        
        String hora = partes[3].trim();  // Hora
    
        // Cria uma nova instância de ServiceOrder
        ServiceOrder serviceOrder = new ServiceOrder(nome, descricao);
        
        // Atualiza o código de serviço para o que foi lido da string
        serviceOrder.codigoServico = codigoServico;  
        
        // Atualiza a hora
        serviceOrder.hora = hora;
        
        return serviceOrder;
    }    

    @Override
    public int compareTo(ServiceOrder outro) {
        return Integer.compare(this.codigoServico, outro.codigoServico);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigoServico;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceOrder other = (ServiceOrder) obj;
        if (codigoServico != other.codigoServico)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServiceOrder [Codigo de Serviço=" + getCodigoServico() + ", Nome=" + nome + ", Descrição="
                + descricao + ", Hora=" + hora + "]";
    }
}