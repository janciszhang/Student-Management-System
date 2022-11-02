package assignment2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class SubjectDatabase is the collection of subjects
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class SubjectDatabase
{
    /**
     * field
     */
    private DBConnector dbConnector;
    private ArrayList<Subject> subjects;

    /**
     * default constructor
     */
    public SubjectDatabase()
    {
        this.dbConnector = new DBConnector();
        this.subjects = new ArrayList<>();
    }

    /**
     * constructor
     *
     * @param dbConnector an object of DBConnector
     * @param subjects    ArrayList<Subject>
     */
    public SubjectDatabase(DBConnector dbConnector, ArrayList<Subject> subjects)
    {
        this.dbConnector = dbConnector;
        this.subjects = subjects;
    }

    /**
     * add new subject
     * total credit cannot more than 15
     *
     * @param subject an object of Subject
     */
    public void addNewSubject(Subject subject)
    {
        if ((getSubjectIndex(subject) == -1))
        {
            if (getTotalCredit() + subject.getCreditPoints() <= 15)
            {
                subjects.add(subject);
                System.out.println("\nADD SUCCESS!");
            }
            else
            {
                System.out.println("\nNow the total credit points is " + getTotalCredit() + ". \nIt cannot more than 15!");
                System.out.println("Error! ADD FAILED!");
            }
        }
        else
        {
            System.out.println("\nThis subject already exit!");
            System.out.println("Error! ADD FAILED!");
        }
    }

    /**
     * delete one subject
     *
     * @param subject an object of Subject
     */
    public void deleteOneSubject(Subject subject)
    {
        if ((getSubjectIndex(subject) != -1))
        {
            subjects.remove(subject);
            System.out.println("\nDELETE SUCCESS!");
        }
        else
        {
            System.out.println("\nThis subject does not exit!");
            System.out.println("Error! DELETE FAILED!");
        }
    }

    /**
     * This method is to read subjects.txt to get the data
     */
    public void fetch() throws FileNotFoundException
    {
        dbConnector.setFileName("subjects.txt");
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            //BranchId,OpeningHours,Street,Suburb,State,Postcode
            Subject subject = new Subject(lineArray[0], Integer.parseInt(lineArray[1]));
            subjects.add(subject);
        }
    }

    /**
     * accessor of dbConnector
     *
     * @return dbConnector
     */
    public DBConnector getDbConnector()
    {
        return dbConnector;
    }

    /**
     * get subject by order number
     *
     * @param orderNumber int
     * @return Subject
     */
    public Subject getSubjectByOrderNumber(int orderNumber)
    {
        return subjects.get(orderNumber - 1);
    }

    /**
     * get subject index
     * if it does not exit, return -1
     *
     * @param subject Subject
     * @return int, index
     */
    private int getSubjectIndex(Subject subject)
    {
        for (int i = 0; i < subjects.size(); i++)
        {
            if (subjects.get(i).getName().equals(subject.getName()))
                return i;
        }
        return -1;
    }

    /**
     * accessor of subjects
     *
     * @return subjects
     */
    public ArrayList<Subject> getSubjects()
    {
        return subjects;
    }

    /**
     * get total credit
     *
     * @return int
     */
    private int getTotalCredit()
    {
        int total = 0;
        for (Subject subject : subjects)
        {
            total += subject.getCreditPoints();
        }
        return total;
    }

    /**
     * mutator of dbConnector
     *
     * @param dbConnector DBConnector
     */
    public void setDbConnector(DBConnector dbConnector)
    {
        this.dbConnector = dbConnector;
    }

    /**
     * mutator of subjects
     *
     * @param subjects ArrayList<Subject>
     */
    public void setSubjects(ArrayList<Subject> subjects)
    {
        this.subjects = subjects;
    }

    /**
     * show all subjects list
     */
    public void showAllSubjectsList()
    {
        System.out.println("There are all subjects and their credit points:\n");
        int orderNumber = 1;
        for (Subject subject : subjects)
        {
            System.out.println("(" + orderNumber + ") " + subject);
            orderNumber++;
        }
    }
}
