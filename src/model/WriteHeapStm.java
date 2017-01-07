package model;

import utils.MyIDictionary;
import utils.MyIHeap;

/**
 * Created by Wyking on 11/16/2016.
 */
public class WriteHeapStm implements IStm {

    String var_name;
    Exp expression;

    public WriteHeapStm(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, Integer> sb = state.getExDict();
        MyIHeap<Integer> hp = state.getExHeap();

        int sbVal = sb.get(var_name);
        hp.put(sbVal,expression.eval(sb,hp));

        return null;
    }

    @Override
    public String toString(){
        return "WriteHeapStm(" + var_name + "," + expression.toString() + ")";
    }
}
