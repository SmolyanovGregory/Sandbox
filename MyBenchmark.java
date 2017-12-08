package ru.otus;

import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Fork(50)
public class MyBenchmark {

  private int plainV;
  private volatile int volatileV;

  @Benchmark
  public int baseline() {
    return 42;
  }

  @Benchmark
  public int incrPlain() {
    return plainV++;
  }

  @Benchmark
  public int incrVolatile() {
    return volatileV++;
  }

}