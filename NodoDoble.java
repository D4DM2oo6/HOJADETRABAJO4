public class NodoDoble<T> {
    T data;
    NodoDoble<T> next;
    NodoDoble<T> prev;

    public NodoDoble(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}