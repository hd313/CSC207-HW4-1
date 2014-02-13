package edu.grinnell.csc207.boygraem.cohnhann.ryansyd.utils;

/*
 * Authors: Sydney Ryan, Hannah Cohn, Graeme Boy
 */

import java.math.BigInteger;

public class Fraction
{
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  // Build a new fraction with numerator num and denominator denom.
  public Fraction(BigInteger num, BigInteger denom) throws Exception
  {
    this.num = num;
    this.denom = denom;
  } // Fraction(BigInteger, BigInteger)

  // Build a new fraction with numerator num and denominator denom.
  public Fraction(int num, int denom) throws Exception
  {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // Fraction(int, int)

  public Fraction(String frac)
  {
    String[] parts = frac.split("/");
    this.num = new BigInteger(parts[0]);
    this.denom = new BigInteger(parts[1]);

  }

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  // get the numerator of the fraction
  public BigInteger numerator()
  {
    return this.num;
  } // numerator()

  // get the denominator of the fraction
  public BigInteger denominator()
  {
    return this.denom;
  }// denominator()

  // multiplies this and another fraction
  public Fraction multiply(Fraction mult)
    throws Exception
  {
    Fraction ans =
        new Fraction(this.num.multiply(mult.num),
                     this.denom.multiply(mult.denom));
    return ans.simplify();
  } // multiply(int)

  // Add this rational number to another fraction.
  // note: not simplified
  public Fraction add(Fraction other)
    throws Exception
  {
    Fraction ans =
        new Fraction(
                     (this.num.multiply(other.denom).add(this.denom.multiply(other.num))),
                     this.denom.multiply(other.denom));

    return ans.simplify();
  } // add(fraction)
  
  //Subtract
  public Fraction subtract(Fraction other) throws Exception
  {
    return (this.add(other.multiply(new Fraction("-1/1"))));
  }

  public Fraction divide(Fraction other)
    throws Exception
  {
    return new Fraction(other.denom.multiply(this.num),
                        this.denom.multiply(other.num)).simplify();
  }

  /**
   * Round the rational number up to a whole number. (Mutator)
   */
  public BigInteger roundUp()
  {
    if ((this.num.compareTo(new BigInteger("0")) == 0))
      {
        return BigInteger.valueOf(0);
      }
    else if (this.isNegative())
      {
        return this.num.divide(this.denom);
      }
    else
      return this.num.divide(this.denom).add(BigInteger.valueOf(1));
  }// roundUp()

  /**
   * Round the rational number down to a whole number. (Mutator)
   */
  public BigInteger roundDown()
  {
    BigInteger first = this.num.divide(this.denom);
    // checks if only one of the numerator or denom is negative
    if (this.isNegative())
      return first.subtract(new BigInteger("1"));
    else
      return first;
  }// roundDown()
  

  public Boolean isNegative()
  {
    return ((this.num.compareTo(new BigInteger("0")) >= 0) ^ (this.denom.compareTo(new BigInteger(
                                                                                                  "0")) >= 0));
  }

  public Fraction simplify()
    throws Exception
  {
    BigInteger gcd = this.num.gcd(this.denom);
    if ((this.num.compareTo(new BigInteger("0")) <= 0)
        && (this.denom.compareTo(new BigInteger("0")) <= 0))
      {
        this.num = this.num.abs();
        this.denom = this.denom.abs();
      }
    else if (this.denom.compareTo(BigInteger.valueOf(0)) < 0)
      {
        this.num = this.num.multiply(BigInteger.valueOf(-1));
        this.denom = this.denom.abs();
      }
    Fraction simple =
        new Fraction(this.num.divide(gcd), this.denom.divide(gcd));
    return simple;
  }

  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString()
  {
    if ((this.num.compareTo(new BigInteger("0")) == 0))
      {
        return "0";
      }
    else if (this.denom.compareTo(new BigInteger("1")) == 0)
      {
        return this.num.toString();
      }
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num.toString() + "/" + this.denom.toString();
  } // toString()
}// Fraction
