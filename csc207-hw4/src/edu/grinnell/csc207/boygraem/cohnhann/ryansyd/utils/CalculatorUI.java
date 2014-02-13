package edu.grinnell.csc207.boygraem.cohnhann.ryansyd.utils;

/*
 * Authors: Sydney Ryan, Hannah Cohn, Graeme Boy
 */

import java.io.PrintWriter;

public class CalculatorUI
{
  public static void main(String args[])
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    Calculator calc = new Calculator();

    java.io.BufferedReader eyes;
    java.io.InputStreamReader istream;
    istream = new java.io.InputStreamReader(System.in);
    eyes = new java.io.BufferedReader(istream);

    pen.println("Welcome to CalculatorUI. Type 'exit' to exit.");

    while (true)
      {
        try
          {

            String input = eyes.readLine();

            if (input.equals("exit"))
              {
                pen.println("Goodbye. See you soon... :(");
                pen.println("...it's lonely in here...");
                pen.println(";'(");
                break;
              }// if exit
            else
              {
                pen.println(calc.evaluate(input));
              }// else
          }
        catch (Exception e)
          {
            pen.println("Error. Self-Destructing in 3..2..1.. BOOM");
          }
      }// while

    eyes.close();
    pen.close();

  }// main

}// CalculatorUI
