package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import java.io.PrintStream;

class GetBalanceCommand implements Command{

  private final CashMachine cashMachine;
  private final PrintStream printStream;

  public GetBalanceCommand(CashMachine cashMachine, PrintStream printStream) {
    this.cashMachine = cashMachine;
    this.printStream = printStream;
  }

  @Override
  public void execute() {
    printStream.println("Current balance: "+cashMachine.getBalance());
  }
}
