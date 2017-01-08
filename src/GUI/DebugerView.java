package GUI;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.*;

/**
 * Created by Marius on 07/01/2017.
 */
public class DebugerView {

    private BorderPane borderPane;
    private Controller ctr;
    private int prgStateIndex = 0;

    private ListView<Integer> outListView;
    public static final ObservableList outList = FXCollections.observableArrayList();
    private TableView<Heap> heapTable = new TableView<>();
    private static final ObservableList<Heap> heapData = FXCollections.observableArrayList();
    private TableView<FileTable> fileTbTable = new TableView<>();
    private static final ObservableList<FileTable> fileTbData = FXCollections.observableArrayList();
    private TableView<SimbolTable> simbolTbTable = new TableView<>();
    private static final ObservableList<SimbolTable> simbolTbData = FXCollections.observableArrayList();

    private ListView<String> prgStateListView;
    public static final ObservableList prgStateList = FXCollections.observableArrayList();
    private ListView<String> stackListView;
    public static final ObservableList stackList = FXCollections.observableArrayList();

    private List<Heap> heapList;
    private List<FileTable> fileTbList;
    private List<SimbolTable> simbolTbList;

    public DebugerView(Controller ctr) {
        this.ctr = ctr;
        heapList = new ArrayList<>();
        fileTbList = new ArrayList<>();
        simbolTbList = new ArrayList<>();
        prgStateListView = new ListView<>(prgStateList);
        stackListView = new ListView<>(stackList);

        borderPane = new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setBottom(initBottom());
        tablesRefresh();
    }

    private Node initTop() {
        Label label = new Label("Debugger");
        label.setFont(new Font(20));
        AnchorPane ac = new AnchorPane(label);
        AnchorPane.setLeftAnchor(label, 20d);
        AnchorPane.setTopAnchor(label, 20d);
        return ac;
    }
    private Node initCenter(){
        AnchorPane ac = new AnchorPane();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //labels for program states and output, listview for output, text field for program states
        //buttons for oneStep and allStep
        HBox hb = new HBox();
        hb.setSpacing(10);
        Label prgLabel = new Label("Program states");
        prgLabel.setFont(new Font(16));
        TextField prgText = new TextField("" + ctr.getNbOfProgramStates());
        Label outLabel = new Label("Output");
        outLabel.setFont(new Font(16));
        outListView = new ListView<>(outList);
        outListView.setMinWidth(50);
        outListView.setMaxHeight(25);
        outListView.setOrientation(Orientation.HORIZONTAL);
        Button oneStepButton = new Button("One step");
        Button allStepButton = new Button("All step");
        oneStepButton.setMinWidth(150);
        allStepButton.setMinWidth(150);
        oneStepButton.setOnAction(this::oneStepAction);
        allStepButton.setOnAction(this::allStepAction);
        hb.getChildren().addAll(prgLabel,prgText,outLabel,outListView, oneStepButton, allStepButton);
        grid.add(hb,0,0,10,1);

        //labels for heap and filetable
        Label heapLabel = new Label("Heap");
        heapLabel.setFont(new Font(16));
        Label fileTableLabel = new Label("File table");
        fileTableLabel.setFont(new Font(16));
        grid.add(heapLabel, 0,2);
        grid.add(fileTableLabel, 1,2);

        //the table views for heap and file table
        //heap
        TableColumn adrCol = new TableColumn("Adress");
        TableColumn valCol = new TableColumn("Value");
        adrCol.setCellValueFactory(new PropertyValueFactory<Heap,String>("adress"));
        valCol.setCellValueFactory(new PropertyValueFactory<Heap,String>("value"));
        heapTable.getColumns().addAll(adrCol,valCol);
        heapTable.setMaxWidth(160d);
        heapTable.setItems(heapData);
        heapData.addAll(heapList);
        //file table
        TableColumn idfCol = new TableColumn("Identifier");
        TableColumn flnCol = new TableColumn("File name");
        idfCol.setCellValueFactory(new PropertyValueFactory<FileTable,String>("identifier"));
        flnCol.setCellValueFactory(new PropertyValueFactory<FileTable,String>("flName"));
        fileTbTable.getColumns().addAll(idfCol,flnCol);
        fileTbTable.setMaxWidth(160d);
        fileTbTable.setItems(fileTbData);
        fileTbData.addAll(fileTbList);
        grid.add(heapTable, 0,3);
        grid.add(fileTbTable, 1,3);

        //labels for program state, symbol table, stack
        Label prgStateLabel = new Label("Program state");
        prgStateLabel.setFont(new Font(16));
        Label simbolsLabel = new Label("Symbol table");
        simbolsLabel.setFont(new Font(16));
        Label stackLabel = new Label("Stack");
        stackLabel.setFont(new Font(16));
        grid.add(prgStateLabel, 4,2);
        grid.add(simbolsLabel,3,2);
        grid.add(stackLabel,5,2);

        //prg state list view
        prgStateListView = new ListView<>(prgStateList);
        prgStateListView.setMaxWidth(160d);
        prgStateListView.getSelectionModel().selectedItemProperty().addListener(this::selectedPrgState);
        grid.add(prgStateListView, 4, 3);

        //simbolTb table
        TableColumn nameCol = new TableColumn("Variable");
        TableColumn valueCol = new TableColumn("Value");
        nameCol.setCellValueFactory(new PropertyValueFactory<SimbolTable,String>("name"));
        valueCol.setCellValueFactory(new PropertyValueFactory<SimbolTable,String>("value"));
        simbolTbTable.getColumns().addAll(nameCol,valCol);
        simbolTbTable.setMaxWidth(160d);
        simbolTbTable.setItems(simbolTbData);
        simbolTbData.addAll(simbolTbList);
        grid.add(simbolTbTable, 3,3);

        //stack list view
        stackListView = new ListView<>(stackList);
        stackListView.setMinWidth(480d);
        stackListView.setMaxWidth(480d);
        grid.add(stackListView, 5,3,3,1);

        ac.getChildren().add(grid);
        AnchorPane.setTopAnchor(grid, 20d);
        AnchorPane.setLeftAnchor(grid, 20d);
        return ac;
    }
    private Node initBottom(){
        AnchorPane ac = new AnchorPane();
        return ac;
    }

