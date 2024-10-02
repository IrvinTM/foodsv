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
    @JsonProperty("total_fats")
    @Column(name = "total_fats")
    private double TotalFats;
    @PositiveOrZero
    @JsonProperty("saturated_fats")
    @Column(name = "saturated_fats")
    private double saturatedFats;
    @PositiveOrZero
    @JsonProperty("trans_fats")
    @Column(name = "trans_fats")
    private double transFats;
    @PositiveOrZero
    private double cholesterol;
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

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotNull URL getImage() {
        return image;
    }

    public void setImage(@NotNull URL image) {
        this.image = image;
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public NovaClasification getNovaClasification() {
        return novaClasification;
    }

    public void setNovaClasification(NovaClasification novaClasification) {
        this.novaClasification = novaClasification;
    }

    @PositiveOrZero
    public int getCalories() {
        return calories;
    }

    public void setCalories(@PositiveOrZero int calories) {
        this.calories = calories;
    }

    @PositiveOrZero
    public double getProtein() {
        return protein;
    }

    public void setProtein(@PositiveOrZero double protein) {
        this.protein = protein;
    }

    @PositiveOrZero
    public double getTotalFats() {
        return TotalFats;
    }

    public void setTotalFats(@PositiveOrZero double totalFats) {
        TotalFats = totalFats;
    }

    @PositiveOrZero
    public double getSaturatedFats() {
        return saturatedFats;
    }

    public void setSaturatedFats(@PositiveOrZero double saturatedFats) {
        this.saturatedFats = saturatedFats;
    }

    @PositiveOrZero
    public double getTransFats() {
        return transFats;
    }

    public void setTransFats(@PositiveOrZero double transFats) {
        this.transFats = transFats;
    }

    @PositiveOrZero
    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(@PositiveOrZero double cholesterol) {
        this.cholesterol = cholesterol;
    }

    @PositiveOrZero
    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(@PositiveOrZero double carbs) {
        this.carbs = carbs;
    }

    @PositiveOrZero
    public double getSugar() {
        return sugar;
    }

    public void setSugar(@PositiveOrZero double sugar) {
        this.sugar = sugar;
    }

    @PositiveOrZero
    public double getSodium() {
        return sodium;
    }

    public void setSodium(@PositiveOrZero double sodium) {
        this.sodium = sodium;
    }

    @Positive
    public double getServingSize() {
        return servingSize;
    }

    public void setServingSize(@Positive double servingSize) {
        this.servingSize = servingSize;
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
