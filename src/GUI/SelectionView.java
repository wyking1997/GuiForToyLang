package GUI;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.IStm;

import java.util.ArrayList;

/**
 * Created by Marius on 07/01/2017.
 */

public class SelectionView {

    Stage primaryStage;
    private BorderPane borderPane;
    private ArrayList<Controller> controllers;

    public static final ObservableList programs = FXCollections.observableArrayList();
    private ListView<String> list;
    private Button buttonok= new Button("Ok");


    public SelectionView(ArrayList<Controller> controllers, ArrayList<String> program_repr, Stage stage) {
        this.controllers = controllers;
        this.primaryStage = stage;
        programs.addAll(program_repr);
        initBorderPane();
    }

    private void initBorderPane(){
        borderPane = new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setBottom(initBottom());
    }
    private Node initTop(){
        AnchorPane anchorPane = new AnchorPane();
        Label l = new Label("Select the program to be executed:");
        l.setFont(new Font(20));
        AnchorPane.setTopAnchor(l, 20d);
        AnchorPane.setLeftAnchor(l, 20d);
        anchorPane.getChildren().add(l);
        return anchorPane;
    }
    private Node initCenter() {
        AnchorPane anchorPane=new AnchorPane();
        list = new ListView<String>(programs);
        list.setMinWidth(800d);
        AnchorPane.setTopAnchor(list, 20d);
        AnchorPane.setLeftAnchor(list, 20d);
        anchorPane.getChildren().add(list);
        return anchorPane;
    }
    private Node initBottom() {
        AnchorPane anchorPane = new AnchorPane();

        AnchorPane.setLeftAnchor(buttonok, 20d);
        AnchorPane.setBottomAnchor(buttonok, 20d);

        buttonok.setOnAction(this::selected);
        buttonok.setFont(new Font(20));
        buttonok.setMinWidth(800d);
        anchorPane.getChildren().add(buttonok);
        return anchorPane;
    }

    public void selected(ActionEvent ac){
        int index = list.getSelectionModel().getSelectedIndices().get(0);
        if (index > -1 && index < controllers.size()) {
            DebugerView debuger = new DebugerView(this.controllers.get(index));
            primaryStage.setScene(new Scene(debuger.getView(), 840, 550));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error ocured during the execution...");
            alert.setContentText("There was nothing selected from the list, to start the debugger you first need" +
                    "to select a program from the given list!");

            alert.showAndWait();
        }
    }


    public BorderPane getSelectionView(){return borderPane;}
}
