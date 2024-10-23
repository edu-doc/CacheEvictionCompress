public class ListaAutoAjustavel<T> {
    private T[] elementos;
    private int tamanho;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public ListaAutoAjustavel(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.elementos = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    public void adicionar(T elemento) {
        if (tamanho >= capacidade) {
            redimensionar();
        }
        elementos[tamanho++] = elemento;
    }

    public T obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        return elementos[indice];
    }

    public void remover(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }

        for (int i = indice; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        elementos[--tamanho] = null; // Remove a referência do último elemento
    }

    public int tamanho() {
        return tamanho;
    }

    private void redimensionar() {
        capacidade *= 2; // Dobra a capacidade
        @SuppressWarnings("unchecked")
        T[] novoArray = (T[]) new Object[capacidade];
        System.arraycopy(elementos, 0, novoArray, 0, tamanho);
        elementos = novoArray;
    }
}
