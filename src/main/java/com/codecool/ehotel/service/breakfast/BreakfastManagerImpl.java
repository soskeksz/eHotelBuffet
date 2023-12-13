package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;
import com.codecool.ehotel.service.report.Report;
import com.codecool.ehotel.service.report.ReportImpl;

import java.time.LocalTime;
import java.util.*;

public class BreakfastManagerImpl implements BreakfastManager {

    private final BuffetService buffetService;
    public final Report report;
    private LocalTime currentTime;
    private Logger logger;

    public BreakfastManagerImpl(BuffetService buffetService){
        this.buffetService = buffetService;
        this.report = new ReportImpl();
        this.currentTime = LocalTime.of(6, 0,0);
        this.logger = new ConsoleLogger();
    }

    @Override
    public void serve(List<Guest> guests) {
        List<BreakfastGroup> groups = generateBreakfastGroups(guests);

        for(BreakfastGroup group : groups){

          // fill the buffet
            logger.logInfo("Refilling buffet...");
            MealType[] types = MealType.values();
            for(MealType type : types){
                buffetService.cookBatch(1, type);
            }

          // guests eat food (write the report)
            logger.logInfo("Commencing breakfast cycle for the " + group.time() + " group");
            for (Guest guest : group.guests()) {
                if (buffetService.consumeFreshest(guest)) {
                    report.addHappyGuest();
                } else {
                    report.addUnhappyGuest();
                }
            }

          // discard waste (write the report)
            report.addWasteCost(buffetService.collectWaste(MealDurability.SHORT, currentTime.minusMinutes(90)));

          // change time
            currentTime = currentTime.plusMinutes(30);
        }
        report.addWasteCost(buffetService.collectWaste(MealDurability.SHORT, currentTime));
        report.addWasteCost(buffetService.collectWaste(MealDurability.MEDIUM, currentTime));
        System.out.println(report);
    }

    @Override
    public List<BreakfastGroup> generateBreakfastGroups(List<Guest> guests) {

        List<HashSet<Guest>> guestGroups = generateGuestGroups(guests);
        List<BreakfastGroup> breakfastGroups = new ArrayList<>();

        for (int i = 0; i < guestGroups.size(); i++) {
            BreakfastGroup breakfastGroup = new BreakfastGroup(guestGroups.get(i), getTimes().get(i));
            breakfastGroups.add(breakfastGroup);
        }

        return breakfastGroups;
    }

    private List<LocalTime> getTimes() {
        List<LocalTime> times = new ArrayList<>();
        LocalTime initialTime = LocalTime.parse("06:00");
        times.add(initialTime);
        for (int i = 0; i < 6; i++) {
            LocalTime nextTime = times.get(i).plusMinutes(30);
            times.add(nextTime);
        }
        return times;
    }

    private List<HashSet<Guest>> generateGuestGroups(List<Guest> guests) {

        List<HashSet<Guest>> groups = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            HashSet<Guest> setOfGuests = new HashSet<>();
            groups.add(setOfGuests);
        }

        for (Guest guest : guests) {
            groups.get((int) randomGroupIndex()).add(guest);
        }

        return groups;
    }

    private double randomGroupIndex() {
        return Math.floor(Math.random()*7);
    }
}
