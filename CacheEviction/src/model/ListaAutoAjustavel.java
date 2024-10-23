public class ListaAutoAjustavel {
    private Object[] elementos;
    private int tamanho;

    // Construtor
    public ListaAutoAjustavel(int cap) {
        elementos = new Object[cap];
        tamanho = 0;
    }

    // Método para adicionar um elemento na primeira posição
    public void adicionar(Object elemento) {
        if (tamanho == elementos.length) {
            aumentarCapacidade();
        }

        // Move todos os elementos para a direita
        System.arraycopy(elementos, 0, elementos, 1, tamanho);
        elementos[0] = elemento; // Insere o novo elemento na primeira posição
        tamanho++;
    }

    // Método para aumentar a capacidade do array
    private void aumentarCapacidade() {
        int novaCapacidade = elementos.length * 2;
        Object[] novoArray = new Object[novaCapacidade];
        System.arraycopy(elementos, 0, novoArray, 0, elementos.length);
        elementos = novoArray;
    }

    // Método para remover o elemento mais antigo (o último elemento na lista)
    public boolean remover() {
        if (tamanho == 0) {
            return false; // Se a lista estiver vazia, não há nada para remover
        }
        elementos[--tamanho] = null; // Remove a referência ao último elemento
        return true;
    }

    // Método para remover um elemento pelo índice
    public boolean remover(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora do limite: " + indice);
        }

        // Move os elementos para a esquerda a partir do índice
        System.arraycopy(elementos, indice + 1, elementos, indice, tamanho - indice - 1);
        elementos[--tamanho] = null; // Remove a referência ao último elemento
        return true; // Retorna true se a remoção for bem-sucedida
    }

    // Método para obter o tamanho da lista
    public int tamanho() {
        return tamanho;
    }

    // Método para obter um elemento por índice
    public Object obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora do limite: " + indice);
        }
        return elementos[indice];
    }

    // Método para imprimir os elementos da lista
    public void imprimir() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }
}