package array;

public interface List<T> {

    void add(T option);
    void remove(int index);
    T get(int index);
    public T[] getAll();
}
