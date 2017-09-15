package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    @Range(min=1, max=5, message = "Please enter a rating between 1 and 5")
    private int rating;

    @ManyToOne
    private Category category;

    public Cheese(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + this.description;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
