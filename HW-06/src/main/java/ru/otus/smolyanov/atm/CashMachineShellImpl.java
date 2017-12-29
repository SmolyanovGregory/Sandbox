package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collection;
import java.util.StringTokenizer;

public class CashMachineShellImpl implements CashMachineShell {

  private final String COMMAND_BALANCE = "1";
  private final String COMMAND_WITHDRAWAL = "2";
  private final String COMMAND_ENTRY = "3";
  private final String START_MENU = "------------------------------\n" +
                                    "Type '"+COMMAND_BALANCE+"' for show the balance\n" +
                                    "Type '"+COMMAND_WITHDRAWAL+"' for withdrawal cash\n" +
                                    "Type '"+COMMAND_ENTRY+"' for entry cash"+
                                    "\n------------------------------";
  private final String NOT_ACCEPTED = "Banknotes of the following denominations are not accepted:";
  private final String ENTRY_CASH = "Please entry cash: ";
  private final String TAKE_MONEY = "Please take money: ";
  private final String TYPE_AMOUNT = "Please type amount: ";
  private final String CURRENT_BALANCE = "Current balance: ";
  private final String INCORRECT_COMMAND = "Incorrect command: ";

  private Scanner scanner;
  private InputStream inputStream;
  private PrintStream printStream;
  private CashMachine cashMachine;

  public CashMachineShellImpl(CashMachine cashMachine) {
    this.cashMachine = cashMachine;
  }

  @Override
  public void start() {
    cashMachine.start();

    startListener();
  }

  @Override
  public void setPrintStream(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
    scanner = new Scanner(this.inputStream);
  }

  private void showStartMenu() {
    printStream.println(START_MENU);
  }

  private void startListener() {

    while (true) {
      showStartMenu();
      dispatchCommand(getUserEntry());
    }
  }

  private void dispatchCommand(String command) {
    switch (command) {
      case COMMAND_BALANCE:
        processCommandBalance();
        break;
      case COMMAND_WITHDRAWAL:
        processCommandWithdrawal();
        break;
      case COMMAND_ENTRY:
        processCommandEntry();
        break;
      default:
        printStream.println(INCORRECT_COMMAND+command);
        break;
    }
  }

  private void processCommandBalance(){
    printStream.println(CURRENT_BALANCE+cashMachine.getBalance());
  }

  private void processCommandWithdrawal(){
    printStream.println(TYPE_AMOUNT);

    long amount = Long.valueOf(getUserEntry());
    try {
      Collection<Banknote> banknoteBundle = cashMachine.cachWithdrawal(amount);
      printStream.println(TAKE_MONEY+banknoteBundleToString(banknoteBundle)+"\n");

    } catch (NotEnoughMoneyException | ImpossibleToGiveSpecifiedAmountException e) {
      printStream.println(e.getMessage());
    }
  }

  private void processCommandEntry() {
    printStream.println(ENTRY_CASH);

    Collection<String> notSupportedBanknotes = new LinkedList<>();
    Collection<Banknote> banknoteBundle = new LinkedList<>();
    StringTokenizer tokenizer = new StringTokenizer(getUserEntry());

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();

      boolean isValidDenomination = false;
      for (Banknote banknote : Banknote.values()) {
        if (banknote.getdDenomination() == Long.valueOf(token)) {
          banknoteBundle.add(banknote);
          isValidDenomination = true;
        }
      }
      if (!isValidDenomination)
        notSupportedBanknotes.add(token);
    }

    cashMachine.cashEntry(banknoteBundle);

    if (!notSupportedBanknotes.isEmpty()) {
      StringBuffer sb = new StringBuffer();
      sb.append(NOT_ACCEPTED);
      for(String banknote: notSupportedBanknotes) {
        sb.append(" ").append(banknote);
      }
      printStream.println(sb.toString());
    }
  }

  private String getUserEntry() {
    return scanner.nextLine();
  }

  private String banknoteBundleToString(Collection<Banknote> banknoteBundle) {
    StringBuffer result = new StringBuffer();
    for (Banknote banknote : banknoteBundle) {
      if (result.length() > 0) {
         result.append(" ");
      }
      result.append(banknote.getdDenomination());
    }
    return result.toString();
  }
}
