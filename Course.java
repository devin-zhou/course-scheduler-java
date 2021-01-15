/*
 * Devin Zhou
 */
public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	/**
	 * Default Constructor
	 */
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	/**
	 * Constructor
	 * @param name Name of course
	 * @param code Code of course
	 * @param descr Description of course
	 * @param fmt Format of the course
	 */
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	
	//Getters 
	public String getCode()
	{
	   return code;
	}
	   
	public String getName()
	{
	  return name;
	}
	   
	public String getFormat()
	{
	  return format;
	}
	   
	public String getDescription()
	{
	  return code + " - " + name + "\n" + description + "\n" + format;
	}
	
	public String getDescr() {
		return description;
	}

	public String getInfo()
	{
		return code +" - " + name;
	}
	 
	// static method to convert numeric score to letter grade string 
	 /**
	  * 
	  * @param score Numerical value
	  * @return Alphabetical represenation of grade
	  */
	public static String convertNumericGrade(double score)
	{
		 if (score >= 90) 
			 return "A+";
		 else if (score <= 89 && score >= 85) 
			 return "A";
		 else if (score <= 84 && score >= 80) 
			 return "A-";
		 else if (score <= 79 && score >= 77) 
			 return "B+";
		 else if (score <= 76 && score >= 73) 
			 return "B";
		 else if (score <= 72 && score >= 70) 
			 return "B-";
		 else if (score <= 69 && score >= 67) 
			 return "C+";
		 else if (score <= 66 && score >= 63) 
			 return "C";
		 else if (score <= 62 && score >= 60) 
			 return "C-";
		 else if (score <= 59 && score >= 57) 
			 return "D+";
		 else if (score <= 56 && score >= 53) 
			 return "D";
		 else if (score <= 52 && score >= 50) 
			 return "D-";
		 else
			 return "F";
	} 
}
