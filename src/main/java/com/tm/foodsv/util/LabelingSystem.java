package com.tm.foodsv.util;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;

import java.util.ArrayList;
import java.util.List;

public class LabelingSystem {
    public static final double MAX_SODIUM_PER_100G = 320;
    public static final double MAX_SODIUM_PER_BEVERAGE_NO_CALORIES = 45;

    public static Food checkSodium(Food food){
        // Empty the warnings map
        if(food.getCategory() == Category.BEVERAGES){
            if(food.getCalories() == 0 && food.getSodium() >= MAX_SODIUM_PER_BEVERAGE_NO_CALORIES){
                ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
                w.add(Warnings.HIGH_SODIUM);
                food.setWarnings(w);
            }
        } else {
            double sodiumPer100g = food.getSodium() / food.getServingSize() * 100;
            if (sodiumPer100g >= MAX_SODIUM_PER_100G) {
                ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
                w.add(Warnings.HIGH_SODIUM);
                food.setWarnings(w);
            }
        }
        return food;
    }

    public static Food checkTransFats(Food food){
        double energyFromTransFatsPercentage = food.getTransFats() * 9 / food.getCalories() * 100;
        if (energyFromTransFatsPercentage > 1) {
            ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
            w.add(Warnings.HIGH_TRANS_FATS);
        }
        return food;
    }

    public static Food checkSaturatedFats(Food food){
        double energyFromSaturatedFatsPercentage = food.getSaturatedFats() * 9 / food.getCalories() * 100;
        if (energyFromSaturatedFatsPercentage > 10) {
            ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
            w.add(Warnings.HIGH_SATURATED_FATS);
        }
        return food;
    }
}
