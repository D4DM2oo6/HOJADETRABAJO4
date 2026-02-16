public abstract class AbstractList<T> implements IList<T> {

    protected int size;

    public AbstractList() {
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
}