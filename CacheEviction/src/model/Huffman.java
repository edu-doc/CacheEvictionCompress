class Huffman {
    No raiz;

    // Função para construir a árvore de Huffman com ordens de serviço
    void construirArvore(int n, ServiceOrder[] ordens, int[] arrayFrequencias) {
        // Fila de prioridade mínima (min-heap)
        FilaPrioridade<No> heapMinimo = new FilaPrioridade<>(n);
    
        // Inserir cada ordem de serviço e sua frequência na fila de prioridade
        for (int i = 0; i < n; i++) {
            No no = new No(ordens[i], arrayFrequencias[i]);
            heapMinimo.enqueue(no);
        }
    
        raiz = null;
    
        // Construir a árvore de Huffman
        while (heapMinimo.size() > 1) {
            No x = heapMinimo.dequeue();  // Nó com menor frequência
            No y = heapMinimo.dequeue();  // Segundo nó com menor frequência
    
            // Criar um novo nó z combinando x e y
            No z = new No(null, x.freq + y.freq);  // Nó intermediário não contém ServiceOrder
            z.esquerda = x;
            z.direita = y;
    
            raiz = z;  // Atualiza a raiz para o último nó criado
            heapMinimo.enqueue(z);  // Insere o novo nó na fila de prioridade
        }
    
        // No final, se heapMinimo contém um único nó, ele será a raiz da árvore
        if (heapMinimo.size() == 1) {
            raiz = heapMinimo.dequeue(); // Atribui a raiz se apenas um nó restar
        }
    }
    

    // Função para imprimir os códigos de Huffman junto com os dados da ordem de serviço
    void imprimirCodigo(No no, String s) {
        // Se for uma folha (que contém uma ServiceOrder), exibe o código e os dados da ordem de serviço
        if (no.esquerda == null && no.direita == null && no.getServiceOrder() != null) {
            System.out.println(no.getServiceOrder() + ": " + s);
            return;
        }

        // Recursivamente chamar para os nós esquerdo e direito
        imprimirCodigo(no.esquerda, s + "0");
        imprimirCodigo(no.direita, s + "1");
    }
}