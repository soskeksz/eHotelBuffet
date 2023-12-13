package com.codecool.ehotel.service.report;

public class ReportImpl implements Report{
  private int unhappyGuests;
  private int happyGuests;
  private int wastedCost;

  @Override
  public void addUnhappyGuest() {
    unhappyGuests++;
  }

  @Override
  public void addHappyGuest() {
    happyGuests++;
  }

  @Override
  public void addWasteCost(int cost) {
    wastedCost+=cost;
  }

  @Override
  public String toString(){
    return "Happy guests: " + happyGuests + "\nUnhappy guests: " + unhappyGuests + "\n Wasted money: " + wastedCost;
  }
}
