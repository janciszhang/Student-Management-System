package assignment2;

/**
 * This class Subject contain two attributes
 * name, creditPoints
 *
 * @author
 * @version 1.8.0   18 Oct 2021
 */
public class Subject
{
    /**
     * field
     * name: String, subject name, not be a blank string, can be numeric/alphabetic
     * creditPoints: int, credit points, between 1-6 (inclusive)
     */
    private int creditPoints;
    private String name;

    /**
     * Default constructor for class Subject
     */
    public Subject()
    {
    }

    /**
     * constructor for class Subject
     *
     * @param creditPoints int, credit points, between 1-6 (inclusive)
     * @param name         String, subject name, not be a blank string, can be numeric/alphabetic
     */
    public Subject(String name, int creditPoints)
    {
        this.creditPoints = creditPoints;
        this.name = name;
    }

    @Override
    public boolean equals(Object object)
    {
        Subject newSubject = (Subject) object;
        return this.name.equals(newSubject.getName()) && this.creditPoints == newSubject.getCreditPoints();
    }

    /**
     * accessor of creditPoints
     *
     * @return creditPoints
     */
    public int getCreditPoints()
    {
        return creditPoints;
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
     * mutator of creditPoints
     *
     * @param creditPoints int
     */
    public void setCreditPoints(int creditPoints)
    {
        this.creditPoints = creditPoints;
    }

    /**
     * mutator of name
     *
     * @param name String
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return name + ": " + creditPoints + " credit points";
    }
}
