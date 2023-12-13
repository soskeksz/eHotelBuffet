package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.BreakfastGroup;
import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public interface BreakfastManager {
  void serve(List<Guest> guests);

  public List<BreakfastGroup> generateBreakfastGroups(List<Guest> guests);

}
