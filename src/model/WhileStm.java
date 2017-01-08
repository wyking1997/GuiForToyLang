package model;

/**
 * Created by Wyking on 11/17/2016.
 */
public class WhileStm implements IStm {

    Exp condition;
    IStm stm;

    public WhileStm(Exp condition, IStm stm) {
        this.condition = condition;
        this.stm = stm;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if (condition.eval(state.getExDict(),state.getExHeap()) == 0)
            return null;
        state.getExStack().push(this);
        state.getExStack().push(stm);
        return null;
    }

    @Override
    public String toString(){
        return "while(" + condition.toString() + "){" + stm.toString() + "}";
    }
}
