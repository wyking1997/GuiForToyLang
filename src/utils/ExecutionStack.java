package utils;

import java.util.Stack;

/**
 * Created by Wyking on 10/23/2016.
 */
public class ExecutionStack<T> implements MyIStack<T> {

    Stack<T> stack;

    public ExecutionStack(){

        stack = new Stack<T>();
    }

    @Override
    public void push(T el){

        stack.push(el);
    };

    @Override
    public T pop(){

        return stack.pop();
    };

    @Override
    public boolean isEmpty(){

        return (stack.isEmpty());
    };

    @Override
    public String toString(){

        String s = "ExecutionStack:\n";
        Stack<T> s2 = (Stack<T>) stack.clone();
        while (!s2.isEmpty()){
            T el = s2.pop();
            s += el.toString() + "\n";
        }
        return s + "::::::::::::::::\n";
    };
}
