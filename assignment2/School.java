package assignment2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class School is A Virtual Student Management System
 * main class, which contains the program's main logic
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class School
{
    /**
     * Field
     * studentDatabase: an object of class StudentDatabase
     * subjectDatabase: an object of class SubjectDatabase
     */
    private StudentDatabase studentDatabase;
    private SubjectDatabase subjectDatabase;

    /**
     * Default Constructor
     * initialize studentDatabase and subjectDatabase
     */
    public School()
    {
        this.studentDatabase = new StudentDatabase();
        this.subjectDatabase = new SubjectDatabase();
    }

    /**
     * choose subjects from list
     *
     * @return SubjectDatabase, newSubjectDatabase
     */
    public SubjectDatabase chooseSubjectsFromList()
    {
        SubjectDatabase newSubjectDatabase = new SubjectDatabase();
        System.out.println("\n------------------Choose subject------------------");
        subjectDatabase.showAllSubjectsList();
        System.out.println("<0> Enter 0 to finish the choosing");
        System.out.print("\n>>> Please choose a subject: ");
        int option = Input.inputIntegerInRange(0, subjectDatabase.getSubjects().size());
        while (option != 0)
        {
            newSubjectDatabase.addNewSubject(subjectDatabase.getSubjectByOrderNumber(option));
            System.out.println("\n------------------Choose subject------------------");
            subjectDatabase.showAllSubjectsList();
            System.out.println("\n<0> Enter 0 to finish the choose");
            System.out.print("\n>>> Please choose a subject: ");
            option = Input.inputIntegerInRange(0, subjectDatabase.getSubjects().size());
        }
        System.out.println("---------------Choose subject done----------------");
        return newSubjectDatabase;
    }

    /**
     * get student name and student id from input
     * if there is only one student is this name, user do not need to enter id
     *
     * @return nameAndId, ArrayList
     */
    public ArrayList getNameAndIdFromInput()
    {
        ArrayList nameAndId = new ArrayList();
        System.out.print("\n>>> Please input STUDENT NAME: ");
        String name = Input.inputFormatString("studentName");
        int id = 0;
        if (studentDatabase.getIdsByName(name).size() == 1)
        {
            id = studentDatabase.getIdsByName(name).get(0);
        }
        else
        {
            System.out.print("\n>>> Please input STUDENT ID: ");
            id = Input.inputStudentId();
        }
        nameAndId.add(name);
        nameAndId.add(id);
        return nameAndId;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        School school = new School();
        school.start();
    }

    /**
     * Option (1)
     * allows the user to add a new student into the database.
     * The user should be asked for the student's details, plus what subject(s) to "enrol" him/her in.
     * Duplicate students (same name & ID) should be rejected.
     */
    public void option1AddNewStudent()
    {
        System.out.println("You should input attribute one by one to add new student.");
        System.out.print("\n>>> Please input STUDENT NAME: ");
        String name = Input.inputFormatString("studentName");
        System.out.print("\n>>> Please input STUDENT ID: ");
        int id = Input.inputStudentId();
        System.out.print("\n>>> Please input STUDENT SUSPENDED STATUS: ");
        boolean suspendedStatus = Input.inputBoolean();
        SubjectDatabase newSubjectDatabase = chooseSubjectsFromList();
        System.out.print("\u000C");
        studentDatabase.addOneStudent(name, id, suspendedStatus, newSubjectDatabase.getSubjects());
    }

    /**
     * Option (2) allows the user to remove an existing student from the database.
     */
    public void option2DeleteStudent()
    {
        System.out.println("You can delete a student.");
        ArrayList nameAndId = getNameAndIdFromInput();
        System.out.print("\u000C");
        studentDatabase.showOneStudentDetails((String) nameAndId.get(0), (int) nameAndId.get(1));
        studentDatabase.deleteOneStudent((String) nameAndId.get(0), (int) nameAndId.get(1));
    }

    /**
     * Option (3) allows the user to find an existing student in the database, and mark the student's status as appropriate.
     */
    public void option3EditSuspendStatus()
    {
        System.out.println("You can edit one student suspend status.");
        ArrayList nameAndId = getNameAndIdFromInput();
        studentDatabase.showOneStudentDetails((String) nameAndId.get(0), (int) nameAndId.get(1));
        System.out.print("\n>>> Please input STUDENT SUSPENDED STATUS: ");
        boolean suspendedStatus = Input.inputBoolean();
        System.out.print("\u000C");
        studentDatabase.editOneStudentSuspendStatus((String) nameAndId.get(0), (int) nameAndId.get(1), suspendedStatus);
    }

    /**
     * Option (4) allows the user to find an existing student in the database and edit his subject list (add or delete subjects).
     * The user should be asked to enter a name to search for.
     * If there are more than one student with the same name (but different IDs), the user should be asked to choose the correct one.
     */
    public void option4EditStudentSubjects()
    {
        System.out.println("You can edit one student subjects.");
        ArrayList nameAndId = getNameAndIdFromInput();
        studentDatabase.showOneStudentDetails((String) nameAndId.get(0), (int) nameAndId.get(1));
        SubjectDatabase newSubjectDatabase = chooseSubjectsFromList();
        System.out.print("\u000C");
        studentDatabase.editOneStudentSubjects((String) nameAndId.get(0), (int) nameAndId.get(1), newSubjectDatabase);
    }

    /**
     * Option (5) allows the user to display a list of students enrolled in a subject.
     */
    public void option5ListStudentsBySubjects()
    {
        SubjectDatabase newSubjectDatabase = chooseSubjectsFromList();
        System.out.print("\u000C");
        studentDatabase.showStudentsListBySubjects(newSubjectDatabase);
    }

    /**
     * Option (6) allows the user to display a list of all students who have been suspended.
     */
    public void option6ListSuspendedStudents()
    {
        studentDatabase.showSuspendedStudentsList();
    }

    /**
     * Option (7) allows the user to display a list of all students enrolled in the school.
     */
    public void option7ListAllStudents()
    {
        studentDatabase.showAllStudentsList();
    }

    /**
     * Option (8) exits the program.
     * All the students currently in memory are automatically saved to "students.txt".
     * clear ArrayList
     *
     * @throws FileNotFoundException
     */
    private void option8ExitSystem() throws FileNotFoundException
    {
        studentDatabase.passData();
        studentDatabase.getStudents().clear();
        subjectDatabase.getSubjects().clear();
        System.out.println("ฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅ");
        System.out.println("\n            THANK YOU VERY MUCH!             \n");
        System.out.println("ฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅ");
        System.exit(0);
    }

    /**
     * main menu option switch
     *
     * @param option: a integer, option input
     * @throws FileNotFoundException
     */
    private void optionSwitch(int option) throws FileNotFoundException
    {
        switch (option)
        {
            case 1:
                option1AddNewStudent();
                break;
            case 2:
                option2DeleteStudent();
                break;
            case 3:
                option3EditSuspendStatus();
                break;
            case 4:
                option4EditStudentSubjects();
                break;
            case 5:
                option5ListStudentsBySubjects();
                break;
            case 6:
                option6ListSuspendedStudents();
                break;
            case 7:
                option7ListAllStudents();
                break;
            case 8:
                option8ExitSystem();
                break;
        }
    }

    /**
     * print main menu
     */
    private void showMenu()
    {
        System.out.println("ฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅ");
        System.out.println("Welcome to Use Virtual Student Management System");
        System.out.println("==============================================\n");
        System.out.println("(1) Add New Student");
        System.out.println("(2) Delete Student");
        System.out.println("(3) Suspend/Unsuspend Student");
        System.out.println("(4) Edit Student");
        System.out.println("(5) List Students by Subjects");
        System.out.println("(6) List Suspended Students");
        System.out.println("(7) List All Students");
        System.out.println("(8) Exit System");
    }

    /**
     * Start point
     *
     * @param args
     * @throws FileNotFoundException
     */

    /**
     * the whole process
     *
     * @throws FileNotFoundException
     */
    public void start() throws FileNotFoundException
    {
        // fetch
        studentDatabase.fetch();
        subjectDatabase.fetch();
        int option = 0;
        do
        {
            showMenu();
            System.out.print("\n>>> Please input your choose: ");
            option = Input.inputIntegerInRange(1, 8);
            System.out.print("\u000C");
            optionSwitch(option);
            if (option != 8)
                System.out.println("\n---------------Option finished---------------\n\n");
        } while (option != 8);
    }
}
