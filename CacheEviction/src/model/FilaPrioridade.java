import java.util.ArrayList;
import java.util.List;

class FilaPrioridade<T extends Comparable<T>> {
    private List<T> heap;

    // Construtor
    public FilaPrioridade(int cap) {
        heap = new ArrayList<T>(cap);
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Retorna o tamanho da fila
    public int size() {
        return heap.size();
    }

    // Retorna o menor elemento (maior prioridade) sem removê-lo
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    // Insere um elemento na fila
    public void enqueue(T item) {
        heap.add(item);  // Adiciona o novo elemento no final da lista
        subir(heap.size() - 1);  // Corrige a posição para manter a propriedade da heap
    }

    // Remove e retorna o menor elemento (maior prioridade)
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila de prioridade está vazia");
        }

        T raiz = heap.get(0);  // Elemento com maior prioridade

        // Substitui a raiz pelo último elemento e ajusta a heap
        T ultimoElemento = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, ultimoElemento);
            descer(0);
        }

        return raiz;
    }

    // Função para subir um elemento na heap (corrigir a posição após inserção)
    private void subir(int indice) {
        int pai = (indice - 1) / 2;
        // Se o elemento for menor que o pai, troca-os
        while (indice > 0 && heap.get(indice).compareTo(heap.get(pai)) < 0) {
            trocar(indice, pai);
            indice = pai;
            pai = (indice - 1) / 2;
        }
    }

    // Função para descer um elemento na heap (corrigir a posição após remoção)
    private void descer(int indice) {
        int tamanho = heap.size();
        int menor = indice;

        int esquerda = 2 * indice + 1;
        int direita = 2 * indice + 2;

        // Verifica se o filho esquerdo tem menor valor
        if (esquerda < tamanho && heap.get(esquerda).compareTo(heap.get(menor)) < 0) {
            menor = esquerda;
        }

        // Verifica se o filho direito tem menor valor
        if (direita < tamanho && heap.get(direita).compareTo(heap.get(menor)) < 0) {
            menor = direita;
        }

        // Se o menor elemento não for o pai, troca-os
        if (menor != indice) {
            trocar(indice, menor);
            descer(menor);  // Continua ajustando a heap
        }
    }

    // Função para trocar dois elementos na heap
    private void trocar(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    // Imprime a fila de prioridade (heap)
    public void printQueue() {
        System.out.println(heap);
    }
}
