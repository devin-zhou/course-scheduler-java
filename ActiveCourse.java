/*
 * Devin Zhou
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
	public ArrayList<Student> students; 
	private String semester, lectureDay;
	private int lectureStart, lectureDuration;
	
	/**
	 * constructor 
	 * @param name Name of course
	 * @param code Course code of course
	 * @param descr Description of course
	 * @param fmt Format of course
	 * @param semester Semester of course
	 * @param students Students in the course
	 */
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
   }
   public String getLectureDay()
   {
	   return lectureDay;
   }
   public void setLectureDay(String day) {
	   lectureDay = day;
   }
   public int getLectureStart()
   {
	   return lectureStart;
   }
   public void setLectureStart(int start) {
	   lectureStart = start;
   }
   public int getLectureDuration()
   {
	   return lectureDuration;
   }
   public void setLectureDuration(int duration) {
	   lectureDuration = duration;
   }
   
   //returns semester
   public String getSemester()
   {
	   return semester;
   }
   
   // Prints the students in this course  (name and student id)
   public void printClassList()
   {
	   for(int i = 0; i < students.size(); i++) {
		   System.out.println(students.get(i).toString());
	   }
   }
   
   // Prints the grade of each student in this course (along with name and student id)
   public void printGrades()
   {
	   for(int i = 0; i < students.size(); i++) {
		   Student stu = students.get(i);
		   System.out.println(students.get(i).toString() + " " + stu.getGrade(getCode()));
	   }
   }
   
   //returns students ArrayList
   public ArrayList<Student> getStudents(){
	   return students;
   }
   
   /**
    * Returns grade of student
    * @param studentId Id of student
    * @return Grade of the student
    */
   public double getGrade(String studentId)
   {
	  // Search the student's list of credit courses to find the course code that matches this active course
	  for (int i = 0; i < students.size(); i++) {
		  if (studentId.equalsIgnoreCase(students.get(i).getId())) {
			  return students.get(i).getGrade(getCode());
		  }
	  }
	  return 0; 
   }
   
   // Returns a String containing the course information as well as the semester and the number of students 
   // enrolled in the course
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() + "\n";
   }
   public String getCourseDescription()
   {
	   return getDescr();
   }
    
   // Sort the students in the course by name
   public void sortByName()
   {
	   NameComparator namCom = new NameComparator();
	   Collections.sort(students, namCom);
   }
   
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student>
   {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getName().compareTo(o2.getName());
	}
   }
   
   // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
   public void sortById()
   {
	   IdComparator idCom = new IdComparator();
	   Collections.sort(students, idCom);
   }
   
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student>
   {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getId().compareTo(o2.getId());
	}

   }
}
