package film_database;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public  class Human {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int ID=1;
	private String name;
	private String surname;
	private Set<Production> productions;
	private Type type;
	
	public enum Type {
		Director,
		Actor,
		Animator
	}
	
	public Human(String name, String surname,Type type) {
		ID = count.incrementAndGet();
		this.setName(name);
		this.setSurname(surname);
		productions = new HashSet<>();
		this.type=type;
	}

	public Set<Production> getPruductions() {
		return productions;
	}

	public void setPruductions(Set<Production> productions) {
		this.productions = productions;
	}

	public void addProductions(Production production)
	{
		productions.add(production);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getFullName()
	{
		return this.getName() + " " + this.getSurname();
	}
	
	public String getFullNameWithID()
	{
		return this.getID()+ " " + this.getName() + " " + this.getSurname();
	}

	public Type getType() {
		return type;
	}
	
	
	
	
	
}
