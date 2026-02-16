public class DoublyLinkedList<T> extends AbstractList<T> {

    private NodoDoble<T> head;
    private NodoDoble<T> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void addFirst(T item) {
        NodoDoble<T> nuevo = new NodoDoble<>(item);

        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            nuevo.next = head;
            head.prev = nuevo;
            head = nuevo;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Lista vacía (removeFirst).");

        T val = head.data;

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return val;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new RuntimeException("Lista vacía (getFirst).");
        return head.data;
    }
}