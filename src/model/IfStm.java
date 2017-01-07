package model;

/**
 * Created by Wyking on 10/29/2016.
 */
public class IfStm implements IStm {

    Exp exp;
    IStm trueS;
    IStm falseS;

    public IfStm(Exp verified_exp, IStm if_ok, IStm if_not_ok){
        exp = verified_exp;
        trueS = if_ok;
        falseS = if_not_ok;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if (exp.eval(state.getExDict(), state.getExHeap()) != 0)
            trueS.execute(state);
        else
            falseS.execute(state);
        return null;
    }

    @Override
    public String toString(){
        return "if (" + exp.toString() + ") then " + trueS.toString() + " else " + falseS.toString();
    }
}
