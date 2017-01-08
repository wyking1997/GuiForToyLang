package controller;

import model.IStm;
import model.PrgState;
import repository.MyIRepository;
import utils.MyIStack;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Wyking on 10/29/2016.
 */
public class Controller {

    MyIRepository repo;
    ExecutorService executor;

    public int getNbOfProgramStates(){
        return repo.getNbOfProgramStates();
    }
    public List<Integer> getOutputList(){
        return repo.getOutputList();
    }
    public Map<Integer,Integer> getHeap(){
        return this.repo.getHeap();
    }
    public Map<Integer,String> getFileTable(){return repo.getFileTable();}
    public List<String> getPrgList(){return repo.getProgramStates();}
    public Map<String,Integer> getSymbolTable(int index){return repo.getSymbolTable(index);}
    public List<String> getStack(int index){return repo.getStack(index);}

    public Controller(MyIRepository r) {
        repo = r;
        repo.init_file();
    }

    public void add(PrgState state) {
        repo.add(state);
    }

    public void executeAllStep(){
        while(true){
            List<PrgState> l = repo.getPrgList();
            List<PrgState> prgList=removeCompletedPrg(l);
            if (prgList.size() == 0)
                break;
            try {
                oneStepForAllPrg(prgList);
            } catch(Exception e) {
                System.out.println("INTERNAL ERROR!");
                repo.logPrgStateExec("INTERNAL ERROR!\n");
                System.exit(1);
            }
        }
    }

    public int executeOneStep(){
        List<PrgState> l = repo.getPrgList();
        List<PrgState> prgList=removeCompletedPrg(l);
        if (prgList.size() == 0)
            return 0;
        try {
            oneStepForAllPrg(prgList);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        List<PrgState> x = inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
        return x;
    }

    void oneStepForAllPrg(List<PrgState> ls) throws Exception {
        executor = Executors.newFixedThreadPool(2);
        List<Callable<PrgState>> callList = ls.stream()
                                              .map((PrgState p)->(Callable<PrgState>)(() -> {return p.oneStep();}))
                                              .collect(Collectors.toList());
        List<PrgState> newPrgList =
                executor.invokeAll(callList).stream()
                                            .map(future -> {
                                                                try {
                                                                    return future.get();
                                                                } catch (Exception e) {
                                                                    repo.logPrgStateExec(e.getMessage());
                                                                }
                                                                return null;
                                                            }
                                            ).filter(p -> p != null)
                                             .collect(Collectors.toList());
        repo.addAll(newPrgList);
        executor.shutdownNow();
    }

}