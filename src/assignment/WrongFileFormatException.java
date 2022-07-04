package assignment;

public class WrongFileFormatException extends Exception {
	String errMsg;

	public WrongFileFormatException(String errMsg) {
		super();
		this.errMsg = errMsg;
	}

}
