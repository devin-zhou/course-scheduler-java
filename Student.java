/*
 * Devin Zhou
 */

import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  /**
   * Student constructor
   * @param name Name of student
   * @param id Id of student
   */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  //returns id
  public String getId()
  {
	  return id;
  }
  //returns name
  public String getName()
  {
	  return name;
  }

  /**
   * adds a credit course to list of courses for this student
   * @param courseName Name of course
   * @param courseCode Course code of course
   * @param descr Description of course
   * @param format Format of course
   * @param sem Semester of course
   * @param grade Grade of the student
   */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  // create a CreditCourse object
	  CreditCourse cc = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
	  // set course active
	  cc.setActive();
	  // add to courses array list
	  courses.add(cc);
  }
  
  public double getGrade(String courseCode)
  {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCode().equals(courseCode)) {
				return courses.get(i).grade;
			}
		}
		return 0;
  }
  
  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code, course name, 
  // semester, letter grade
  public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++) {
		  if(courses.get(i).getActive() == false) {
			  System.out.println(courses.get(i).displayGrade());
		  	}
	  }
  }
  
  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
  public void printActiveCourses()
  {
	 for (int i = 0; i < courses.size(); i++) {
		 if(courses.get(i).getActive()) {
			 System.out.println(courses.get(i).getDescription());
		 }
	 }
  }
  
  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
  /**	@param courseCode Code of course
    */
  public void removeActiveCourse(String courseCode)
  {
	 for (int i = 0; i < courses.size(); i++) {
		 if(courses.get(i).getCode().equalsIgnoreCase(courseCode) && courses.get(i).getActive()) {
			 courses.remove(i);
		 }
	 }
  }
  // returns string of object with info of student
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  // override equals method inherited from superclass Object
  // returns true if student name and id are equal
  public boolean equals(Object other)
  {
	  Student other1 = (Student) other;
	  return (this.name.equalsIgnoreCase(other1.name)) && (this.id.equalsIgnoreCase(other1.id));
  }

@Override
// CompareTo method
public int compareTo(Student o) {
	return (this.getName().compareTo(o.getName()));
	}
  
}
