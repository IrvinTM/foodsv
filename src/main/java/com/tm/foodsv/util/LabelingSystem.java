package com.tm.foodsv.util;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;

import java.util.ArrayList;

public class LabelingSystem {
    public static final double MAX_SODIUM_PER_100G = 320;
    public static final double MAX_SODIUM_PER_BEVERAGE_NO_CALORIES = 45;

    public static Food addLabels(Food food){
        food = checkSodium(food);
        food = checkTransFats(food);
        food = checkSaturatedFats(food);
        food = checkSugar(food);
        food = checkCalories(food);
        return food;
    }

    private static Food checkSodium(Food food){
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

    private static Food checkTransFats(Food food){
        double energyFromTransFatsPercentage = food.getTransFats() * 9 / food.getCalories() * 100;
        if (energyFromTransFatsPercentage > 1) {
            ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
            w.add(Warnings.HIGH_TRANS_FATS);
        }
        return food;
    }

    private static Food checkSaturatedFats(Food food){
        double energyFromSaturatedFatsPercentage = food.getSaturatedFats() * 9 / food.getCalories() * 100;
        if (energyFromSaturatedFatsPercentage > 10) {
            ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
            w.add(Warnings.HIGH_SATURATED_FATS);
        }
        return food;
    }

    private static Food checkSugar(Food food){
        double energyFromSugarPercentage = ((food.getSugar() * 4) / food.getCalories()) * 100;
        if (energyFromSugarPercentage > 10) {
            ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
            w.add(Warnings.HIGH_SUGAR);
        }
        return food;
    }

    private static Food checkCalories(Food food){
        if (food.getCategory() == Category.BEVERAGES) {
            double energyFromSugar = food.getSugar() * 4;
            double caloriesPer100Ml = (food.getCalories() / food.getServingSize()) * 100;
            if (caloriesPer100Ml > 70 || energyFromSugar > 8) {
                ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
                w.add(Warnings.HIGH_CALORIES);
            }
        }else {
            double caloriesPer100g = (food.getCalories() / food.getServingSize()) * 100;

            if (caloriesPer100g > 275) {
                ArrayList<Warnings> w = (ArrayList<Warnings>) food.getWarnings();
                w.add(Warnings.HIGH_CALORIES);
            }
        }
        return food;
    }
}
