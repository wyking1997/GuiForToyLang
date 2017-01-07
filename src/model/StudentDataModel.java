package model;

import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


public class StudentDataModel {

    public static ObservableList<Student> getStudentDataModel()
    {
        List<Student> l=new ArrayList<Student>();
        l.add(new Student("1","Barbu","Ionut","aaa@yahoo.com"));
        l.add(new Student("2","Andu","Dan","dd@yahoo.com"));
        l.add(new Student("3","Barbu","Andrei","dsss@yahoo.com"));
        l.add(new Student("4","Stache","Paul","aads@yahoo.com"));
        ObservableList<Student> studentDataModel = FXCollections.observableArrayList(l);
        return studentDataModel;
    }

}
