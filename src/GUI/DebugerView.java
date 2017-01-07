package GUI;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import javax.security.auth.callback.LanguageCallback;

/**
 * Created by Marius on 07/01/2017.
 */
public class DebugerView {

    private BorderPane borderPane;
    private Controller ctr;

    public static final ObservableList outList = FXCollections.observableArrayList();
    ListView<Integer> outListView;

    public DebugerView(Controller ctr) {
        this.ctr = ctr;
        borderPane = new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setBottom(initBottom());
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
        Label fileTableLabel = new Label("File table");
        grid.add(heapLabel, 1,1,3,1);
        grid.add(fileTableLabel, 6,1,3,1);

        //third line


        ac.getChildren().add(grid);
        AnchorPane.setTopAnchor(grid, 20d);
        AnchorPane.setLeftAnchor(grid, 20d);
        return ac;
    }
    private Node initBottom(){
        AnchorPane ac = new AnchorPane();
        return ac;
    }


    public BorderPane getView(){
        return this.borderPane;
    }
}
