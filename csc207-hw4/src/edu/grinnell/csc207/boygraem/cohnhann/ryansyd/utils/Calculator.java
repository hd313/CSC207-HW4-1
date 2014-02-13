package edu.grinnell.csc207.boygraem.cohnhann.ryansyd.utils;

/*
 * Authors: Sydney Ryan, Hannah Cohn, Graeme Boy
 */

import java.math.BigInteger;

public class Calculator
{
  public final int STORAGE_CAPACITY = 8;
  public String[] memory = new String[STORAGE_CAPACITY];
  public Fraction[] register = new Fraction[STORAGE_CAPACITY];
  public int memoryIndex;

  public Calculator()
  {
    memoryIndex = 0;
  }

  public void setRegister(int index, Fraction value)
  {
    register[index] = value;
  }

  public void saveMemory(String nextString)
  {
    if (this.memoryIndex == STORAGE_CAPACITY)
      {
        this.memoryIndex = 0; // start writing over
      }
    this.memory[this.memoryIndex] = nextString;
    this.memoryIndex++;
  }

  public void saveParts(String[] parts)
  {
    /*
     * One thing we could do here would be to reverse this array, which would
     * mean that the most recent values would be r0 and r1, etc.
     */
    for (int i = 0; i < parts.length; i++)
      {
        this.saveMemory(parts[i]);
      }
  }

  public Fraction convertFraction(String stringIn) throws Exception
  {
    Fraction answer;
    if (isFrac(stringIn))
      {
        answer = new Fraction(stringIn);
      }
    else
      {
        answer = new Fraction(new BigInteger(stringIn), BigInteger.valueOf(1));
      }
    return answer;
  }

  public String calculate(String expression)
    throws Exception
  {
    String[] parts = expression.split(" ");
    Fraction next;
    Fraction answer;
    String nextString;
    String[] valuesToSave = new String[this.STORAGE_CAPACITY];
    int saveCount = 0;

    answer = convertFraction(parts[0]);
    valuesToSave[saveCount] = parts[0];
    saveCount++;

    for (int i = 1; i < parts.length; i++)
      {
        if (saveCount == STORAGE_CAPACITY)
          {
            saveCount = 0;
          }
        
        if (parts[i + 1].charAt(0) == 'r')
          { // User is referencing a memory item:
            // we are referencing a storage unit
            // find the storage index
            int registerIndex =
                Integer.parseInt(String.valueOf(parts[i + 1].charAt(1)));
            if (this.register[registerIndex] != null)
              {
                nextString = this.register[registerIndex].toString();
              }
            else
              {
                throw new Exception("There was no stored value at "
                                    + parts[i + 1]);
              }
          } else if (parts[i + 1].charAt(0) == 'm')
          { // User is referencing a memory item:
            // we are referencing a storage unit
            // find the storage index
            int storageItem =
                Integer.parseInt(String.valueOf(parts[i + 1].charAt(1)));
            if (this.memory[storageItem] != null)
              {
                nextString = this.memory[storageItem];
              }
            else
              {
                throw new Exception("There was no stored value at "
                                    + parts[i + 1]);
              }
          }
        else
          {
            nextString = parts[i + 1];
          }

        // Add this string value to what we have to store later in memory
        valuesToSave[saveCount] = nextString;
        saveCount++;
        
        next = convertFraction(nextString);
        

        // char ch = parts[i].charAt(0);
        switch (parts[i])
          {

            case "+":
              answer = answer.add(next);
              i++;
              break;

            case "-":
              answer = answer.subtract(next);
              i++;
              break;

            case "*":
              answer = answer.multiply(next);
              i++;
              break;

            case "/":
              answer = answer.divide(next);
              i++;
              break;

          // throw new Exception("Unrecognized Operator");
          }// switch for operation

      }// for
    // System.out.println(valuesToSave.length);
    saveParts(valuesToSave);
    /*
     * Now that we have made the calculation, we can save the numbers to storage
     */
    return answer.toString();
  }

  public String [] evaluate(String[] input) throws Exception
  {
    String [] answers = new String[input.length];
    for (int i = 0; i < input.length; i++)
      {
        answers[i] = evaluate(input[i]);
      }
    return answers;
  }
  
  public String evaluate(String input)
    throws Exception
  {

    // Set answer, which represents the first part of the expression
    // to a new Fraction.
    if (input.charAt(0) == 'r')
      {
        // The user is setting a register item.
        int registerIndex = Integer.parseInt(String.valueOf(input.charAt(1)));
        String[] parts = input.split(" ");
        String evaluation = calculate(parts[2]);
        setRegister(registerIndex, convertFraction(evaluation));

      }
    else
      {
        // the user is calculating the value of an expression
        String answer = calculate(input);
        return answer;
      }
    return "";
  }

  public Fraction getFromRegister(int index)
  {
    return this.register[index];
  }

  /*
   * Convert the Fraction storage array to string array, and return
   */
  // public String[] getStorage()
  // {
  // String storage[] = new String[this.STORAGE_CAPACITY];
  // for (int i = 0; i < this.STOAGE_CAPACITY; i++)
  // {
  // if (this.memory[i] != null)
  // {
  // storage[i] = this.memory[i].toString();
  // }
  // }
  // return storage;
  // }

  public static void main(String args[])
    throws Exception
  {

    String input = "1 + 4 / 5 * 7/2 / 2 * 18 - 3 * 2 / 3";
    Calculator cal = new Calculator();
    cal.evaluate("r0 = 5");
    cal.evaluate("r2 = 18");
    cal.evaluate("r3 = 5");
    // String answer = cal.calculate(input);
    //System.out.println(cal.getFromRegister(2).toString());

    input = "1 + 4 / 5 * 7/2 / 2 * 18 - 3 * 2 / 3 * r2";
    String answer = cal.calculate(input);
    System.out.println(answer);

  }

  public static boolean isFrac(String args)
  {
    if (args.length() > 2)
      for (int j = 0; j < args.length(); j++)
        {
          if (args.charAt(j) == '/')
            {

              return true;

            }
        }// for

    return false; // args is not a fraction
  }
}
