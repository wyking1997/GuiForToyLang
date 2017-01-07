package model;

import sun.plugin2.message.Serializer;

import java.io.Serializable;

/**
 * Created by Wyking on 10/23/2016.
 */
public interface IStm extends Serializable {
    PrgState execute(PrgState state) throws Exception;
    String toString();
}
