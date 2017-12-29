package ru.otus.smolyanov.main;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import ru.otus.smolyanov.atm.*;

public class Main {

  private void run() {
    CashMachineShellImpl cashMachineShell = new CashMachineShellImpl(new CashMachineImpl());
    cashMachineShell.setInputStream(System.in);
    cashMachineShell.setPrintStream(System.out);
    cashMachineShell.start();
  }

  public static void main(String ... args) {
    try {
      Main main = new Main();
      main.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
