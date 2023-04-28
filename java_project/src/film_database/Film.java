package film_database;

import film_database.Human.Type;

public class Film extends Production{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static byte MAXFEEDBACK = 5;
	
	public Film(String name, short yearOfPublication) {
		super(name, yearOfPublication);
		
	}
	
	@Override
	public String toString() {
		return "film   " + super.getName();
	}
	

	public byte getMaxfeedback() {
		return MAXFEEDBACK;
	}
	
	@Override
	public void addActor(String name, String surname) {
		super.effectives.add(new Human(name,surname, Type.Actor));
	}

	@Override
	public byte getAge() {
		return 0;
	}

	@Override
	public String getType() {
		return "Film";
	}
	
	
	
	

}
