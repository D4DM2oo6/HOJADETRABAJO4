public abstract class AbstractStack<T> implements IStack<T> {

    protected int size;

    public AbstractStack() {
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        clearInternal();
    }

    protected abstract void clearInternal();
}