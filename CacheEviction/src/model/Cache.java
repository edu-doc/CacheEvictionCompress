public class ListaAutoAjustavel<T> {
    private Object[] elementos;
    private int tamanho;
    private int capacidade;

    public ListaAutoAjustavel(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new Object[capacidade];
        this.tamanho = 0;
    }

    // Método para adicionar um elemento no início da lista
    public void adicionarNoInicio(T elemento) {
        if (tamanho < capacidade) {
            // Desloca os elementos para a direita
            for (int i = tamanho; i > 0; i--) {
                elementos[i] = elementos[i - 1];
            }
            // Adiciona o novo elemento na posição 0
            elementos[0] = elemento;
            tamanho++;
        } else {
            System.out.println("Capacidade máxima atingida. Não é possível adicionar mais elementos.");
        }
    }

    // Outros métodos já existentes na sua classe...
    
    public void adicionar(T elemento) {
        if (tamanho < capacidade) {
            elementos[tamanho] = elemento;
            tamanho++;
        } else {
            System.out.println("Capacidade máxima atingida. Não é possível adicionar mais elementos.");
        }
    }

    public T obter(int indice) {
        if (indice >= 0 && indice < tamanho) {
            return (T) elementos[indice];
        }
        return null;
    }

    public void remover(int indice) {
        if (indice >= 0 && indice < tamanho) {
            for (int i = indice; i < tamanho - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            elementos[tamanho - 1] = null; // Remove a referência do último elemento
            tamanho--;
        }
    }

    public int tamanho() {
        return tamanho;
    }
}