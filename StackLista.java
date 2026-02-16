public class StackLista<T> extends AbstractStack<T> {

    private final IList<T> lista;

    public StackLista(IList<T> lista) {
        this.lista = lista;
        this.size = 0;
    }

    @Override
    public void push(T item) {
        lista.addFirst(item);
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException("La pila está vacía (pop).");
        size--;
        return lista.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException("La pila está vacía (peek).");
        return lista.getFirst();
    }

    @Override
    protected void clearInternal() {
        while (!lista.isEmpty()) {
            lista.removeFirst();
        }
    }
}