package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import java.io.PrintStream;

public class ViewerImpl implements Viewer{

  private final PrintStream printStream;

  public ViewerImpl(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void showMenu() {
    printStream.println("Type 1 for ");
  }

  @Override
  public void showMessage() {

  }
}
