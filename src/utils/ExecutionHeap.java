package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Wyking on 11/9/2016.
 */
public class ExecutionHeap<T> implements MyIHeap<T> {

    static int nextFreeAddr = 1;

    Map<Integer,T> map;

    public ExecutionHeap(){
        map = new HashMap<Integer, T>();
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Integer key) {
        return map.containsKey(key);
    }

    public boolean containsValue(T value) {
        return map.containsValue(value);
    }

    public T get(Integer key) throws Exception {
        try{
            return map.get(key);
        }
        catch (Exception e){
            throw new MyException("Unexisting adress: " + key + " !");
        }
    }

    @Override
    public Map<Integer, T> getContent() {
        return map;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int put(T value) {
        map.put(nextFreeAddr, value);
        return nextFreeAddr++;
    }

    public void put(int pos, T value) {
        map.put(pos,value);
    }

    @Override
    public void setContent(Map<Integer, T> newMap) {
        map.clear();
        map.putAll(newMap);
    }

    @Override
    public Map<Integer, T> getHeap() {
        return this.map;
    }

    public String toString(){
        String res = "Heap:\n";
        Iterator<Integer> it = map.keySet().iterator();

        while (it.hasNext())
            try {
                int s = it.next();
                res += s + "=" + this.get(s) + "\n";
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        return res + "::::::::::::::::\n";
    }
}
