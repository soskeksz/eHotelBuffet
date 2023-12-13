package com.codecool.ehotel.model;

import com.codecool.ehotel.service.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class Buffet {
    private final List<Meal> meals;
    public Buffet(){
        this.meals = new ArrayList<>();
    }

    //method overloading flex coming up:

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public void addMeal(List<Meal> meals) { this.meals.addAll(meals); }

    public void removeMeal(Meal meal) {
        this.meals.removeIf(food -> food.equals(meal));
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
