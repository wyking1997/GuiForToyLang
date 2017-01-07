package GUI;

import domain.validators.ValidatorException;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import service.StudentService;
import utils.Observable;
import utils.Observer;

/**
 * StudentController is an MVC controller - see also Grasp Controller
 */
public class StudentViewController implements Observer<Student> {
    private ObservableList<Student> model;
    private StudentView view;
    StudentService service;

    public StudentViewController(StudentService service, StudentView view){
        this.view=view;
        this.model= FXCollections.observableArrayList(service.getAllStudents());
        view.studTable.setItems(model);
        this.service=service;
    }

    public ChangeListener<Student> changedTableItemListener() {
        ChangeListener<Student> changeListener = (observable, oldvalue, newValue) -> showStudentDetails(newValue);
        return changeListener;
    }

    public void showStudentDetails(Student value)
    {
        if (value==null)
        {
            view.textFieldFirstName.setText("");
            view.textFieldLastName.setText("");
            view.textFieldEmail.setText("");
            view.textFieldId.setText("");

        }
        else
        {
            view.textFieldFirstName.setText(value.getFirstName());
            view.textFieldLastName.setText(value.getLastName());
            view.textFieldEmail.setText(value.getEmail());
            view.textFieldId.setText(value.getId());
        }
    }

    @Override
    public void update(Observable<Student> observable) {
            StudentService s=(StudentService)observable;
            model.setAll(s.getAllStudents());
    }


    public void handleAddStudent(ActionEvent e)
    {
        String id=view.textFieldId.getText();
        String nume=view.textFieldLastName.getText();
        String pren=view.textFieldFirstName.getText();
        String email=view.textFieldEmail.getText();
        Student s=new Student(id,nume,pren,email);
        try {
            Student  saved=service.save(s);
            if (saved==null) {
                showMessage(Alert.AlertType.INFORMATION, "Salvare cu succes", "Studentul a fost adaugat!");
                showStudentDetails(null);
            }
            else
                showErrorMessage("Exista deja un student cu acest id!");
        } catch (ValidatorException e1) {
            showErrorMessage(e1.getMessage());
        }
    }

    static void showMessage(Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.showAndWait();
    }

    static void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
}
