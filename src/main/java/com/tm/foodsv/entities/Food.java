package com.tm.foodsv.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tm.foodsv.util.Warnings;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private URL image;
    @NotNull
    private Category category;
    @Column(name = "nova_group")
    @JsonProperty("nova_group")
    private NovaClasification novaClasification;
    @PositiveOrZero
    private int calories;
    @PositiveOrZero
    private double protein;
    @PositiveOrZero
    private double fats;
    @PositiveOrZero
    private double carbs;
    @PositiveOrZero
    private double sugar;
    @PositiveOrZero
    private double sodium;
    @JsonProperty("serving_size")
    @Column(name = "serving_size")
    @Positive
    private double servingSize;
    @ElementCollection(targetClass = Warnings.class)
    @CollectionTable(name = "warnings", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "warning", nullable = false)
    private List<Warnings> warnings= new ArrayList<>();

    public double getServingSize() {
        return servingSize;
    }

    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
    public NovaClasification getNovaClasification() {
        return novaClasification;
    }

    public void setNovaClasification(NovaClasification novaClasification) {
        this.novaClasification = novaClasification;
    }

    public List<Warnings> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Warnings> warnings) {
        this.warnings = warnings;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Food other = (Food) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
