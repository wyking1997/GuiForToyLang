package model;

import sun.plugin2.message.Serializer;
import utils.MyIDictionary;
import utils.MyIHeap;

import java.io.Serializable;

/**
 * Created by Wyking on 10/29/2016.
 */
abstract class Exp implements Serializable{
    abstract int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> hp) throws Exception;
}
