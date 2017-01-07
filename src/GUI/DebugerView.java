package GUI;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import utils.Observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Marius on 07/01/2017.
 */
public class DebugerView {

    private BorderPane borderPane;
    private Controller ctr;

    public static final ObservableList outList = FXCollections.observableArrayList();
    private ListView<Integer> outListView;
    private TableView<Heap> heapTable = new TableView<>();
    private final ObservableList<Heap> heapData =
            FXCollections.observableArrayList();
    private TableView<FileTable> fileTbTable = new TableView<>();
    private final ObservableList<FileTable> fileTbData=
            FXCollections.observableArrayList();

    public static final ObservableList prgStateList = FXCollections.observableArrayList();
    private ListView<String> prgStateListView;

    private List<Heap> heapList;
    private List<FileTable> fileTbList;

    public DebugerView(Controller ctr) {
        this.ctr = ctr;
        heapList = new ArrayList<>();
        fileTbList = new ArrayList<>();
        prgStateListView = new ListView<>(prgStateList);

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

        //first line
        Label prgLabel = new Label("Program states");
        TextField prgText = new TextField("" + ctr.getNbOfProgramStates());
        Label outLabel = new Label("Output");
        outListView = new ListView<>(outList);
        outListView.setMinWidth(100);
        outListView.setMaxHeight(25);
        outListView.setOrientation(Orientation.HORIZONTAL);
        outList.addAll(this.ctr.getOutputList());
        grid.add(prgLabel,0,0);
        grid.add(prgText, 1,0);
        grid.add(outLabel, 6, 0);
        grid.add(outListView, 7, 0);

        //second line
        Label heapLabel = new Label("Heap");
        heapLabel.setFont(new Font(16));
        Label fileTableLabel = new Label("File table");
        fileTableLabel.setFont(new Font(16));
        grid.add(heapLabel, 1,2,3,1);
        grid.add(fileTableLabel, 6,2,3,1);

        //third line
        //heap
        TableColumn adrCol = new TableColumn("Adress");
        TableColumn valCol = new TableColumn("Value");
        adrCol.setCellValueFactory(new PropertyValueFactory<Heap,String>("adress"));
        valCol.setCellValueFactory(new PropertyValueFactory<Heap,String>("value"));
        heapTable.getColumns().addAll(adrCol,valCol);
        heapTable.setItems(heapData);
        heapData.addAll(heapList);
        //file table
        TableColumn idfCol = new TableColumn("Identifier");
        TableColumn flnCol = new TableColumn("File name");
        idfCol.setCellValueFactory(new PropertyValueFactory<FileTable,String>("identifier"));
        flnCol.setCellValueFactory(new PropertyValueFactory<FileTable,String>("flName"));
        fileTbTable.getColumns().addAll(idfCol,flnCol);
        fileTbTable.setItems(fileTbData);
        fileTbData.addAll(fileTbList);
        grid.add(heapTable, 0,3,4,3);
        grid.add(fileTbTable, 5,3,4,3);

        //fourth line
        Label prgStateLabel = new Label("Program state");
        prgStateLabel.setFont(new Font(16));
        Label simbolsLabel = new Label("Symbol table");
        simbolsLabel.setFont(new Font(16));
        Label stackLabel = new Label("Stack");
        stackLabel.setFont(new Font(16));
        grid.add(prgStateLabel, 15,0,3,1);
        grid.add(simbolsLabel,18,0,3,1);
        grid.add(stackLabel,21,0,3,1);


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

        Map<Integer,Integer> map = this.ctr.getHeap();
        for(Integer key : map.keySet()){
            heapList.add(new Heap(key, map.get(key)));
        }
        Map<Integer,String> map2 = this.ctr.getFileTable();
        for(Integer key : map2.keySet()){
            fileTbList.add(new FileTable(key, map2.get(key)));
        }
    }

    public BorderPane getView(){
        return this.borderPane;
    }
}
