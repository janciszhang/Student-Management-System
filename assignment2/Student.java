package assignment2;

import java.util.ArrayList;

/**
 * This class Student includes four attributes
 * name, id, suspendedStatus, subjects
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class Student
{
    /**
     * Field
     * id: int, student ID, 3-digit number between 111-999 (inclusive)
     * name: String, the student name, not a blank string, only alphabetic, may contain multiple words
     * subjects: an object of Subject, a "database" of unique subjects, total credit points <= 15
     * suspendedStatus: boolean, suspended status
     */
    private int id;
    private String name;
    private ArrayList<Subject> subjects;
    private boolean suspendedStatus;

    /**
     * Default constructor for class Student
     */
    public Student()
    {
    }

    /**
     * Constructor for class Student
     *
     * @param name            String, String, the student name, not a blank string, only alphabetic, may contain multiple words
     * @param id              int, student ID, 3-digit number between 111-999 (inclusive)
     * @param suspendedStatus boolean, suspended status
     * @param subjects        an object of Subject, a "database" of unique subjects, total credit points <= 15
     */
    public Student(String name, int id, boolean suspendedStatus, ArrayList<Subject> subjects)
    {
        this.name = name;
        this.id = id;
        this.suspendedStatus = suspendedStatus;
        this.subjects = subjects;
    }

    /**
     * accessor of id
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * accessor of name
     *
     * @return name
     */
    public String getName()
    {
        return name;
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
     * accessor of suspendedStatus
     *
     * @return suspendedStatus
     */
    public boolean getSuspendedStatus()
    {
        return suspendedStatus;
    }

    /**
     * mutator of id
     *
     * @param id int
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * mutator of id
     *
     * @param name String
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * mutator of subjects
     *
     * @param subjects Subject
     */
    public void setSubjects(ArrayList<Subject> subjects)
    {
        this.subjects = subjects;
    }

    /**
     * mutator of suspendedStatus
     *
     * @param suspendedStatus, boolean
     */
    public void setSuspendedStatus(boolean suspendedStatus)
    {
        this.suspendedStatus = suspendedStatus;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Student name: " + name + "\nStudent ID: " + id + "\nSuspended Status: " + suspendedStatus + "\nEnrolled Subjects: " + subjects;
    }
}
