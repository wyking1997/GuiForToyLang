package model;

import utils.MyIOut;

/**
 * Created by Wyking on 10/29/2016.
 */
public class PrintStm implements IStm {
    Exp exp;

    public PrintStm(Exp ex){
        exp = ex;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {

        MyIOut<Integer> out = state.getExOut();
        int e = exp.eval(state.getExDict(), state.getExHeap());
        out.add(e);
        return null;
    }

    @Override
    public String toString(){
        return "print(" + exp.toString() + ")";
    }
}
