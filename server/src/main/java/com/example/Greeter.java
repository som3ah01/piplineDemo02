package com.example;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {

  }

  /**
 * This is a @param someone final pramter.
 * This is a @return  final pramter.
 */
  public final String greet(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}
