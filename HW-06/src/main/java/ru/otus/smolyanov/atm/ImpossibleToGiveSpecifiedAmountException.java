package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

public class ImpossibleToGiveSpecifiedAmountException extends Exception {
  public ImpossibleToGiveSpecifiedAmountException() {
    super("The ATM cannot give the specified amount.");
  }
}
