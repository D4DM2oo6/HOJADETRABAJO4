public class SinglyLinkedList<T> extends AbstractList<T> {

    private NodoSimple<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    @Override
    public void addFirst(T item) {
        NodoSimple<T> nuevo = new NodoSimple<>(item);
        nuevo.next = head;
        head = nuevo;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Lista vacía (removeFirst).");
        T val = head.data;
        head = head.next;
        size--;
        return val;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new RuntimeException("Lista vacía (getFirst).");
        return head.data;
    }
}