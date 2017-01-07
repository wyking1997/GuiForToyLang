package model;

import utils.*;

import java.io.BufferedReader;

/**
 * Created by Marius on 12/12/2016.
 */
public class ForkStatement implements IStm {

    IStm stm;

    public ForkStatement(IStm stm) {
        this.stm = stm;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStm> nStack = new ExecutionStack<IStm>();
        MyIDictionary<String, Integer> nExDict = state.getExDict().deepCopy();
        MyIHeap<Integer> nHeap = state.getExHeap();
        MyIFileTable<Integer, Pair<String, BufferedReader>> nFileTb = state.getExFlTable();
        MyIOut<Integer> nOut = state.getExOut();
        return new PrgState(nStack, nExDict, nOut, nFileTb, nHeap, stm);
    }

    @Override
    public String toString(){
        return  "fork(" + stm.toString() + ")";
    }
}
