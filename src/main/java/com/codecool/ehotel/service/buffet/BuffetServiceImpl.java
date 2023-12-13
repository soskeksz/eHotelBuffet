package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.logger.Logger;

import java.util.Random;
import java.time.LocalTime;
import java.util.*;

public class BuffetServiceImpl implements BuffetService {

    private final Logger logger;
    private final Buffet buffet;
    private final Random random;

    public BuffetServiceImpl(Logger logger, Buffet buffet){
        this.logger = logger;
        this.buffet = buffet;
        this.random = new Random();
    }

    @Override
    public void cookBatch(int amount, MealType mealType) {
        List<Meal> meals = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
             meals.add(new Meal(mealType, LocalTime.now(), mealType.getCost(), UUID.randomUUID()));
        }
        logger.logInfo("New batch of " + mealType + " ready");
        buffet.addMeal(meals);
    }

    @Override
    public boolean consumeFreshest(Guest guest) {
        List<MealType> preferredMealType = guest.guestType().getMealPreferences();
        MealType meal = preferredMealType.get(random.nextInt(preferredMealType.size()));
        Optional<Meal> freshestMealOfType = findFreshestMealOfType(buffet, meal);

        if (freshestMealOfType.isPresent()) {
            buffet.removeMeal(freshestMealOfType.get());
            logger.logInfo(freshestMealOfType.get().mealType() + " consumed");
            return true;
        }
        logger.logInfo("Preferred meal not found in buffet");
        return false;
    }

    @Override
    public int collectWaste(MealDurability mealDurability, LocalTime expirationTime) {
        Set<Meal> mealsToRemove = findMealsToRemove(mealDurability, expirationTime, buffet);

        int costCounter = 0;

        for (Meal meal : mealsToRemove) {
            costCounter += meal.mealType().getCost();
            buffet.removeMeal(meal);
        }

        logger.logInfo("Waste collected. Total cost: " + costCounter);
        return costCounter;
    }

    private Set<Meal> findMealsToRemove(MealDurability mealDurability, LocalTime expirationTime, Buffet buffet) {
        List<Meal> allMealsOfDurability = buffet.getMeals().stream().filter(meal -> meal.mealType().getDurability().equals(mealDurability)).toList();
        List<Meal> expiredMeals = buffet.getMeals().stream().filter(meal -> meal.cookedAt().isAfter(expirationTime)).toList();

        Set<Meal> mealsToRemove = new HashSet<>();
        mealsToRemove.addAll(allMealsOfDurability);
        mealsToRemove.addAll(expiredMeals);

        return mealsToRemove;
    }

    private Optional<Meal> findFreshestMealOfType(Buffet buffet, MealType mealType) {
        return buffet.getMeals().stream()
                .filter(meal -> meal.mealType().equals(mealType))
                .max(Comparator.comparing(Meal::cookedAt));
    }
}
