package film_database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import film_database.Human.Type;

public abstract class Production implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final AtomicInteger count = new AtomicInteger(0); 
	protected int ID=1;
	private String name;
	private Human director;
	private ArrayList<Feedback> feedback = new ArrayList<>();
	private short yearOfPublication;
	protected ArrayList<Human> effectives;
	
	public Production(String name, short yearOfPublication)	{
		ID = count.incrementAndGet();
		this.name=name;
		this.yearOfPublication=yearOfPublication;
		effectives = new ArrayList<>();
		
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Feedback> getFeedback() {
		return feedback;
	}


	public void setFeedback(byte feedback, String comment) {
		this.feedback.add(new Feedback(feedback, comment));
	}


	public short getYearOfPublication() {
		return yearOfPublication;
	}


	public void setYearOfPublication(short yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}


	public  int getID() {
		return ID;
	}


	public  void setID(int ID) {
		this.ID = ID;
	}


	public ArrayList<Human> getActors() {
		return effectives;
	}
	public void PrintListActors()
	{
		for (Human human : effectives) {
			System.out.print(human.getFullName() + ",");
		}
		System.out.println();
	}
	public void PrintActorsWithID()
	{
		for (Human human : effectives) {
			System.out.println(human.getFullNameWithID());
		}
	}

	public void addActor(String name, String surname) 
	{
		
	}
	
	public void deleteActor(int id)
	{
		Human human = FindByID(id);
		effectives.remove(human);
	}
	
	public String getDirector()
	{
		return this.director.getFullName();
	}
	public void setDirector(String name, String surname) {
		this.director = new Human(name, surname, Type.Director);
	}
	public Human FindByID(int number)
	{
		for (var item: effectives) 
		{
			int id = item.getID();
			
			  if (id == number)
			  {
			  return item;
			  }
		}
		return null;
	}
	
	public void SortFeedback()
	{
		feedback.sort((o1, o2) -> (o1.getNumber().compareTo(o2.getNumber())));
		Collections.reverse(feedback);
	}
	public void PrintFeedback()
	{
		System.out.println("Hodnoceni:");
		for (Feedback feedback : feedback) {
			System.out.println(feedback.toString());
		}
	}
	
	public abstract String getType();
	
	public abstract byte getMaxfeedback();
	public abstract byte getAge();
	
	
}