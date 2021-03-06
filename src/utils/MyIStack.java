package utils;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Wyking on 10/23/2016.
 */
public interface MyIStack<T> extends Serializable {
    public void push(T el);
    public T pop();
    public boolean isEmpty();

    public String toString();
    Stack<T> getStack();
}
