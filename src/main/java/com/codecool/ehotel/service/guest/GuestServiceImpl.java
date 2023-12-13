package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.FullName;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.model.TimeDuration;

import java.util.Random;
import java.time.LocalDate;
import java.util.*;

public class GuestServiceImpl implements GuestService {

    Random random = new Random();
    private final TimeDuration seasonDuration;
    private final Set<Guest> allGuests;

    public GuestServiceImpl(LocalDate start, LocalDate end){
      this.allGuests = new HashSet<>();
      this.seasonDuration = new TimeDuration(start, end);
    }

    @Override
    public Guest generateRandomGuest() {
        TimeDuration generatedDuration = createGuestStayDuration();
        return new Guest(FullName.getRandomName(), GuestType.randomGuestType(), generatedDuration);
    }

    private TimeDuration createGuestStayDuration(){
        int diffBetween = seasonDuration.getDurationInDays();
        int reservedDays;
        if (diffBetween > 7){ // max reservation is 7 day
            reservedDays = random.nextInt(1,7);
        } else {
            reservedDays = random.nextInt(1, diffBetween);
        }
        LocalDate start = seasonDuration.start().plusDays(random.nextInt(0, diffBetween - reservedDays));
        LocalDate end = start.plusDays(reservedDays + 1); // +1 to include max limit (seasonEnd) ???
        return new TimeDuration(start, end);
    }

    @Override
    public Set<Guest> getGuestsForDay(LocalDate date) {
        Set<Guest> setOfGuests = new HashSet<>();

        for (Guest guest : allGuests) {
            if (isCheckedIn(guest, date)) {
                setOfGuests.add(guest);
            }
        }
        return setOfGuests;
    }

    private boolean isCheckedIn(Guest guest, LocalDate date) {
        LocalDate start = guest.reservationDuration().start();
        LocalDate end = guest.reservationDuration().end();
        return start.isBefore(date) && !end.isBefore(date);
    }

    public Set<Guest> getAllGuests() {
        return allGuests;
    }

    public void generateGuests(int amount) {
        for (int i = 0; i < amount; i++) {
            allGuests.add(generateRandomGuest());
        }
    }
}
