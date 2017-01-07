package GUI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Marius on 07/01/2017.
 */
public class FileTable {
    private final SimpleIntegerProperty identifier;
    private final SimpleStringProperty flName;

    public FileTable(Integer identifier, String flName) {
        this.identifier = new SimpleIntegerProperty(identifier);
        this.flName = new SimpleStringProperty(flName);
    }

    public int getIdentifier() {
        return identifier.get();
    }

    public SimpleIntegerProperty identifierProperty() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier.set(identifier);
    }

    public String getFlName() {
        return flName.get();
    }

    public SimpleStringProperty flNameProperty() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName.set(flName);
    }
}
