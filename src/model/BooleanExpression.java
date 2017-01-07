package model;

import utils.MyIDictionary;
import utils.MyIHeap;

/**
 * Created by Wyking on 11/16/2016.
 */
public class BooleanExpression extends Exp {

    Exp exp1;
    Exp exp2;
    String opperator;

    public BooleanExpression(Exp exp1, Exp exp2, String opperator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.opperator = opperator;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> hp) throws Exception {
        int e1 = exp1.eval(tbl, hp);
        int e2 = exp2.eval(tbl, hp);
        switch (opperator){
            case "<": if (e1 < e2) return 1; else break;
            case "<=": if (e1 <= e2) return 1; else break;
            case "==": if (e1 == e2) return 1; else break;
            case "!=": if (e1 != e2) return 1; else break;
            case ">": if (e1 > e2) return 1; else break;
            case ">=": if (e1 >= e2) return 1; else break;
        }
        return 0;
    }

    @Override
    public String toString(){
        return "(" + exp1.toString() + opperator + exp2.toString() + ")";
    }
}