package GUI;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Marius on 07/01/2017.
 */
public class Heap {
    private final SimpleIntegerProperty adress;
    private final SimpleIntegerProperty value;

    public Heap(Integer adress, Integer value) {
        this.adress = new SimpleIntegerProperty(adress);
        this.value = new SimpleIntegerProperty(value);
    }

    public int getAdress() {
        return adress.get();
    }

    public SimpleIntegerProperty adressProperty() {
        return adress;
    }

    public void setAdress(int adress) {
        this.adress.set(adress);
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