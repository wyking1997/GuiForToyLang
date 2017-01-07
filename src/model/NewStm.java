package model;

import utils.MyIDictionary;
import utils.MyIHeap;

/**
 * Created by Wyking on 11/9/2016.
 */
public class NewStm implements IStm {

    String var_name;
    Exp expression;

    public NewStm(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIHeap<Integer> heap = state.getExHeap();
        MyIDictionary<String, Integer> simbols = state.getExDict();

        int exp = expression.eval(simbols, state.getExHeap());
        int addr = heap.put(exp);
        simbols.put(var_name,addr);

        return null;
    }

    @Override
    public String toString(){
        return "new(" + var_name + "," + expression + ")";
    }
}
