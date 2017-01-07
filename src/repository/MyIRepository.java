package repository;

import model.PrgState;
import utils.MyException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Wyking on 10/29/2016.
 */
public interface MyIRepository {
    void addAll(List<PrgState> states);
    void add(PrgState state);
    PrgState get(int position) throws Exception;
    void logPrgStateExec(String data);
    void logPrgStateExec(PrgState state);
    String getOutputFile();
    void serialize(PrgState state, String serialize_file_name);
    PrgState deserializare(String file_name) throws Exception;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> p);
    void init_file();

    int getNbOfProgramStates();
    List<Integer> getOutputList();
    Map<Integer, Integer> getHeap();
    Map<Integer,String> getFileTable();
}