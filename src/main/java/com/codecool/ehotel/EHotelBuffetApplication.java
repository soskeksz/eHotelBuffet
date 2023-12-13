package com.codecool.ehotel;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.breakfast.BreakfastManagerImpl;
import com.codecool.ehotel.service.buffet.*;
import com.codecool.ehotel.service.guest.*;
import com.codecool.ehotel.service.logger.*;

import java.time.LocalDate;
import java.util.Set;

public class EHotelBuffetApplication {

  public static void main(String[] args) {
    LocalDate seasonStart = LocalDate.parse("2023-08-23");
    LocalDate seasonEnd = LocalDate.parse("2023-08-30");

    Logger logger = new ConsoleLogger();
    Buffet buffet = new Buffet();

    GuestService guestService = new GuestServiceImpl(seasonStart, seasonEnd);
    guestService.generateGuests(20);

    BuffetService buffetService = new BuffetServiceImpl(logger, buffet);

    BreakfastManager breakfastManager = new BreakfastManagerImpl(buffetService);
    breakfastManager.serve(guestService.getAllGuests().stream().toList());
  }

  private static GuestService createGuestsAndDisplayThem(){
    LocalDate seasonStart = LocalDate.parse("2023-08-23");
    LocalDate seasonEnd = LocalDate.parse("2023-08-30");

    GuestService guestService= new GuestServiceImpl(seasonStart, seasonEnd);
    guestService.generateGuests(12);

    Set<Guest> guests = guestService.getGuestsForDay(LocalDate.parse("2023-08-25"));
    for(Guest guest : guests){
      System.out.println(guest);
    }
    Set<Guest> allGuest = guestService.getAllGuests();
    System.out.println("\n all guests: \n");
    for(Guest guest : allGuest) {
      System.out.println(guest);
    }
    return guestService;
  }
}
