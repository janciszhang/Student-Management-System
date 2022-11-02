package assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class DBConnector is read file or write to file
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class DBConnector
{
    /**
     * Field
     * fileName: a string, file name or path
     */
    private String fileName;

    /**
     * Default constructor for class DBConnector
     */
    public DBConnector()
    {
    }

    /**
     * This method is to get the file name
     *
     * @return filename String
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * read a file and return a string ArrayList
     * each line in file is stored as a string item in this string ArrayList
     *
     * @return data: a string ArrayList, store all lines of the file
     * @throws FileNotFoundException
     */
    public ArrayList<String> readDataFromFile() throws FileNotFoundException
    {
        ArrayList<String> data = new ArrayList<>();
        try
        {
            FileReader fileReader = new FileReader(fileName);
            try
            {
                Scanner scan = new Scanner(fileReader);
                while (scan.hasNextLine())
                {
                    String lineStr = scan.nextLine();
                    data.add(lineStr);
                }
            }
            finally
            {
                fileReader.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error! Cannot find the file: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Error! IO exception");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return data;
    }

    /**
     * This method is to set the file name
     *
     * @param fileName The name of file
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * write a string ArrayList to a file
     *
     * @param data: a string ArrayList, that need to be output to a file
     * @throws FileNotFoundException
     */
    public void writeDataToFile(ArrayList<String> data) throws FileNotFoundException
    {
        try
        {
            PrintWriter printWriter = new PrintWriter(fileName);
            for (String lineStr : data)
            {
                printWriter.println(lineStr);
            }
            printWriter.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error! Cannot find the file: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Error! IO exception");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
