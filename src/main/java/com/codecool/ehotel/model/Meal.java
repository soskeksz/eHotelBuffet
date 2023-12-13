package com.codecool.ehotel.model;


import java.time.LocalTime;
import java.util.UUID;

public record Meal(MealType mealType, LocalTime cookedAt, double value, UUID uuid) { //UUID.randomUUID();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Meal meal)) {
            return false;
        }

        return meal.uuid.equals(this.uuid);
    }
}
