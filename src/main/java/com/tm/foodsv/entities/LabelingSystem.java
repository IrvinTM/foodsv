package com.tm.foodsv.entities;

public record LabelingSystem() {
    private static final double HIGH_SUGAR_THRESHOLD = 22.5; // grams per serving
    private static final double HIGH_SODIUM_THRESHOLD = 1.5; // grams per serving
    private static final double HIGH_FAT_THRESHOLD = 17.5; // grams per serving
    private static final int HIGH_CALORIES_THRESHOLD = 400;

    public String checkLabels(Food food) {
        StringBuilder labels = new StringBuilder();

        if (food.getSugar() > HIGH_SUGAR_THRESHOLD) {
            labels.append("High in Sugar | ");
        }
        if (food.getSodium() > HIGH_SODIUM_THRESHOLD) {
            labels.append("High in Sodium | ");
        }
        if (food.getFats() > HIGH_FAT_THRESHOLD) {
            labels.append("High in Fat | ");
        }
        if (food.getCalories() > HIGH_CALORIES_THRESHOLD) {
            labels.append("High in Calories | ");
        }

        // Remove trailing " | " if present
        if (!labels.isEmpty()) {
            labels.setLength(labels.length() - 3);
        } else {
            labels.append("No warnings");
        }

        return labels.toString();
    }
}
