package com.codecool.ehotel.model;

public record Guest(FullName name, GuestType guestType, TimeDuration reservationDuration) {
}
