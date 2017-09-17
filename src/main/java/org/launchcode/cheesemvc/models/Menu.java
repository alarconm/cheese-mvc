package org.launchcode.cheesemvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Please enter a name that is 3 to 15 characters long")
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu() { }

    public Menu(String name) {
        this.name = name;
    }

    public void addItem(Cheese item) {
        cheeses.add(item);
    }

    public void removeItem(Cheese item) {
        cheeses.remove(item);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

}
