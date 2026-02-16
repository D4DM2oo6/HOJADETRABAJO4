public class NodoSimple<T> {
    T data;
    NodoSimple<T> next;

    public NodoSimple(T data) {
        this.data = data;
        this.next = null;
    }
}