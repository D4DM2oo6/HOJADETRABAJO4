public interface IList<T> {
    void addFirst(T item);
    T removeFirst();      // si está vacía, lanza RuntimeException
    T getFirst();         // si está vacía, lanza RuntimeException
    boolean isEmpty();
    int size();
}