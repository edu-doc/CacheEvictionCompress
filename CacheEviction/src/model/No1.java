class No1 implements Comparable<No1> {
    char caractere;
    int freq;
    No1 esquerda, direita;

    public No1(char caractere, int freq) {
        this.caractere = caractere;
        this.freq = freq;
        this.esquerda = null;
        this.direita = null;
    }

    public No1(){}

    @Override
    public int compareTo(No1 outro) {
        return Integer.compare(this.freq, outro.freq); // Compara pela frequência
    }
}
