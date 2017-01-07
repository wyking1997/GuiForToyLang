package utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Wyking on 11/9/2016.
 */
public interface MyIHeap<T> extends Serializable {

    void clear();
    boolean containsKey(Integer key);
    boolean containsValue(T value);
    T get(Integer key) throws Exception;
    Map<Integer,T> getContent();
    boolean isEmpty();

    //returns the addres of the new element
    int put(T value);
    void put(int pos, T value);

    void setContent(Map<Integer,T> newMap);
    Map<Integer, T> getHeap();

    String toString();
}
