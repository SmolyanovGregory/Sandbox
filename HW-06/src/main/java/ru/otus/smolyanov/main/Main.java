package ru.otus.smolyanov.main;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import ru.otus.smolyanov.atm.*;
import java.util.Collection;

import java.io.IOException;
import java.util.LinkedList;

public class Main {

  private void run() throws IOException, NotEnoughMoneyException {

//    Listener listener = new Listener(System.in);
//    listener.start();
//    ViewerImpl viewerImpl = new ViewerImpl(System.out);
//    viewerImpl.showMenu();
    CashMachine cashMachine = new CashMachineImpl();
    cashMachine.start();

    Collection<Banknote> pack = new LinkedList<>();
    pack.add(Banknote.FIVE_HUNDRED);
    pack.add(Banknote.FIVE);
    pack.add(Banknote.TEN);
    pack.add(Banknote.TEN);

    cashMachine.cahsEntry(pack);

    System.out.println("Balance: "+cashMachine.getBalance());

    try {
      Collection<Banknote> pack2 = cashMachine.cachWithdrawal(400);
    } catch (ImpossibleToGiveSpecifiedAmountException e) {
      System.out.println(e.getMessage());
    }

    System.out.println("Balance: "+cashMachine.getBalance());
  }

  public static void main(String ... args) {
    try {
      Main main = new Main();
      main.run();
    } catch (Exception e) {
      System.out.println("!!! Error: "+e.getMessage());
      e.printStackTrace();
    }
  }
}
