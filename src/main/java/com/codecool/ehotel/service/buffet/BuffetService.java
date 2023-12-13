package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.*;

import java.time.LocalTime;

public interface BuffetService {

    void cookBatch(int amount, MealType mealType);
    boolean consumeFreshest(Guest guest);
    int collectWaste(MealDurability mealDurability, LocalTime cookedAt);

}
