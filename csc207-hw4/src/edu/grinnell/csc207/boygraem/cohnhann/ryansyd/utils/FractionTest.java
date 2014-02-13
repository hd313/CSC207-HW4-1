package edu.grinnell.csc207.boygraem.cohnhann.ryansyd.utils;

/*
 * Authors: Sydney Ryan, Hannah Cohn, Graeme Boy
 */

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class FractionTest
{

  @Test
  public void testNumerator()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, 6);
    Fraction f3 = new Fraction(-2, 3);
    assertEquals("1", new BigInteger("0"), f1.num);
    assertEquals("2", new BigInteger("5"), f2.num);
    assertEquals("3", new BigInteger("-2"), f3.num);
  }

  @Test
  public void testDenominator()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, -6);
    Fraction f3 = new Fraction(-2, 3);
    assertEquals("1", new BigInteger("1"), f1.denom);
    assertEquals("2", new BigInteger("-6"), f2.denom);
    assertEquals("3", new BigInteger("3"), f3.denom);
  }

  @Test
  public void testMultiply()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, -6);
    Fraction f3 = new Fraction(-2, 3);
    Fraction f4 = new Fraction(3, 5);
    assertEquals("0", f1.multiply(f2).toString());
    assertEquals("5/9", f2.multiply(f3).toString());
    assertEquals("4/9", f3.multiply(f3).toString());
    assertEquals("-1/2", f2.multiply(f4).toString());
  }

  @Test
  public void testSimplify()
    throws Exception
  {
    Fraction f1 = new Fraction(5, 3);
    Fraction f2 = new Fraction(0, 7);
    Fraction f3 = new Fraction(144, 12);
    Fraction f4 = new Fraction(-16, 8);
    Fraction f5 = new Fraction(-20, -10);

    assertEquals("5/3", f1.simplify().toString());
    assertEquals("0", f2.simplify().toString());
    assertEquals("12", f3.simplify().toString());
    assertEquals("-2", f4.simplify().toString());
    assertEquals("2", f5.simplify().toString());

  }

  @Test
  public void testAdd()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, -6);
    Fraction f3 = new Fraction(-2, 3);
    assertEquals("-5/6", f1.add(f2).toString());
    assertEquals("-3/2", f2.add(f3).toString());
    assertEquals("-4/3", f3.add(f3).toString());
  }

  @Test
  public void testRoundUp()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, 6);
    Fraction f3 = new Fraction(10, 3);
    Fraction f4 = new Fraction(-2, 3);

    assertEquals(new BigInteger("0"), f1.roundUp());
    assertEquals(new BigInteger("1"), f2.roundUp());
    assertEquals(new BigInteger("4"), f3.roundUp());
    assertEquals(new BigInteger("0"), f4.roundUp());

  }

  @Test
  public void testRoundDown()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, 6);
    Fraction f3 = new Fraction(10, 3);
    Fraction f4 = new Fraction(-2, 3);

    assertEquals(new BigInteger("0"), f1.roundDown());
    assertEquals(new BigInteger("0"), f2.roundDown());
    assertEquals(new BigInteger("3"), f3.roundDown());
    assertEquals(new BigInteger("-1"), f4.roundDown());
  }

  @Test
  public void testDivide()
    throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(1, 4);
    Fraction f3 = new Fraction(10, 3);
    Fraction f4 = new Fraction(-2, 3);
    
    assertEquals("-3/8", f2.divide(f4).toString());
    assertEquals("40/3", f3.divide(f2).toString());
    assertEquals("0", f1.divide(f3).toString());
  }
  
  @Test
  public void testSubtract()
  throws Exception
  {
    Fraction f1 = new Fraction(0, 1);
    Fraction f2 = new Fraction(5, -6);
    Fraction f3 = new Fraction(-2, 3);
    Fraction f4 = new Fraction(1, 4);
    
    assertEquals("5/6", f1.subtract(f2).toString());
    assertEquals("11/12", f4.subtract(f3).toString());
    assertEquals("-1/4", f1.subtract(f4).toString());
  }
}
