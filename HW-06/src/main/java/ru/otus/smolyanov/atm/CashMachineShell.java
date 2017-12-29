package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */

import java.io.PrintStream;
import java.io.InputStream;

public interface CashMachineShell {

  void start();

  void setPrintStream(PrintStream printStream);

  void setInputStream(InputStream inputStream);
}
