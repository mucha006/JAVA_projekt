package film_database;

import java.util.concurrent.atomic.AtomicInteger;

public class Feedback {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int ID=1;
	private String comment;
	private int number;
	
	public Feedback(int number, String comment)
	{
		setID(count.incrementAndGet());
		this.setNumber(number);
		this.setComment(comment);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return number + " - " + comment;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
