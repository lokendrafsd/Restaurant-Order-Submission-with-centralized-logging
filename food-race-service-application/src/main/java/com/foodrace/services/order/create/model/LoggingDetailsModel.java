package main.java.com.foodrace.services.order.create.model;

/**
 * @author lokendrav
 *
 */
public class LoggingDetailsModel {
	/**
	 * @param level
	 * @param msg
	 */
	public LoggingDetailsModel( String level, String msg) {
		super();
		this.level = level;
		this.msg = msg;
	}

	String level;
	String msg;


	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "LoggingDetailsModel [level=" + level + ", msg=" + msg + "]";
	}
}
