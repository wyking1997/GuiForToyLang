package model;

import utils.MyIDictionary;
import utils.MyIHeap;

/**
 * Created by Wyking on 10/29/2016.
 */
public class VarExp extends Exp {

    String nm;

    public VarExp(String name){
        nm = name;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> hp) throws Exception {
        return tbl.get(nm);
    }

    @Override
    public String toString(){
        return nm;
    }
}
