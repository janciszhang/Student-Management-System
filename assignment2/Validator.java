package assignment2;

import java.util.HashMap;

/**
 * This class InputFormatValidator is to check th String if it is following the format
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class Validator
{
    /**
     * field
     */
    private static HashMap<String, String> patternNameAndPattern;

    /**
     * default constructor
     */
    public Validator()
    {
        patternNameAndPattern = new HashMap<>();
    }

    /**
     * check student ID, 3-digit number between 111-999 (inclusive)
     *
     * @param number int
     * @return boolean, check result
     */
    public static boolean checkStudentID(int number)
    {
        return number >= 111 && number <= 999;
    }

    /**
     * check string format
     * it cannot contain "," because it separates the data in database file
     *
     * @param input
     * @return Boolean check result
     */
    public static Boolean formatValidation(String input)
    {
        setPatternNameAndPattern();
        return input.matches(patternNameAndPattern.get("noDBF"));
    }

    /**
     * check string format
     * it cannot contain "," because it separates the data in database file
     * it has other format requirement that need to follow
     *
     * @param input
     * @param patternName
     * @return
     */
    public static Boolean formatValidation(String input, String patternName)
    {
        setPatternNameAndPattern();
        return input.matches(patternNameAndPattern.get(patternName)) && formatValidation(input);
    }

    /**
     * accessor for patternNameAndPattern
     *
     * @return HashMap<String, String> patternNameAndPattern
     */
    public static HashMap<String, String> getPatternNameAndPattern()
    {
        return patternNameAndPattern;
    }

    /**
     * mutator for patternNameAndPattern
     */
    public static void setPatternNameAndPattern()
    {
        patternNameAndPattern = new HashMap<>();
        //data separate by ",", so any input which need store cannot contain ","
        patternNameAndPattern.put("noDBF", "^((?!\\|).)*$");
        patternNameAndPattern.put("studentName", "^[A-Za-z\\s]+(·[A-Za-z]+)*$");
        patternNameAndPattern.put("subjectName", "^[A-Za-z0-9\\s]+(·[A-Za-z0-9]+)*$");
        patternNameAndPattern.put("creditPoints", "[1-6]{1}");
    }
}

