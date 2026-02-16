import java.util.Vector;

public class StackVector<T> extends AbstractStack<T> {

    private final Vector<T> data;

    public StackVector() {
        this.data = new Vector<>();
    }

    @Override
    public void push(T item) {
        data.add(item);
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException("La pila está vacía (pop).");
        size--;
        return data.remove(data.size() - 1);
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException("La pila está vacía (peek).");
        return data.get(data.size() - 1);
    }

    @Override
    protected void clearInternal() {
        data.clear();
    }
}