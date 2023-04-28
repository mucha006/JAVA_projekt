package film_database;

import film_database.Human.Type;

public class Anime extends Production {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static byte MAXFEEDBACK = 10;
	private byte age;
	

	
	public Anime(String name, short yearOfPublication, byte age) {
		super(name, yearOfPublication);
		this.setAge(age);
	}
	
	@Override
	public String toString() {
		return "animak  " + super.getName();	
		}


	public byte getMaxfeedback() {
		return MAXFEEDBACK;
	}

	@Override
	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}
	
	@Override
	public void addActor(String name, String surname) {
		super.effectives.add(new Human(name,surname, Type.Animator));
	}

	@Override
	public String getType() {
		return "Anime";
	}
}
