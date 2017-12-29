package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import java.util.Collection;

public interface CashMachine {

  // старт
  void start();

  // узнать баланс
  long getBalance();

  // снять наличные
  Collection<Banknote> cachWithdrawal(long amount) throws NotEnoughMoneyException, ImpossibleToGiveSpecifiedAmountException;

  // внести наличные
  void cashEntry(Collection<Banknote> banknoteBundle);
}
