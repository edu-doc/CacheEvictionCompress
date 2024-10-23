import java.util.ArrayList;
import java.util.Collections;

public class FilaPrioridade<T> {
    private final ArrayList<Elemento<T>> elementos;

    private static class Elemento<T> {
        T valor;
        int prioridade;

        public Elemento(T valor, int prioridade) {
            this.valor = valor;
            this.prioridade = prioridade;
        }
    }

    public FilaPrioridade(int tamanho) {
        elementos = new ArrayList<>(tamanho);
    }

    public void adicionar(T valor, int prioridade) {
        Elemento<T> novoElemento = new Elemento<>(valor, prioridade);
        elementos.add(novoElemento);
        Collections.sort(elementos, (a, b) -> Integer.compare(b.prioridade, a.prioridade));
    }

    public T remover() {
        if (estaVazia()) {
            throw new RuntimeException("A fila está vazia!");
        }
        return elementos.remove(elementos.size() - 1).valor;
    }

    public boolean estaVazia() {
        return elementos.isEmpty();
    }

    public int tamanho() {
        return elementos.size();
    }
}