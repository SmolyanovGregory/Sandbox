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

  private final Scanner scanner;
  private final PrintStream printStream;
  private final CashMachine cashMachine;

  public CashMachineShellImpl(CashMachine cashMachine, InputStream inputStream, PrintStream printStream) {
    this.cashMachine = cashMachine;
    scanner = new Scanner(inputStream);
    this.printStream = printStream;
  }

  @Override
  public void start() {
    cashMachine.start();

    startListener();
  }

  private void showStartMenu() {
    printStream.println("------------------------------\n" +
        "Type '"+COMMAND_BALANCE+"' for show the balance\n" +
        "Type '"+COMMAND_WITHDRAWAL+"' for withdrawal cash\n" +
        "Type '"+COMMAND_ENTRY+"' for entry cash"+
        "\n------------------------------");
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
        printStream.println("Incorrect command: "+command);
        break;
    }
  }

  private void processCommandBalance(){
    printStream.println("Current balance: "+cashMachine.getBalance());
  }

  private void processCommandWithdrawal(){
    printStream.println("Please type amount: ");

    long amount = Long.valueOf(getUserEntry());
    try {
      Collection<Banknote> banknoteBundle = cashMachine.cashWithdrawal(amount);
      printStream.println("Please take money: "+banknoteBundleToString(banknoteBundle)+"\n");

    } catch (NotEnoughMoneyException | ImpossibleToGiveSpecifiedAmountException e) {
      printStream.println(e.getMessage());
    }
  }

  private void processCommandEntry() {
    printStream.println("Please entry cash: ");

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
      StringBuilder sb = new StringBuilder();
      sb.append("Banknotes of the following denominations are not accepted:");
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
    StringBuilder result = new StringBuilder();
    for (Banknote banknote : banknoteBundle) {
      if (result.length() > 0) {
         result.append(" ");
      }
      result.append(banknote.getdDenomination());
    }
    return result.toString();
  }
}
