package assignment2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class StudentDatabase is the collection of students
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class StudentDatabase
{
    /**
     * field
     */
    private DBConnector dbConnector;
    private ArrayList<Student> students;

    /**
     * default constructor
     */
    public StudentDatabase()
    {
        this.dbConnector = new DBConnector();
        this.students = new ArrayList<>();
    }

    /**
     * constructor
     *
     * @param dbConnector DBConnector
     * @param students    ArrayList<Student>
     */
    public StudentDatabase(DBConnector dbConnector, ArrayList<Student> students)
    {
        this.dbConnector = dbConnector;
        this.students = students;
    }

    /**
     * add one new student
     *
     * @param name            String
     * @param id              int
     * @param suspendedStatus boolean
     * @param subjects        ArrayList<Subject>
     */
    public void addOneStudent(String name, int id, boolean suspendedStatus, ArrayList<Subject> subjects)
    {
        if (getStudentIndexByNameAndId(name, id) == -1)
        {
            students.add((new Student(name, id, suspendedStatus, subjects)));
            System.out.println("\nADD SUCCESS!");
        }
        else
        {
            System.out.println("\nThis student already exit!");
            System.out.println("Error! ADD FAILED!");
        }
    }

    /**
     * delete one student
     *
     * @param name String
     * @param id   int
     */
    public void deleteOneStudent(String name, int id)
    {
        if (getStudentIndexByNameAndId(name, id) != -1)
        {

            students.remove(getStudentIndexByNameAndId(name, id));
            System.out.println("\nDELETE SUCCESS!");
        }
        else
        {
            System.out.println("\nThis student does not exit.");
            System.out.println("Error! DELETE FAILED!");
        }
    }

    /**
     * edit one student subjects
     *
     * @param name            String
     * @param id              int
     * @param subjectDatabase SubjectDatabase
     */
    public void editOneStudentSubjects(String name, int id, SubjectDatabase subjectDatabase)
    {
        if (getStudentIndexByNameAndId(name, id) != -1)
        {
            students.get(getStudentIndexByNameAndId(name, id)).setSubjects(subjectDatabase.getSubjects());
            System.out.println("\nEDIT subjects SUCCESS!");
        }
        else
        {
            System.out.println("\nThis student does not exit.");
            System.out.println("Error! EDIT subjects FAILED!");
        }
    }

    /**
     * edit one student suspend status
     *
     * @param name            String
     * @param id              int
     * @param suspendedStatus boolean
     */
    public void editOneStudentSuspendStatus(String name, int id, boolean suspendedStatus)
    {
        if (getStudentIndexByNameAndId(name, id) != -1)
        {
            students.get(getStudentIndexByNameAndId(name, id)).setSuspendedStatus(suspendedStatus);
            System.out.println("\nEDIT suspended status SUCCESS!");
        }
        else
        {
            System.out.println("\nThis student does not exit.");
            System.out.println("Error! EDIT suspended status FAILED!");
        }
    }

    /**
     * This method is to read students.txt to get the data
     */
    public void fetch() throws FileNotFoundException
    {
        dbConnector.setFileName("students.txt");
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            //name, id, status, subjects
            Student student = new Student();
            student.setName(lineArray[0]);
            student.setId(Integer.parseInt(lineArray[1]));
            student.setSuspendedStatus(Boolean.parseBoolean(lineArray[2]));
            ArrayList<Subject> subjects = new ArrayList<>();
            if (((lineArray.length - 3) / 2) > 0)
                for (int i = 0; i < (lineArray.length - 3) / 2; i++)
                {
                    subjects.add((new Subject(lineArray[i * 2 + 3], Integer.parseInt(lineArray[i * 2 + 4]))));
                }
            student.setSubjects(subjects);
            students.add(student);
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
     * get all student ID by student name
     *
     * @param name String
     * @return ArrayList<Integer> ids
     */
    public ArrayList<Integer> getIdsByName(String name)
    {
        ArrayList<Integer> listOfId = new ArrayList<>();
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).getName().equals(name))
                listOfId.add(students.get(i).getId());
        }
        return listOfId;
    }

    /**
     * get student index by student name and ID
     *
     * @param name String
     * @param id   int
     * @return int index
     */
    private int getStudentIndexByNameAndId(String name, int id)
    {
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).getName().equals(name) && students.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    /**
     * accessor of students
     *
     * @return students
     */
    public ArrayList<Student> getStudents()
    {
        return students;
    }

    /**
     * pass data to write
     *
     * @throws FileNotFoundException
     */
    public void passData() throws FileNotFoundException
    {
        ArrayList<String> lines = new ArrayList<>();
        for (Student student : students)
        {
            String line = student.getName() + "," + student.getId() + "," + student.getSuspendedStatus();
            for (Subject subject : student.getSubjects())
            {
                line += "," + subject.getName() + "," + subject.getCreditPoints();
            }
            lines.add(line);
        }
        dbConnector.writeDataToFile(lines);
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
     * mutator of students
     *
     * @param students ArrayList<Student>
     */
    public void setStudents(ArrayList<Student> students)
    {
        this.students = students;
    }

    /**
     * show all student list
     */
    public void showAllStudentsList()
    {
        System.out.println("There are all students list:\n");
        int orderNumber = 1;
        for (Student student : students)
        {
            System.out.println("(" + orderNumber + ") " + student);
            orderNumber++;
        }
    }

    /**
     * show one student details
     *
     * @param name
     * @param id
     */
    public void showOneStudentDetails(String name, int id)
    {
        if (getStudentIndexByNameAndId(name, id) != -1)
        {
            System.out.println("\nHere are the details about this student:");
            System.out.println(students.get(getStudentIndexByNameAndId(name, id)));
        }
        else
            System.out.println("\nThis student does not exit.");
    }

    /**
     * show students list by subject (only for Unsuspend Student)
     *
     * @param subjectDatabase
     */
    public void showStudentsListBySubjects(SubjectDatabase subjectDatabase)
    {
        int orderNumber = 1;
        for (Student student : students)
        {
            if (student.getSuspendedStatus() == false)
            {
                boolean containAllSubjects = true;
                for (Subject subject : subjectDatabase.getSubjects())
                {
                    if (!student.getSubjects().contains(subject))
                    {
                        containAllSubjects = false;
                        break;
                    }
                }
                if (containAllSubjects)
                {
                    System.out.println("(" + orderNumber + ") " + student);
                    orderNumber++;
                }
            }
        }
        if (orderNumber == 1)
            System.out.println("\nHere are no student enrolled these subjects!");
    }

    /**
     * show unsuspend student
     */
    public void showSuspendedStudentsList()
    {
        int orderNumber = 1;
        for (Student student : students)
        {
            if (student.getSuspendedStatus())
            {
                System.out.println("(" + orderNumber + ") " + student);
                orderNumber++;
            }
        }
        if (orderNumber == 1)
            System.out.println("\nHere are no student suspended!");
    }
}
