import java.util.ArrayList;

public class FilaPrioridade<T extends Comparable<T>> {
    private ArrayList<T> heap; // Usamos um ArrayList para armazenar os elementos do heap

    public FilaPrioridade(int n) {
        this.heap = new ArrayList<>(n);
    }

    // Método para inserir um novo elemento na fila de prioridade
    public void inserir(T elemento) {
        heap.add(elemento); // Adiciona o elemento ao final do heap
        subir(heap.size() - 1); // Reorganiza o heap
    }

    // Método para remover e retornar o elemento de maior prioridade (o menor)
    public T remover() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }
        T raiz = heap.get(0); // O elemento de maior prioridade
        T ultimo = heap.remove(heap.size() - 1); // Remove o último elemento
        if (!heap.isEmpty()) {
            heap.set(0, ultimo); // Coloca o último elemento na raiz
            descer(0); // Reorganiza o heap
        }
        return raiz; // Retorna o elemento removido
    }

    // Retorna o tamanho da fila de prioridade
    public int size() {
        return heap.size();
    }

    // Retorna o elemento de maior prioridade sem remover (peek)
    public T peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }
        return heap.get(0); // O elemento de maior prioridade
    }

    // Método para imprimir todos os elementos da fila de prioridade
    public void imprimir() {
        if (heap.isEmpty()) {
            System.out.println("A fila de prioridade está vazia.");
            return;
        }
        System.out.println("Elementos da fila de prioridade:");
        for (T elemento : heap) {
            System.out.println(elemento);
        }
    }

    // Reorganiza o heap para cima
    private void subir(int indice) {
        while (indice > 0) {
            int pai = (indice - 1) / 2; // Índice do pai
            if (heap.get(indice).compareTo(heap.get(pai)) >= 0) {
                break; // O heap está organizado
            }
            // Troca os elementos
            T temp = heap.get(indice);
            heap.set(indice, heap.get(pai));
            heap.set(pai, temp);
            indice = pai; // Continua para o pai
        }
    }

    // Reorganiza o heap para baixo
    private void descer(int indice) {
        int tamanho = heap.size();
        while (indice < tamanho) {
            int menor = indice;
            int esquerda = 2 * indice + 1; // Índice do filho à esquerda
            int direita = 2 * indice + 2; // Índice do filho à direita

            // Verifica o filho à esquerda
            if (esquerda < tamanho && heap.get(esquerda).compareTo(heap.get(menor)) < 0) {
                menor = esquerda;
            }

            // Verifica o filho à direita
            if (direita < tamanho && heap.get(direita).compareTo(heap.get(menor)) < 0) {
                menor = direita;
            }

            if (menor == indice) {
                break; // O heap está organizado
            }

            // Troca os elementos
            T temp = heap.get(indice);
            heap.set(indice, heap.get(menor));
            heap.set(menor, temp);
            indice = menor; // Continua para o menor filho
        }
    }
}
