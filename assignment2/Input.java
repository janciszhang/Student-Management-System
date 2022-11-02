package assignment2;

import java.util.Scanner;

/**
 * This class Input get input content via Scanner class.
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */

public class Input
{
    /**
     * Default constructor for class Input
     */
    public Input()
    {
    }

    /**
     * input boolean with check
     *
     * @return
     */
    public static boolean inputBoolean()
    {
        String inputString = inputString();
        while (true)
        {
            if (inputString.equalsIgnoreCase("false"))
                return false;
            else
                if (inputString.equalsIgnoreCase("true"))
                    return true;
                else
                {
                    System.out.print("Input Format Error!\n>>> Please input a valid boolean: ");
                    inputString = inputString();
                }
        }
    }

    /**
     * input format string
     * if input error, ask re-input again and again
     *
     * @param patternName
     * @return String inputString
     */
    public static String inputFormatString(String patternName)
    {
        String inputString = inputString();
        while (!Validator.formatValidation(inputString, patternName))
        {
            System.out.print("Input Format Error!\n>>> Please input a valid " + patternName + ": ");
            inputString = inputString();
        }
        return inputString;
    }

    /**
     * Input int via Scanner class
     * check if the input is int (scan.hasNextInt())
     * still ask input until the input is int (while loop)
     *
     * @return int inputNumber
     */
    public static int inputInt()
    {
        int inputNumber = 0;
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt())
        {
            scan.next();
            System.out.print("INVALID ENTRY! Please enter a integer number: ");
        }
        inputNumber = scan.nextInt();
        return inputNumber;
    }

    /**
     * input integer in a range
     * input a integer from range start to end
     * if input out of the range, print error massage and ask input again until it is valid
     *
     * @param rangeStart: a integer, the start of range
     * @param rangeEnd:   a integer, the end of range
     * @return the selected option
     */
    public static int inputIntegerInRange(int rangeStart, int rangeEnd)
    {
        int option = inputInt();
        while (option < rangeStart || option > rangeEnd)
        {
            System.out.print("INVALID ENTRY! Please input number between " + rangeStart + " and " + rangeEnd + ": ");
            option = inputInt();
        }
        return option;
    }

    /**
     * Input String via Scanner
     * no check, no loop
     *
     * @return String inputString
     */
    public static String inputString()
    {
        String inputString = "";
        Scanner scan = new Scanner(System.in);
        inputString = scan.nextLine().trim();
        return inputString;
    }

    /**
     * input student with check
     * 3-digit number between 111-999 (inclusive)
     *
     * @return
     */
    public static int inputStudentId()
    {
        int number = inputInt();
        while (!Validator.checkStudentID(number))
        {
            System.out.print("Input Error! Student ID: 3-digit number between 111-999 (inclusive)\n>>> Please input a valid student ID: ");
            number = inputInt();
        }
        return number;
    }
}

