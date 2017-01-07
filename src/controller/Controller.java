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

    public Controller(MyIRepository r) {
        repo = r;
    }

    public void add(PrgState state) {
        repo.add(state);
    }

    public void executeAllStep(){
        repo.init_file();
        executor = Executors.newFixedThreadPool(2);
        while(true){
            List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
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
        executor.shutdownNow();
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public String getOutputFile(){
        return repo.getOutputFile();
    }

    public void serialize(String file_name){
        try {
            repo.serialize(repo.get(0), file_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrgState deserialize(String file_name) throws Exception{
        return repo.deserializare(file_name);
    }

    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        Map<Integer,Integer> mp = heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        return mp;
    }

    void oneStepForAllPrg(List<PrgState> ls) throws Exception {
        
        ls.forEach(prg -> repo.logPrgStateExec(prg.toString()));
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
        //add the new created threads to the list of existing threads
        repo.addAll(newPrgList);
        //Log the PrgStates after the execution
        repo.getPrgList().forEach(prg ->repo.logPrgStateExec(prg));
        repo.logPrgStateExec("-----------------------------------------------------------------------------------");
    }
}