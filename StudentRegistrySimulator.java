/*
 * Devin Zhou
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StudentRegistrySimulator 
{
  public static void main(String[] args) 
  {
	  //Creating registry object
	  Registry registry = null;

	  try {
		  registry = new Registry();
	  }
	  catch (FileNotFoundException e) {
		  System.out.println("students.txt not found.");
		  System.exit(0);
	  }
	  catch (NullPointerException e) {
		  System.out.println(e.getMessage()); 
		  System.exit(0);
	  }
	  catch (IOException e) {
		  System.out.println(e.getMessage()); 
		  System.exit(0);
	  }
	  
	  //Creating schedule object
	  Scheduler schedule = new Scheduler(registry.getCoursesTM());
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG")) // register a new student in registry

		  {
			  try {
				  // get name and student id string 
				  String name = commandLine.next();
				  String id = commandLine.next();
				  // Checks:
				  //  ensure name is all alphabetic characters
				  //  ensure id string is all numeric characters
				  if (isNumeric(id) && isStringOnlyAlphabet(name)) {
					  if(!registry.addNewStudent(name,id)){
						  System.out.println("Student " + name + " is already registered.");
					  }
				  }
				  else if (!isStringOnlyAlphabet(name) && isNumeric(id)) {
					  System.out.println("Invalid Characters in Name " + name);
				  }
				  else if (!isNumeric(id) && isStringOnlyAlphabet(name)) {
					  System.out.println("Invalid Characters in ID " + id);
				  }
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, name, id");
			  }
		  }
		  else if (command.equalsIgnoreCase("DEL")) // delete a student from registry
		  {
			  try {
				  // get student id
				  String id = commandLine.next();
				  // ensure numeric
				  if(isNumeric(id)) {
				  // remove student from registry
					  if (!registry.removeStudent(id)) {
						  System.out.println("Cannot find student.");
					  }
				  }
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, id");
			  }
		  }
		  else if (command.equalsIgnoreCase("ADDC")) // add a student to an active course
		  {
			  try {
					 // get student id and course code strings
					  String id = commandLine.next();
					  String code = commandLine.next().toUpperCase();
					 // add student to course (see class Registry)
					  registry.addCourse(id, code);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, id, code");
			  }
		  }
		  else if (command.equalsIgnoreCase("DROPC")) // get student id and course code strings
		  {
			  try {
				  
				  String id = commandLine.next();
				  String code = commandLine.next().toUpperCase();
				  // drop student from course (see class Registry)
				  registry.dropCourse(id, code);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input.Correct format: command, id, code");
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC")) // Prints all classes
		  {
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL")) //prints class list of a course
		  {
			  try {
				  // get course code string
				  String code = commandLine.next().toUpperCase();
				  // print class list (i.e. students) for this course
				  registry.printClassList(code);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, code");
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR")) //Prints grade of all students in the course
		  {
			  try {
				  // get course code string
				  String code = commandLine.next().toUpperCase();
				  // print name, id and grade of all students in active course
				  registry.printGrades(code);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, code");
			  }
		  }
		  else if (command.equalsIgnoreCase("PSC")) // Prints a students courses
		  {
			  try {
				  // get student id string
				  String id = commandLine.next();
				  // print all credit courses of student
				  registry.printStudentCourses(id);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, id");
			  }
		  }
		  else if (command.equalsIgnoreCase("PST")) //prints a student's transcript
		  {
			  try {
				  // get student id string
				  String id = commandLine.next();
				  // print student transcript
				  registry.printStudentTranscript(id);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, id");
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG")) // set final grade of student

		  {
			  try {
				  // get course code, student id, numeric grade
				  String code = commandLine.next().toUpperCase();
				  String id = commandLine.next();
				  double grade = commandLine.nextDouble();
				  // use registry to set final grade of this student (see class Registry)
				  registry.setFinalGrade(code, id, grade);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, code, id, grade");
			  }
		  }
		  else if (command.equalsIgnoreCase("SCN")) 
		  {
			  try {
				  // get course code
				  String code = commandLine.next().toUpperCase();
				  // sort list of students in course by name (i.e. alphabetically)
				  registry.sortCourseByName(code);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, code");
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("SCI")) //Sorts students in a course by ID
		  {
			  try {
				  // get course code
				  String code = commandLine.next().toUpperCase();
				  // sort list of students in course by student id
				  registry.sortCourseById(code);
				  // see class Registry
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, code");
			  }
		  }
		  else if (command.equalsIgnoreCase("SCH")) //Schedules a course for a certain day, start time and duration
		  {
			  try {
				  // get course code
				  String code = commandLine.next().toUpperCase();	
				  // get day
				  String day = commandLine.next();
				  // get start time 
				  int startTime = commandLine.nextInt();
				  // get duration 
				  int duration = commandLine.nextInt();
				  schedule.setDayAndTime(code, day, startTime, duration);
			  }
				catch (UnknownCourseException|InvalidDayException|InvalidTimeException|InvalidDurationException|LectureTimeCollisionException e) {
					System.out.println(e.getMessage());
				}
			  	catch (Exception e) {
			  		System.out.println("Invalid input. Correct format: command, code, day, start time, duration");
			  	}

		  }
		  else if (command.equalsIgnoreCase("CSCH")) //Clears the schedule of the given course 
		  {
			  try {
				  // get course code
				  String code = commandLine.next().toUpperCase();	
				  schedule.clearSchedule(code);
			  }
			  catch (Exception e) {
				  System.out.println("Invalid input. Correct format: command, code");
			  }
		  }
		  else if (command.equalsIgnoreCase("PSCH")) //Prints the entire schedule.
		  {
				  schedule.printSchedule();
		  }
		  
		  //input
		  System.out.print("\n>");
	  }
  }
  /**
   * Checks if a string is only alphabetical characters
   * @param str Name
   * @return True if string is only alphabet
   */
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      for (int i = 0; i < str.length(); i++) {
    	  if(!Character.isLetter(str.charAt(i))) {
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
  public static boolean isNumeric(String str)
  {
      for (int i = 0; i < str.length(); i++) {
    	  if(!Character.isDigit(str.charAt(i))) {
    		  return false;
    	  }
      }
      return true;
  }
  
  
}