    private void tablesRefresh(){
        heapList.clear();
        fileTbList.clear();

        //heap
        Map<Integer,Integer> map = this.ctr.getHeap();
        for(Integer key : map.keySet()){
            heapList.add(new Heap(key, map.get(key)));
        }
        //file table
        Map<Integer,String> map2 = this.ctr.getFileTable();
        for(Integer key : map2.keySet()){
            fileTbList.add(new FileTable(key, map2.get(key)));
        }
        outList.clear();
        outList.addAll(this.ctr.getOutputList());
        //program states
        prgStateList.clear();
        prgStateList.addAll(ctr.getPrgList());
        prgStateListView.getSelectionModel().select(0);
    }

    private void selectedPrgState(javafx.beans.Observable e){
        prgStateIndex = prgStateListView.getSelectionModel().getSelectedIndices().get(0);
        refreshSymbolTable();
        refreshStack();
    }

    private void refreshStack() {
        stackList.clear();
        stackList.addAll(ctr.getStack(prgStateIndex));
    }

    private void refreshSymbolTable() {
        simbolTbList.clear();
        if (prgStateIndex == -1)
            prgStateIndex = 0;
        Map<String,Integer> map = ctr.getSymbolTable(prgStateIndex);
        for(String k : map.keySet())
            simbolTbList.add(new SimbolTable(k, map.get(k)));
    }

    public BorderPane getView(){
        return this.borderPane;
    }

    private void oneStepAction(javafx.event.ActionEvent e){

    }

    private void allStepAction(javafx.event.ActionEvent e){
        ctr.executeAllStep();
        tablesRefresh();
    }
}
