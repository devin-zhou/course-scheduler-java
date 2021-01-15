
/*
 * Devin Zhou
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class Registry {

	private TreeMap<String, Student> students1 = new TreeMap<String, Student>();
	private TreeMap<String, ActiveCourse> courses1 = new TreeMap<String, ActiveCourse>();

	public Registry() throws FileNotFoundException, NullPointerException, IOException {

		// Reading students from file
		File studentsfile = new File("students.txt");
		Scanner fileRead = new Scanner(studentsfile);
		String name = null;
		String id = null;
		if(!fileRead.hasNext()) { //if the file is empty
			fileRead.close();
			throw new IOException("Bad File Format for " + studentsfile + "\nNo data found.");
		}
		while (fileRead.hasNext()) { 
			Scanner lineRead = new Scanner(fileRead.nextLine()); //Extracts the name and id line by line
			try {
				name 	= lineRead.next();
				id 		= lineRead.next();
			}
			catch (NoSuchElementException e) { //a missing name or id will cause a NoSuchElementException
				System.out.println("Bad File Format for " + studentsfile);
				System.exit(0);
			}
			// Checks:
			//  ensure name is all alphabetic characters
			//  ensure id string is all numeric characters
			if(!isStringOnlyAlphabet(name) || !isNumeric(id)) {
				lineRead.close();
				throw new IOException("Bad File Format for " + studentsfile);
			}

			Student stu1 = new Student(name, id); // creating student object
			students1.put(id, stu1); // putting the student into students1 TreeMap
			lineRead.close();
		}
		fileRead.close();

		ArrayList<Student> list = new ArrayList<Student>();
		// CPS209
		String courseName = "Computer Science II";
		String courseCode = "CPS209";
		String descr = "Learn how to write complex programs!";
		String format = "3Lec 2Lab";
		list.add(students1.get("38467")); // s2
		list.add(students1.get("98345")); // s3
		list.add(students1.get("57643")); // s4
		courses1.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		students1.get("57643").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("98345").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("57643").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		
		// CPS511
		list.clear();
		courseName = "Computer Graphics";
		courseCode = "CPS511";
		descr = "Learn how to write cool graphics programs";
		format = "3Lec";
		list.add(students1.get("34562")); // s1
		list.add(students1.get("25347")); // s5
		list.add(students1.get("46532")); // s6
		courses1.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		students1.get("34562").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("25347").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("46532").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		
		// CPS643
		list.clear();
		courseName = "Virtual Reality";
		courseCode = "CPS643";
		descr = "Learn how to write extremely cool virtual reality programs";
		format = "3Lec 2Lab";
		list.add(students1.get("34562")); // s1
		list.add(students1.get("38467")); // s2
		list.add(students1.get("57643")); // s4
		list.add(students1.get("46532")); // s6
		courses1.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		students1.get("34562").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("38467").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("57643").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("46532").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		
		// CPS706
		list.clear();
		courseName = "Computer Networks";
		courseCode = "CPS706";
		descr = "Learn about Computer Networking";
		format = "3Lec 1Lab";
		list.add(students1.get("38467")); // s2
		list.add(students1.get("57643")); // s4
		list.add(students1.get("25347")); // s5
		courses1.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		students1.get("38467").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("57643").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("25347").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		
		// CPS616
		list.clear();
		courseName = "Algorithms";
		courseCode = "CPS616";
		descr = "Learn about Algorithms";
		format = "3Lec 1Lab";
		list.add(students1.get("34562")); // s1
		list.add(students1.get("98345")); // s3
		courses1.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		students1.get("34562").addCourse(courseName, courseCode, descr, format, "W2020", 0);
		students1.get("98345").addCourse(courseName, courseCode, descr, format, "W2020", 0);

	}
	
	/**
	 * gets courses TreeMap
	 * @return courses1 TreeMap
	 */
	public TreeMap<String, ActiveCourse> getCoursesTM(){
		return courses1;
	}

	/**
	 * Add new student to the registry
	 * 
	 * @param name Name of student
	 * @param id   Id of student
	 * @return true if student is added to registry
	 */
	public boolean addNewStudent(String name, String id) {
		if (students1.containsKey(id))
			return false;
		else {
			Student stu1 = new Student(name, id); // creating student object
			students1.put(id, stu1); // putting the student into students1 TreeMap
			return true;
		}
	}

	/**
	 * Remove student from registry
	 * 
	 * @param studentId The id of the student being removed
	 * @return true if student is removed
	 */
	public boolean removeStudent(String studentId) {
		if (students1.containsKey(studentId)) {
			students1.remove(studentId);
			return true;
		}
		return false;
	}

	// Print all registered students
	public void printAllStudents() {
		for (String key : students1.keySet()) {
			System.out.println("ID: " + key + " Name: " + students1.get(key).getName());
		}
	}

	/**
	 * Given a studentId and a course code, add student to the active course
	 * 
	 * @param studentId  Id of student
	 * @param courseCode Course code of the course
	 */
	public void addCourse(String studentId, String courseCode) {
		// Find student object in registry
		Student stu = null;
		ActiveCourse ac = null;
		Boolean taken = false;
		if (students1.containsKey(studentId)) { 
			stu = students1.get(studentId);
			for (int i = 0; i < stu.courses.size(); i++) {
				// Check if student has already taken this course in the past
				if (stu.courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
					taken = true;
				}
			}
			if (!taken) {
				if (courses1.containsKey(courseCode)) {
					ac = courses1.get(courseCode);
					// iterates through students in active course
					for (int j = 0; j < ac.students.size(); j++) {
						// If not already enrolled
						if (!ac.students.get(j).getId().equals(studentId)) {
							// add student to the active course
							ac.students.add(stu);
							// add course to student list of credit courses with initial grade of 0
							stu.addCourse(ac.getName(), ac.getCode(), ac.getCourseDescription(), ac.getFormat(),
									ac.getSemester(), 0);
							return;
						}
					}
				}
			}
		}
	}


	/**
	 * Given a studentId and a course code, drop student from the active course
	 * 
	 * @param studentId  Id of student
	 * @param courseCode Course code of the course
	 */
	public void dropCourse(String studentId, String courseCode) {
		// Find the active courses
		if (courses1.containsKey(courseCode)) {
			// Find the student in the list of students for this course
			for (int i = 0; i < courses1.get(courseCode).getStudents().size(); i++) {
				// If student found:
				if (courses1.get(courseCode).getStudents().get(i).getId().equalsIgnoreCase(studentId)) {
					// remove the student from the active course
					courses1.get(courseCode).getStudents().remove(courses1.get(courseCode).getStudents().get(i));
					// remove the credit course from the student's list of credit courses
					courses1.get(courseCode).getStudents().get(i).removeActiveCourse(courseCode);
				}
			}
		}
	}

	// Print all active courses
	public void printActiveCourses() {
		for (String key : courses1.keySet()) {
			System.out.println(courses1.get(key).getDescription());
		}
	}

	/**
	 * Print the list of students in an active course
	 * 
	 * @param courseCode Course code of the respective course
	 */
	public void printClassList(String courseCode) {
		if (courses1.containsKey(courseCode)) {
			courses1.get(courseCode).printClassList();
		}
	}

	/**
	 * Given a course code, find course and sort class list by student name
	 * 
	 * @param courseCode Course code of the respective course
	 */
	public void sortCourseByName(String courseCode) {
		if (courses1.containsKey(courseCode)) {
			courses1.get(courseCode).sortByName();
		}
	}

	/**
	 * Given a course code, find course and sort class list by student ID
	 * 
	 * @param courseCode Course code of the course
	 */
	public void sortCourseById(String courseCode) {
		if (courses1.containsKey(courseCode)) {
			courses1.get(courseCode).sortById();
		}
	}

	/**
	 * Given a course code, find course and print student names and grades
	 * 
	 * @param courseCode Course code of the course
	 */
	public void printGrades(String courseCode) {
		if (courses1.containsKey(courseCode)) {
			courses1.get(courseCode).printGrades();
		}
	}

	/**
	 * Given a studentId, print all active courses of student
	 * 
	 * @param studentId Student ID of the respective student
	 */

	public void printStudentCourses(String studentId) {
		if (students1.containsKey(studentId)) {
			students1.get(studentId).printActiveCourses();
		}
	}

	/**
	 * Given a studentId, print all completed courses and grades of student
	 * 
	 * @param studentId Id of the student
	 */
	public void printStudentTranscript(String studentId) {
		if (students1.containsKey(studentId)) {
			students1.get(studentId).printTranscript();
		}
	}

	/**
	 * sets the final grade of the student
	 * 
	 * @param courseCode Course code of the course
	 * @param studentId  Id of the student
	 * @param grade      Numerical grade of the student
	 */
	public void setFinalGrade(String courseCode, String studentId, double grade) {
		// finding the active course
		if (courses1.containsKey(courseCode)) {
			// If found, find the student in class list
			for (int i = 0; i < courses1.get(courseCode).students.size(); i++) {
				if (courses1.get(courseCode).students.get(i).getId().equalsIgnoreCase(studentId)) {
					// then search student credit course list in student object and find course
					for (int j = 0; j < courses1.get(courseCode).students.get(i).courses.size(); j++) {
						if (courseCode.equalsIgnoreCase(courses1.get(courseCode).students.get(i).courses.get(j).getCode())) {
							courses1.get(courseCode).students.get(i).courses.get(j).grade = grade;
							courses1.get(courseCode).students.get(i).courses.get(j).setInactive();
							break;
						}
					}
				}
			}

		}
	}
	/**
	 * Checks if a string is only alphabetical characters
	 * @param str name
	 * @return True if string is only numbers
	 */
	private static boolean isStringOnlyAlphabet(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a string is only numeric characters
	 * @param str ID
	 * @return True if string is only numeric
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
