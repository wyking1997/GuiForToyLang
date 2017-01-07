package utils;

import java.util.*;

/**
 * Created by Wyking on 11/2/2016.
 */
public class ExecutionFileTable<I,T> implements MyIFileTable<I,T> {
    Map<I,T> map;

    public ExecutionFileTable() {
        map = new HashMap<I, T>();
    }

    public Set<I> getKeys(){
        return map.keySet();
    }

    public boolean containsKey(I key){
        return (map.containsKey(key));
    }

    public T get(I key){
        return map.get(key);
    }

    public void add(I key, T el){
        map.put(key, el);
    }

    public void remove(I key){
        map.remove(key);
    }

    @Override
    public Map<I, T> getFileTable() {
        return this.map;
    }

    public String toString(){
        String res = "FileTable\n";
        for (I i : map.keySet()){
            res = res + i + " --> " + map.get(i).toString() + "\n";
        }
        return res + "::::::::::::::::\n";
    }

}
