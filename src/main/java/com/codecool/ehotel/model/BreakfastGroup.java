package com.codecool.ehotel.model;

import java.time.LocalTime;
import java.util.HashSet;

public record BreakfastGroup(HashSet<Guest> guests, LocalTime time) {
}
