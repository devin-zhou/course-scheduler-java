/*
 * Devin Zhou
 */

public class InvalidDayException extends Exception {

	private static final long serialVersionUID = 108916007294676390L;
	public InvalidDayException() {}
	public InvalidDayException (String message) {
		super(message);
	}
}