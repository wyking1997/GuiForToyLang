package GUI;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import utils.*;

import java.io.BufferedReader;
import java.util.ArrayList;


public class Main extends Application {

    ArrayList<Controller> controllers;
    ArrayList<String> program_repr;

    @Override
    public void start(Stage stage) {
        initializeAtributes();
        loadFirstScene();
    }

    public void loadFirstScene(){
        Stage primaryStage = new Stage();
        Parent root = (new SelectionView(controllers, program_repr, primaryStage)).getSelectionView();
        Scene scene = new Scene(root, 840, 550);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Go for it");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeAtributes(){
        IStm statement1 = new CompStm(new PrintStm(new ArithExp(new ConstExp(10), new BooleanExpression(new ConstExp(2), new ConstExp(6),"<"), '+')), new PrintStm(new BooleanExpression(new ArithExp(new ConstExp(10),new ConstExp(2),'+'), new ConstExp(6),"<")));
        PrgState state1 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),statement1);
        repository.Repository repo1 = new repository.Repository(state1);
        Controller ctr1 = new Controller(repo1);

        IStm statement2 = new CompStm(new AssingnStm("v",new ConstExp(6)), new CompStm(new WhileStm(new BooleanExpression(new VarExp("v"), new ConstExp(30), "<"), new CompStm(new PrintStm(new VarExp("v")), new AssingnStm("v",new ArithExp(new VarExp("v"), new ConstExp(3),'+')))), new PrintStm(new VarExp("v"))));
        PrgState state2 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),statement2);
        repository.Repository repo2 = new repository.Repository(state2);
        Controller ctr2 = new Controller(repo2);

        IStm stm1 = new CompStm(new PrintStm(new ConstExp(10)), new PrintStm(new ConstExp(100)));
        IStm stm222 = new CompStm(new AssingnStm("a", new ConstExp(10)), new CompStm(new AssingnStm("b", new ConstExp(45)), new PrintStm(new ArithExp(new VarExp("a"), new VarExp("b"), '+'))));
        IStm stm22 = new CompStm(stm1, stm222);
        IStm stm23 = new CompStm(stm22, new PrintStm(new ArithExp(new VarExp("a"), new VarExp("b"), '-')));
        IStm stm24 = new CompStm(stm23, new PrintStm(new ArithExp(new VarExp("a"), new VarExp("b"), '*')));
        IStm stm2 = new CompStm(stm24, new PrintStm(new ArithExp(new VarExp("a"), new VarExp("b"), '/')));
        IStm stm33 = new IfStm(new ArithExp(new VarExp("a"), new VarExp("b"), '+'), new PrintStm(new ConstExp(1)), new PrintStm(new ConstExp(0)));
        IStm stm3 = new CompStm(stm2, stm33);
        PrgState state3 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm3);
        repository.Repository repo3 = new repository.Repository(state3);
        Controller ctr3 = new Controller(repo3);

        String fl_name1 = "da.txt";
        String fl_name2 = "nu.txt";
        IStm stm05 = new CompStm(new OpenRFileStm("file1", fl_name1), new CompStm(new ReadFileStm(new VarExp("file1"), "a"), new ReadFileStm(new VarExp("file1"), "b")));
        IStm stm55 = new CompStm(stm05, new OpenRFileStm("file2", fl_name2));
        IStm stm51 = new CompStm(new ReadFileStm(new ConstExp(1), "c"), new CloseRFileStm(new VarExp("file1")));
        IStm stm4 = new CompStm(stm55, new CompStm(new ReadFileStm(new VarExp("file2"), "c"), stm51));
        PrgState state4 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm4);
        repository.Repository repo4 = new repository.Repository(state4,"da");
        Controller ctr4 = new Controller(repo4);

        IStm stm5 = new CompStm(new AssingnStm("v", new ConstExp(10)), new CompStm(new NewStm("v", new ConstExp(20)), new CompStm(new NewStm("a",new ConstExp(22)), new PrintStm(new VarExp("v")))));
        PrgState state5 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm5);
        repository.Repository repo5 = new repository.Repository(state5);
        Controller ctr5 = new Controller(repo5);

        IStm stm6 = new CompStm(new AssingnStm("v", new ConstExp(10)), new CompStm(new NewStm("v", new ConstExp(20)), new CompStm(new NewStm("a",new ConstExp(22)),new PrintStm(new ArithExp(new ConstExp(100),new ReadHeap("a"),'+')))));
        PrgState state6 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm6);
        repository.Repository repo6 = new repository.Repository(state6);
        Controller ctr6 = new Controller(repo6);

        IStm stm7 = new CompStm(new AssingnStm("v", new ConstExp(10)), new CompStm(new NewStm("v", new ConstExp(20)), new CompStm(new NewStm("a",new ConstExp(22)), new CompStm(new WriteHeapStm("a", new ConstExp(30)), new CompStm(new PrintStm(new VarExp("a")), new PrintStm(new ReadHeap("a")))))));
        PrgState state7 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm7);
        repository.Repository repo7 = new repository.Repository(state7);
        Controller ctr7 = new Controller(repo7);

        IStm stm8 = new CompStm(new AssingnStm("v", new ConstExp(10)), new CompStm(new NewStm("v", new ConstExp(20)), new CompStm(new NewStm("a",new ConstExp(22)), new CompStm(new WriteHeapStm("a", new ConstExp(30)), new CompStm(new PrintStm(new VarExp("a")), new CompStm(new PrintStm(new ReadHeap("a")),new CompStm(new AssingnStm("a", new ConstExp(0)), new PrintStm(new ConstExp(1)))))))));
        PrgState state8 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm8);
        repository.Repository repo8 = new repository.Repository(state8);
        Controller ctr8 = new Controller(repo8);

        IStm stm9 = new CompStm(new AssingnStm("v", new ConstExp(10)), new CompStm(new NewStm("a",new ConstExp(22)), new CompStm(new ForkStatement(new WriteHeapStm("a", new ConstExp(30))),new CompStm(new AssingnStm("v", new ConstExp(32)), new CompStm(new PrintStm(new ReadHeap("a")), new CompStm(new PrintStm(new VarExp("v")),new PrintStm(new ReadHeap("a"))))))));
        PrgState state9 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm9);
        repository.Repository repo9 = new repository.Repository(state9);
        Controller ctr9 = new Controller(repo9);

        IStm fork1 = new ForkStatement(new CompStm(new NewStm("a", new ConstExp(10)), new CompStm(new NewStm("b", new ConstExp(20)), new NewStm("c", new ConstExp(30)))));
        IStm fork2 = new ForkStatement(new CompStm(new PrintStm(new ReadHeap(new ConstExp(1))), new CompStm(new PrintStm(new ReadHeap(new ConstExp(2))), new PrintStm(new ReadHeap(new ConstExp(3))))));
        IStm stm10 = new CompStm(fork1, fork2);
        PrgState state10 = new PrgState(new ExecutionStack<IStm>(), new ExecutionDictionary<String, Integer>(), new ExecutionOut<Integer>(), new ExecutionFileTable<Integer, Pair<String, BufferedReader>>(), new ExecutionHeap<Integer>(),stm10);
        repository.Repository repo10 = new repository.Repository(state10);
        Controller ctr10 = new Controller(repo10);

        program_repr = new ArrayList<>();
        program_repr.clear();
        program_repr.add(statement1.toString());
        program_repr.add(statement2.toString());
        program_repr.add(stm3.toString());
        program_repr.add(stm4.toString());
        program_repr.add(stm5.toString());
        program_repr.add(stm6.toString());
        program_repr.add(stm7.toString());
        program_repr.add(stm8.toString());
        program_repr.add(stm9.toString());
        program_repr.add(stm10.toString());

        controllers = new ArrayList<>();
        controllers.clear();
        controllers.add(ctr1);
        controllers.add(ctr2);
        controllers.add(ctr3);
        controllers.add(ctr4);
        controllers.add(ctr5);
        controllers.add(ctr6);
        controllers.add(ctr7);
        controllers.add(ctr8);
        controllers.add(ctr9);
        controllers.add(ctr10);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
