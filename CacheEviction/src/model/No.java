class No implements Comparable<No> {
    char caractere;
    int freq;
    No esquerda, direita;

    public No(char caractere, int freq) {
        this.caractere = caractere;
        this.freq = freq;
        this.esquerda = null;
        this.direita = null;
    }

    public No(){}

    @Override
    public int compareTo(No outro) {
        return Integer.compare(this.freq, outro.freq); // Compara pela frequência
    }
}
