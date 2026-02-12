
import java.util.Vector;

public class  StackVector<T> extends AbstrackStack<T> {
    
    private Vector<T> vector;

    public StackVector() {
        this.vector = new Vector<>();
    }

    @Override
    public void push(T value) {
        data.add (item);
        size++;
    }

     @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T value = data.remove(data.size() - 1);
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return data.get(data.size() - 1);
    }


}
