package GUI;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Marius on 07/01/2017.
 */
public class Heap {
    private final SimpleIntegerProperty adress;
    private final SimpleIntegerProperty value;

    public Heap(Integer addres, Integer value) {
        this.adress = new SimpleIntegerProperty(addres);
        this.value = new SimpleIntegerProperty(value);
    }

    public int getAddres() {
        return adress.get();
    }

    public SimpleIntegerProperty addresProperty() {
        return adress;
    }

    public void setAddres(int addres) {
        this.adress.set(addres);
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
