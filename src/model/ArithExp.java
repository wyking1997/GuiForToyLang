package model;

import utils.MyException;
import utils.MyIDictionary;
import utils.MyIHeap;

/**
 * Created by Wyking on 10/29/2016.
 */
public class ArithExp extends Exp {

    Exp e1;
    Exp e2;

    char opp;

    public ArithExp(Exp e11, Exp e22, char op){
        opp = op;
        e1 = e11;
        e2 = e22;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> sbTbl, MyIHeap<Integer> hp) throws Exception {
       switch (opp) {
           case '+':
               return e1.eval(sbTbl, hp) + e2.eval(sbTbl, hp);
           case '-':
               return e1.eval(sbTbl, hp) - e2.eval(sbTbl, hp);
           case '/':
               if (e2.eval(sbTbl, hp) == 0)
                   throw new MyException("Divide by 0: " + e1.eval(sbTbl, hp) + opp + e2.eval(sbTbl, hp) + "!");
               return e1.eval(sbTbl, hp) / e2.eval(sbTbl, hp);
           case '*':
               return e1.eval(sbTbl, hp) * e2.eval(sbTbl, hp);
           default:
               throw new MyException("Ilegal opperator' " + opp + "'!");
       }
    }

    @Override
    public String toString() {
        return "(" + e1.toString() + " " + opp + " " + e2.toString() + ")";
    }
}
