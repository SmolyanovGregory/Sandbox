package ru.otus.smolyanov.main;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

public class Main {

  private void run() {
    System.out.println("qqq");
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
