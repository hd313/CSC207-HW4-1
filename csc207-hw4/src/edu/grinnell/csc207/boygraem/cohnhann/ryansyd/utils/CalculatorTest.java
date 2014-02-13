package edu.grinnell.csc207.boygraem.cohnhann.ryansyd.utils;

/*
 * Authors: Sydney Ryan, Hannah Cohn, Graeme Boy
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest
{

  @Test
  public void test()
  throws Exception
  {
    Calculator calc = new Calculator();
    // multiplying
    assertEquals("1", "4/15", calc.evaluate(new Fraction(1,3) +" * " + new Fraction(4,5)));
    assertEquals("2", "0", calc.evaluate(0 +" * " + new Fraction(4,5)));
    assertEquals("3", "0", calc.evaluate(new Fraction(1,3) +" * " + 0));
    assertEquals("4", "1/3", calc.evaluate(new Fraction(1,3) +" * " + new Fraction(1,1)));
    // adding
    assertEquals("5", "17/15", calc.evaluate(new Fraction(1,3) +" + " + new Fraction(4,5)));
    assertEquals("6", "1/3", calc.evaluate(new Fraction(1,3) +" + " + 0));
    assertEquals("7", "7/3", calc.evaluate(new Fraction(1,3) +" + " + new Fraction(2,1)));
    // multiple expressions
    String exp1 = new Fraction(1,3) +" * " + new Fraction(4,5);
    String exp2 = new Fraction(0,3) +" * " + new Fraction(4,5);
    String exp3 = new Fraction(1,3) +" + " + new Fraction(0,5);
    String [] exp123 = new String[3];
    exp123[0] = exp1;
    exp123[1] = exp2;
    exp123[2] = exp3;
    assertEquals("8", "4/15", calc.evaluate(exp123[0]));
    assertEquals("9", "0", calc.evaluate(exp123[1]));
    assertEquals("10", "1/3", calc.evaluate(exp123[2]));
    // using registers
    String [] stuffToDo = new String [6];
    stuffToDo[0] = "r1 = 4 + 5";
    stuffToDo[1] = "r2 = 10 * 1";
    stuffToDo[2] = "r2";
    stuffToDo[3] = "r1 + r2 + 1";
    stuffToDo[4] = "r3 = r2 + r1";
    stuffToDo[5] = "r3";

    String[] answer = calc.evaluate(stuffToDo);
    assertEquals("11", "", answer[0]);
    assertEquals("12", "", answer[1]);
    assertEquals("13", "10", answer[2]);
    assertEquals("14", "20", answer[3]);
    assertEquals("15", "", answer[4]);
    assertEquals("15", "19", answer[5]);

  
  
  
  }

}