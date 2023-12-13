package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.Set;

public interface GuestService {

    Guest generateRandomGuest();

    Set<Guest> getGuestsForDay(LocalDate date);
    void generateGuests(int amount);
    Set<Guest> getAllGuests();
}
