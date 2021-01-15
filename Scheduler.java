
/*
 * Devin Zhou
 */

import java.util.TreeMap;

public class Scheduler
{
	// In main() after you create a Registry object, create a Scheduler object and
	// pass in the students ArrayList/TreeMap
	TreeMap<String, ActiveCourse> schedule;

	/**
	 * Constructor for Scheduler
	 * 
	 * @param courses TreeMap containing course code and ActiveCourse object.
	 */
	public Scheduler(TreeMap<String, ActiveCourse> courses) {
		schedule = courses;
	}

	/**
	 * Schedules a course for a certain day, start time and duration
	 * 
	 * @param courseCode Code of the course
	 * @param day        The day the class will take place
	 * @param startTime  The starting time of the class
	 * @param duration   The duration of the class
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws UnknownCourseException,
			InvalidDayException, InvalidTimeException, InvalidDurationException, LectureTimeCollisionException {
		//Error checking
		if (!schedule.containsKey(courseCode)) {
			throw new UnknownCourseException("Unknown course: " + courseCode);
		} else if (!day.equalsIgnoreCase("Mon") && !day.equalsIgnoreCase("Tue") && !day.equalsIgnoreCase("Wed")
				&& !day.equalsIgnoreCase("Thur") && !day.equalsIgnoreCase("Fri")) {
			throw new InvalidDayException(day + " is not a valid day.");
		} else if ((startTime < 800 || startTime > 1700) || (startTime + (duration * 100) > 1700)) {
			throw new InvalidTimeException(startTime + " is not a valid time for class.");
		} else if (!(duration == 1 || duration == 2 || duration == 3)) {
			throw new InvalidDurationException(duration + " is not a valid duration for class.");
		} else if (collisionFound(courseCode, day, startTime, duration)) {
			throw new LectureTimeCollisionException(courseCode + " collides with another lecture.");
		}
		//scheduling the course
		schedule.get(courseCode).setLectureDay(day);
		schedule.get(courseCode).setLectureDuration(duration);
		schedule.get(courseCode).setLectureStart(startTime);
	}

	/**
	 * Method checks for time collisions between scheduled courses
	 * 
	 * @param courseCode Code of the input course
	 * @param day        Day of the course
	 * @param startTime  Start time of the course
	 * @param duration   Duration of course
	 * @return boolean for presence of collision
	 */
	public boolean collisionFound(String courseCode, String day, int startTime, int duration) {
		int endTime = (duration * 100) + startTime;
		for (String key : schedule.keySet()) {
			String otherDay = schedule.get(key).getLectureDay();
			int otherStart 	= schedule.get(key).getLectureStart();
			int otherEnd 	= otherStart + (schedule.get(key).getLectureDuration() * 100);
			if (otherDay != null) {
				if (otherDay.equalsIgnoreCase(day)) {
					//collision timing checks
					if (startTime == otherEnd) {
						continue;
					} else if (endTime == otherStart) {
						continue;
					} else if (startTime < otherEnd && startTime > otherStart) {
						return true;
					} else if (endTime > otherStart && startTime < otherStart) {
						return true;
					} else if (startTime == otherStart && duration == schedule.get(key).getLectureDuration()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Clears the schedule of the given course
	 * @param courseCode Code of the course to be cleared
	 */
	public void clearSchedule(String courseCode) {
		if(schedule.containsKey(courseCode)) {
			schedule.get(courseCode).setLectureDay("");
			schedule.get(courseCode).setLectureStart(0);
			schedule.get(courseCode).setLectureDuration(0);
		}
	}

	/**
	 * Prints out the entire schedule
	 */
	public void printSchedule() {
		String[][] table = new String[10][6];
		// filling in row header (day)
		table[0][0] = "";
		table[0][1] = "Mon";
		table[0][2] = "Tue";
		table[0][3] = "Wed";
		table[0][4] = "Thu";
		table[0][5] = "Fri";

		int start = 800;
		for (int i = 1; i < 10; i++) { // fills in time column
			if (start < 1000) {
				table[i][0] = "0" + start;
			} else {
				table[i][0] = Integer.toString(start);
			}
			start += 100;
		}

		for (String key : schedule.keySet()) {
			for (int j = 1; j < 6; j++) { // traverses col (day)
				if (table[0][j].toUpperCase().equalsIgnoreCase(schedule.get(key).getLectureDay())) { //Check for matching day
					for (int i = 1; i < 10; i++) { // traverses row (time)
						if (schedule.get(key).getLectureStart() == Integer.parseInt(table[i][0])) { //check for matching time
							for (int k = 0; k < schedule.get(key).getLectureDuration(); k++) { //loop for duration of class
								table[i+k][j] = schedule.get(key).getCode();
							}
						}
						
					}
				}
			}
		}
		Array2dPrint(table); //calls method to print out schedule
	}

	/**
	 * Prints out the 2D Array
	 * @param array the array to be printed
	 */
	public void Array2dPrint(String[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == null) {
					System.out.printf("%-10.6s", "");
				}
				else{
					System.out.printf("%-10.6s", array[i][j]); 
				}
			}
			System.out.println();//newline
		}
	}

}
