package GUI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Marius on 08/01/2017.
 */
public class SimbolTable {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty value;

    public SimbolTable(String name, int value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleIntegerProperty(value);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }
}
