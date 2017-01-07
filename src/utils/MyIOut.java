package utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wyking on 10/23/2016.
 */
public interface MyIOut<T> extends Serializable {
    public boolean add(T e);
    public void clear();
    public T get(int index) throws IndexOutOfBoundsException;
    public boolean isEmpty();
    public void remove(int index) throws IndexOutOfBoundsException;
    public List<T> getOutputList();

    public String toString();
}
