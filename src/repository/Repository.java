package repository;

import model.AssingnStm;
import model.IStm;
import model.PrgState;
import utils.MyException;
import utils.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by Wyking on 10/29/2016.
 */
public class Repository implements MyIRepository {

    ArrayList<PrgState> ls;
    private String file_name;
    private String prg_description;

    public Repository(PrgState state, String output_file) {

        ls = new ArrayList<PrgState>();
        ls.add(state);
        file_name = "files\\repo_files\\" + output_file;
    }

    public Repository(PrgState state){

        this(state, "default.txt");
    }

    public Repository(){

        this(null, "default.txt");
        ls.clear();
    }

    @Override
    public void add(PrgState state) {
        ls.add(state);
    }

    @Override
    public void addAll(List<PrgState> states) {
        ls.addAll(states);
    }

    @Override
    public PrgState get(int position) throws Exception{

        if (position < 0 || position > ls.size())
            throw new MyException("Index out of bounds!");
        return ls.get(position);
    }

    public void init_file(){
        FileWriter fw = null;
        PrintWriter pw = null;

        try{
            fw = new FileWriter(file_name, false);
            pw = new PrintWriter(fw, false);
        } catch (IOException e) {
            System.out.println("!!!INTERNAL ERROR: " + e.getMessage());
        }

        try {
            fw.close();
        } catch (IOException e) {
            System.out.println("!!!INTERNAL ERROR: " + e.getMessage());
        }
        pw.close();
    }

    @Override
    public int getNbOfProgramStates() {
        return this.ls.size();
    }

    @Override
    public List<Integer> getOutputList() {
        return ls.get(0).getExOut().getOutputList();
    }

    @Override
    public Map<Integer, Integer> getHeap() {
        return this.ls.get(0).getExHeap().getHeap();
    }

    @Override
    public Map<Integer, String> getFileTable() {
        Map<Integer, Pair<String, BufferedReader>> map = this.ls.get(0).getExFlTable().getFileTable();
        Map<Integer,String> result = new HashMap<>();
        for (int key : map.keySet()){
            result.put(key, map.get(key).getFirstEl());
        }
        return result;
    }

    @Override
    public List<String> getProgramStates() {
        ArrayList<String> result = new ArrayList<>();
        for (PrgState prg : ls)
            result.add("prg " + prg.getId());
        return result;
    }

    @Override
    public Map<String, Integer> getSymbolTable(int index) {
        return ls.get(index).getExDict().getTable();
    }

    @Override
    public List<String> getStack(int index) {
        List<String> list = new ArrayList<>();
        Stack<IStm> stack = this.ls.get(index).getExStack().getStack();
        while (!stack.isEmpty()){
            IStm stm = stack.pop();
            list.add(stm.toString());
        }
        return list;
    }

    @Override
    public void logPrgStateExec(String data){

        FileWriter fw;
        PrintWriter pw = null;

        try{
            fw = new FileWriter(file_name, true);
            pw = new PrintWriter(fw, true);
        } catch (IOException e) {
            System.exit(1);
        }

        pw.write(data + "\n");
        pw.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return ls;
    }

}
