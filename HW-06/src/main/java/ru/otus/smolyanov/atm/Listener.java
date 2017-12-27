package ru.otus.smolyanov.atm;

/**
 * Created by Gregory Smolyanov.
 * <p>
 * Home work 06 - ATM
 */
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Listener {

  private final InputStream inputStream;

  public Listener(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public void start() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

    String str;

    while ((str = br.readLine()) != null) {
      StringTokenizer tok = new StringTokenizer(str);

      while (tok.hasMoreTokens()) {
        System.out.println(">>> "+tok.nextToken());
      }
    }

    br.close();

  }
}
