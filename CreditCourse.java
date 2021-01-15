/*
 * Devin Zhou
 */
public class CreditCourse extends Course
{
	private String semester;
	public double grade = 0.0;
	public Boolean active = true;
	
	/**
	 * Constructor
	 * @param name	 Name of course
	 * @param code	 Code of course
	 * @param descr	 Description of course
	 * @param fmt	 Format of course
	 * @param semester Semester of course 
	 * @param grade	Grade of student
	 */
	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade)
	{
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade; 
	}
	//returns active boolean
	public boolean getActive()
	{
		// add code and remove line below
		return active;
	}
	//sets active to true
	public void setActive()
	{
		active = true;
	}
	//sets active to false
	public void setInactive()
	{
		active = false;
	}

	/**
	 * sets current grade to given grade
	 * @param grade Numerical grade of student
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}
	//returns information, format and grade printout
	public String displayGrade()
	{
		return super.getInfo() + " " + semester + " " + convertNumericGrade(grade);
	}
	
}