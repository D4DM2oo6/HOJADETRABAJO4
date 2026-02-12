public interface  StackVector<T> {
    void push (T value);
    T pop ();
    T peek ();
    boolean isEmpty ();
}
