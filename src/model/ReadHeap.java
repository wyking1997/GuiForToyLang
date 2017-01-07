package model;

import utils.MyIDictionary;
import utils.MyIHeap;

/**
 * Created by Wyking on 11/16/2016.
 */
public class ReadHeap extends Exp {

    // the name of the variable witch we want to read from
    String var_name;
    Exp exp;

    public ReadHeap(String var_name) {
        this.var_name = var_name;
        this.exp = null;
    }
    public ReadHeap(Exp exp) {
        this.exp = exp;
        this.var_name = null;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> hp) throws Exception {
        if (this.exp == null) {
            Integer var = tbl.get(var_name);
            return hp.get(var);
        } else return hp.get(exp.eval(tbl, hp));
    }

    @Override
    public String toString(){
        if (exp == null)
            return "ReadHeap(" + var_name + ")";
        else return  "ReadHeap(" + exp.toString() + ")";
    }
}
