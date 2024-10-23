import java.util.Arrays;

public class ListaAutoAjustavel<T> {
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public ListaAutoAjustavel(int cap) {
        array = (T[]) new Object[cap];
        size = 0;
    }

    public void add(T element) {
        if (size == array.length) {
            resize(array.length * 2); // Dobrar o tamanho do array
        }
        array[size++] = element;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
    
        T removedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null; // Limpa a referência
        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2); // Reduzir o tamanho do array se necessário
        }
        return removedElement; // Retorna o elemento removido
    }
    

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}