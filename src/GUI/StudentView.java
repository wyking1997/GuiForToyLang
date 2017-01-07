package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import domain.Student;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.StudentDataModel;
import service.StudentService;

/**
 * Created by camelia on 11/8/2016.
 */

public class StudentView {
    BorderPane borderPane;

    TableView<Student> studTable=new TableView<>();
    TableColumn<Student,String> firstNameColumn=new TableColumn<>("Nume");
    TableColumn<Student,String> lastNameColumn=new TableColumn<>("Prenume");



    TextField textFieldFirstName=new TextField();
    TextField textFieldLastName=new TextField();
    TextField textFieldId=new TextField();
    TextField textFieldEmail=new TextField();

    Button buttonAdd=new Button("Add");
    Button buttonUpdate=new Button("Update");
    Button buttonDelete=new Button("Delete");

    StudentViewController ctrl;


    public StudentView(StudentService service){
        ctrl=new StudentViewController(service, this);
        service.addObserver(ctrl);
        initBorderPane();
    }

    public BorderPane getView() {
        return borderPane;
    }

    private void initBorderPane() {
        borderPane=new BorderPane();
        borderPane.setTop(initTop());
        borderPane.setCenter(initCenter());
        borderPane.setLeft(initLeft());

    }

    private Node initTop() {
        AnchorPane anchorPane=new AnchorPane();

        Label l=new Label("Student management System");
        l.setFont(new Font(20));
        l.setStyle("-fx-font-weight: bold");
        AnchorPane.setTopAnchor(l,20d);
        AnchorPane.setRightAnchor(l,100d);
        anchorPane.getChildren().add(l);

        Image img = new Image("logo.gif");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        imgView.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(imgView,20d);
        AnchorPane.setRightAnchor(imgView,10d);
        anchorPane.getChildren().add(imgView);

        return anchorPane;
    }

    private Label createLabel(String s, int fontSize, Color c){
        Label l=new Label();
        l.setText(s);
        l.setFont(new Font(15));
        l.setTextFill(c);
        return l;
    }

    private Node initCenter() {
        AnchorPane anchorPane=new AnchorPane();

        //init GridPane students details
        GridPane gridStudentDetails=new GridPane();
        gridStudentDetails.setHgap(5);
        gridStudentDetails.setVgap(5);
        AnchorPane.setLeftAnchor(gridStudentDetails,20d);
        AnchorPane.setTopAnchor(gridStudentDetails,20d);
        ColumnConstraints c=new ColumnConstraints();
        c.setPrefWidth(100);
        gridStudentDetails.getColumnConstraints().add(c);

        buttonAdd.setOnAction(ctrl::handleAddStudent);

        Label labelFirstName=createLabel("First Name:",12,Color.BLACK);
        labelFirstName.setStyle("-fx-font-weight: bold");
        Label labelLastName=createLabel("Last Name:",12,Color.BLACK);
        labelLastName.setStyle("-fx-font-weight: bold");
        Label labelEmail=createLabel("Email:",12,Color.BLACK);
        labelEmail.setStyle("-fx-font-weight: bold");
        Label labelId=createLabel("Id:",12,Color.BLACK);
        labelId.setStyle("-fx-font-weight: bold");
//        labelFirstNameValue=createLabel("Aprogramatoarei",12,Color.CRIMSON);
//        labelLastNameValue=createLabel("Lucica",12,Color.CRIMSON);
//        labelEmailValue=createLabel("lucicaApr@yahoo.com",12,Color.CRIMSON);

        gridStudentDetails.add(labelFirstName,0,0);
        gridStudentDetails.add(labelLastName,0,1);
        gridStudentDetails.add(labelEmail,0,2);
        gridStudentDetails.add(labelId,0,3);
        gridStudentDetails.add(textFieldFirstName,1,0);
        gridStudentDetails.add(textFieldLastName,1,1);
        gridStudentDetails.add(textFieldEmail,1,2);
        gridStudentDetails.add(textFieldId,1,3);

        anchorPane.getChildren().add(gridStudentDetails);
        //init HBox Button

        HBox hb=new HBox(5, buttonAdd,buttonUpdate, buttonDelete);
        AnchorPane.setBottomAnchor(hb,100d);
        AnchorPane.setLeftAnchor(hb,20d);
        //hb.setPadding(new Insets(30));
        anchorPane.getChildren().add(hb);

        return anchorPane;

    }

    private Node initLeft() {
        AnchorPane anchorPane=new AnchorPane();
        //anchor the TableView into the ap
        AnchorPane.setLeftAnchor(studTable,20d);
        AnchorPane.setTopAnchor(studTable,20d);

        studTable.setMinHeight(50d);
        studTable.setPrefHeight(300d);
        initTableView();
        anchorPane.getChildren().add(studTable);
        return anchorPane;
    }

    public void initTableView()
    {
        studTable.getColumns().add(firstNameColumn);
        studTable.getColumns().add(lastNameColumn);

        //stabilirea valorilor asociate unei celule
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName")); //
        // return an ReadOnlyObjectWrapper  if Student class don't have a firstName Property attribute
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

        //studTable.setItems(model);
        studTable.getSelectionModel().selectedItemProperty().addListener(ctrl.changedTableItemListener());

        // Auto resize columns
        studTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // clear Student
        ctrl.showStudentDetails(null);
        // Listen for selection changes and show the Student details when changed.
        //aici
//        studTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable,oldvalue,newValue)->showStudentDetails(newValue) );

    }

}
