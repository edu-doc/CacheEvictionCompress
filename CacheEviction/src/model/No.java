// Classe que representa um nó da árvore de Huffman
class No implements Comparable<No> {
    ServiceOrder serviceOrder;  // Instância de ServiceOrder no nó
    int freq;
    No esquerda, direita;

    // Construtor que recebe uma ordem de serviço e uma frequência
    public No(ServiceOrder serviceOrder, int freq) {
        this.serviceOrder = serviceOrder;
        this.freq = freq;
        this.esquerda = null;
        this.direita = null;
    }

    

    // Implementa o método compareTo para comparar nós com base na frequência
    @Override
    public int compareTo(No outro) {
        return this.freq - outro.freq;  // Retorna negativo se this.freq < outro.freq (min-heap)
    }

    // Retorna a ordem de serviço contida no nó
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public int getFreq() {
        return freq;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public No getDireita() {
        return direita;
    }


}
