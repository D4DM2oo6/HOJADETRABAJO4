public abstract class AbstrackStack<T> implements IStack<T> {
    protected int size;
    
    @Override
    public boolean isEmpty () {
        return this.size == 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
}
