class Huffman {
    No raiz;
    char[] caracteres; // Vetor de caracteres usados para gerar a árvore

    // Método para construir a árvore de Huffman
    void construirArvore(int n, char[] arrayCaracteres, int[] arrayFrequencias) {
        FilaPrioridade<No> heapMinimo = new FilaPrioridade(n);

        // Inicializando os vetores de caracteres
        this.caracteres = arrayCaracteres;

        for (int i = 0; i < n; i++) {
            No no = new No();
            no.caractere = arrayCaracteres[i];
            no.freq = arrayFrequencias[i];
            no.esquerda = null;
            no.direita = null;
            heapMinimo.inserir(no);
        }

        while (heapMinimo.size() > 1) {
            No x = heapMinimo.remover();
            No y = heapMinimo.remover();
            No z = new No();
            z.freq = x.freq + y.freq;
            z.caractere = '-'; // Caractere especial para nós internos
            z.esquerda = x;
            z.direita = y;
            heapMinimo.inserir(z);
        }

        // A raiz é o último nó restante na fila
        raiz = heapMinimo.remover();
    }

    // Método para gerar os códigos de Huffman e retornar um vetor de códigos
    public String[] gerarCodigos() {
        String[] codigos = new String[caracteres.length];  // Vetor para armazenar os códigos
        gerarCodigosRecursivo(raiz, "", codigos);  // Chama o método recursivo para preencher o vetor
        return codigos;  // Retorna o vetor de códigos
    }

    // Método recursivo para gerar os códigos de Huffman
    private void gerarCodigosRecursivo(No no, String codigoAtual, String[] codigos) {
        if (no.esquerda == null && no.direita == null && isCharValido(no.caractere)) {
            // Quando encontrar uma folha, armazena o código no vetor de códigos
            for (int i = 0; i < caracteres.length; i++) {
                if (caracteres[i] == no.caractere) {
                    codigos[i] = codigoAtual;
                    break;
                }
            }
            return;
        }
        // Continua percorrendo a árvore
        if (no.esquerda != null) {
            gerarCodigosRecursivo(no.esquerda, codigoAtual + "0", codigos);
        }
        if (no.direita != null) {
            gerarCodigosRecursivo(no.direita, codigoAtual + "1", codigos);
        }
    }

    private boolean isCharValido(char c) {
        return (Character.isLetterOrDigit(c) | c == ' ' | c == ':');
    }

    // Método para descomprimir uma string usando a árvore de Huffman
    public String descomprimir(String codigo) {
        StringBuilder textoDescomprimido = new StringBuilder();
        No atual = raiz;

        for (int i = 0; i < codigo.length(); i++) {
            char bit = codigo.charAt(i);

            // Navega pela árvore de acordo com o bit
            if (bit == '0') {
                atual = atual.esquerda;
            } else if (bit == '1') {
                atual = atual.direita;
            }

            // Quando chega a uma folha, append o caractere correspondente
            if (atual.esquerda == null && atual.direita == null) {
                textoDescomprimido.append(atual.caractere);
                atual = raiz;  // Volta para a raiz para decodificar o próximo caractere
            }
        }

        return textoDescomprimido.toString();
    }

    // Método auxiliar para imprimir os códigos de Huffman
    void imprimirCodigos(String[] codigos) {
        for (int i = 0; i < caracteres.length; i++) {
            System.out.println(caracteres[i] + ": " + codigos[i]);
        }
    }
}
