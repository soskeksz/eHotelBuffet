package com.codecool.ehotel.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record TimeDuration(LocalDate start, LocalDate end) {
  public int getDurationInDays(){
    return (int) ChronoUnit.DAYS.between(start, end);
  }
}
