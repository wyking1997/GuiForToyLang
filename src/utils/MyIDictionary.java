package utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Wyking on 10/23/2016.
 */
public interface MyIDictionary<S,I> extends Serializable {
    void clear();
    boolean	containsKey(S key);
    boolean	containsValue(I value);
    I get(S key) throws Exception;
    ArrayList<I> getContent();
    boolean isEmpty();
    void put(S key, I value);
    MyIDictionary<S,I> deepCopy();

    String toString();
}
