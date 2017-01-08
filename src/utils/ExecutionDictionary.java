package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Wyking on 10/23/2016.
 */
public class ExecutionDictionary<S,I> implements MyIDictionary<S,I> {

    Map<S,I> map;
    public ExecutionDictionary(){

        map = new HashMap<S, I>();
    }

    public void clear(){

        map.clear();
    };

    public boolean containsKey(S key){

        return map.containsKey(key);
    };

    public boolean containsValue(I value){

        return map.containsKey(value);
    };

    public I get(S key) throws Exception{

        if(this.containsKey(key))
            return map.get(key);
        throw new MyException("Unknown variable: '" + key.toString() + "'");
    }

    @Override
    public ArrayList<I> getContent() {
        ArrayList<I> arr = new ArrayList<>();
        for (S key : map.keySet())
            arr.add(map.get(key));
        return arr;
    }

    ;

    public boolean isEmpty(){

        return map.isEmpty();
    };

    public void put(S key, I value){

        map.put(key, value);
    }

    @Override
    public MyIDictionary<S, I> deepCopy() {
        ExecutionDictionary<S,I> nExDict = new ExecutionDictionary<S, I>();
        for (S key : map.keySet()){
            nExDict.put(key, map.get(key));
        }
        return nExDict;
    }

    ;

    public String toString(){
        String res = "SymbolsTable:\n";
        Iterator<S> it = map.keySet().iterator();

        while (it.hasNext())
            try {
                S s = it.next();
                res += s + "=" + this.get(s) + "\n";
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        return res + "::::::::::::::::\n";
    }

    @Override
    public Map<S,I> getTable() {
        return this.map;
    }
}
