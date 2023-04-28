package film_database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;


public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ID=1;
	private HashMap<Integer, Production> databaseItems;
	
	public Database() 
	{
		setDatabaseItems(new HashMap<Integer, Production>());
	}

	public HashMap<Integer, Production> getDatabaseItems() {
		return databaseItems;
	}

	public void setDatabaseItems(HashMap<Integer, Production> databaseItems) {
		this.databaseItems = databaseItems;
	}
	
	public int addFilm(String name,short year)
	{
		Production newItem = new Film(name, year);
		databaseItems.put(ID++, newItem);
		return ID - 1 ;
	}
	
	public int addAnime(String name, short year, byte age)
	{
		Production newItem = new Anime(name, year, age);
		databaseItems.put(ID++, newItem);
		return ID - 1 ;
	}
	
	public boolean DelProduction(Integer productKey)
	{
		
		return databaseItems.remove(productKey) != null;
	}
	
	public Production getProduction(int ID)
	{
		return databaseItems.get(ID);
	}
	
	public boolean PrintDatabaseIOnlyName()
	{
		if (databaseItems.size() != 0)
		{
			for (Integer item: databaseItems.keySet()) {
			    String key = item.toString();
			    String value = databaseItems.get(item).toString();
			    System.out.println(key + " " + value);
			}
			return true;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}
	/*
	public boolean PrintAllDatabase()
	{
		if (databaseItems.size() != 0)
		{
		    System.out.println("     Typ     Nazev          Reziser             Rok vydani   Doporuceny vek");
			for (Integer item: databaseItems.keySet()) {
			    String toStr = item.toString();
			    String name = databaseItems.get(item).toString();
			    String director = databaseItems.get(item).getDirector();
			    short year = databaseItems.get(item).getYearOfPublication();
			    short age = databaseItems.get(item).getAge();

			    if (databaseItems.get(item).getClass() == Anime.class)
			    {
			    	System.out.println(toStr + "    " + name + "       " + director + "             " + year + "          " + age);
				    System.out.print("Seznam animatoru:");
			    }
			    else 
			    {
				    System.out.println(toStr + "    " + name + "         " + director + "             " + year);
				    System.out.print("Seznam hercu:");
			    }
			    databaseItems.get(item).PrintListActors();
			    System.out.println();
			}
			return true;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}*/
	public boolean PrintAllDatabase() {
	    if (databaseItems.size() != 0) {
	        System.out.println(String.format("%-9s%-20s%-20s%-20s%-20s", "Typ", "Nazev", "Reziser", "Rok vydani", "Doporuceny vek"));

	        for (Integer item : databaseItems.keySet()) {
	            String toStr = item.toString();
	            String name = databaseItems.get(item).toString();
	            String director = databaseItems.get(item).getDirector();
	            short year = databaseItems.get(item).getYearOfPublication();
	            short age = databaseItems.get(item).getAge();

	            if (databaseItems.get(item).getClass() == Anime.class) {
	                System.out.println(String.format("%-2s%-27s%-20s%-20s%-20s", toStr, name, director, year, age));
	                System.out.print("Seznam animatoru:");
	            } else {
	                System.out.println(String.format("%-2s%-27s%-20s%-20s", toStr, name, director, year));
	                System.out.print("Seznam hercu:");
	            }

	            databaseItems.get(item).PrintListActors();
	            System.out.println();
	        }
	        return true;
	    } else {
	        System.out.println("V databazi neni zadany zadny film");
	        return false;
	    }
	}

	public Production FindByName(String word)
	{
		for (Integer item: databaseItems.keySet()) {
		    String name = databaseItems.get(item).getName();
		    if (name.equals(word))
		    {
		    	return databaseItems.get(item);
		    }
		}
		System.out.println("Film nenalezen");
		return null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}
	public boolean Sort()
	{
		if (databaseItems.size() != 0)
		{
			for (Integer item: databaseItems.keySet()) {
			    String key = item.toString();
			    databaseItems.get(item).SortFeedback();
			    
			    String value = databaseItems.get(item).toString();
			    System.out.println(key + " " + value);
			}
			return true;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return false;
		}
	}
	
	public Production Find(Scanner scan, Database database) 
	{
		System.out.println("Zadejte vyraz:");
		
		int count=0;
		String name = null;
		String string = scan.nextLine();
		System.out.println("Nalezeno:");
		for (Integer item: databaseItems.keySet()) {
			name = databaseItems.get(item).getName();
			int condition= (name.indexOf(string));
			if ( condition > -1)
			{
			    count++;
			    System.out.println("- " +name);
			}
			
		}
		if (count != 0) 
			{
				System.out.println("Vyberte film:");
				return FindByName(scan.nextLine());
			}
		 
		return null;
	}
	
	public void FindHuman()
	{
		int shoda=0;
		ArrayList<Production> temp = new ArrayList<>();
		Set<Human> hs;
		
		if (databaseItems.size() != 0)
		{
			
			for (Integer item1: databaseItems.keySet()) {
			    Production film1 = databaseItems.get(item1);
			    temp.add(film1);
			    
			    for (int i = 0; i < film1.effectives.size(); i++) {
					Human actor1 = film1.effectives.get(i);
					
				
			    	for (Integer item2: databaseItems.keySet()) {
					    Production film2 = databaseItems.get(item2);
					    
					    			
					    if (temp.contains(film2))
					    	continue;
					    if (!film1.getName().equals(film2.getName()))
					    {
					    	
					    	for (int j = 0; j < film2.effectives.size(); j++) {
								Human actor2 = film2.effectives.get(j);
								
						    	if (actor2.getFullName().equals(actor1.getFullName()))
						    	{
						    		actor1.addProductions(film1);
						    		actor1.addProductions(film2);
							    	System.out.println("shoda: " + actor1.getFullName() + " - " + film1.getName() + " - " + film2.getName());
							    	shoda++;
						    	}
						    
					    	}
					    	
					    }
					    
						
					}
					
				}
			    
			    
			}
			System.out.println(shoda);
			return;
		}
		else {
			System.out.println("V databazi neni zadany zadny film");
			return;
		}
	}

	
	public void SaveToFile() throws IOException
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		if (directory.exists())
		{
			
			for (Integer item : databaseItems.keySet()) {
				FileWriter fw = null; BufferedWriter out = null;
				
				File file = new File(directory.getName()+ File.separator + databaseItems.get(item).getName().trim() +".txt");
				fw = new FileWriter(file);
				out = new BufferedWriter(fw);
				
				try {
				 	out.write(new String("ID: " + databaseItems.get(item).getID() + " \n"
				 			+ "Typ: "+ databaseItems.get(item).getType() + " \n"
				 			+ "Nazev: "+ databaseItems.get(item).getName() + "\n"
				 			+ "Reziser: "+ databaseItems.get(item).getDirector() + "\n"
				 			+ "Rok: "+ databaseItems.get(item).getYearOfPublication() + "\n"));
				 	
				 	if (databaseItems.get(item).getClass() == Anime.class) {
				 		out.write(new String("Doporuceny vek: " + databaseItems.get(item).getAge() + "\n")); }
				 	
				 	out.write(new String("Ucinkujici: "));
				 	for (Human film : databaseItems.get(item).getActors()) {
						out.write(new String( film.getFullName() + ", " ));
					}
				 	
					 	out.write(new String("\nHodnoceni: "));
					 	for (Feedback feedback : databaseItems.get(item).getFeedback()) {
						out.write(new String("\n"+feedback.getNumber() + " - " + feedback.getComment()));
					}
				 	}
				
				catch (IOException e) {
				System.out.println("Soubor nelze otevrit");
				} 
				
				finally {
				out.close(); fw.close();
				}
				
			}
		}
		else 
		{
			directory.mkdir();
			SaveToFile();
		}
		
	}	

	public void LoadFromFiles() throws IOException
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		
		if (directory.exists())
		{
			
			for (File file : directory.listFiles()) {
				
				FileReader fr = null; BufferedReader in = null;
				String oddelovac ="[ :] " ;
				String radek;
				String [] castiTextu;
				int id= 0;
				int idx=0;
				String name = null;
				short year=0;
				String type=null;
				String directorName=null;
				String directorSurame=null;
				byte age=0;
				
				fr = new FileReader(file);
				in = new BufferedReader(fr);
				
				//ID
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				id = Integer.parseInt(castiTextu[1].trim());

				//Type
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				type=castiTextu[1].trim();
				
				//Name
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				name=castiTextu[1].trim();
				
				
				//Reziser
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				String[] splitDirectorName = castiTextu[1].split(" ");
				directorName=splitDirectorName[0].trim();
				directorSurame=splitDirectorName[1].trim();
				
				//Year
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				year=Short.parseShort(castiTextu[1]);
				
				
				if (type.equalsIgnoreCase("Film"))
				{
					idx = addFilm(name, year);
				}
				
				else 
				{
					//Age
					radek = in.readLine();
					castiTextu = radek.split(oddelovac);
					age=Byte.parseByte(castiTextu[1]);
					idx = addAnime(name, year, age);
				}
				
				//Director
				getProduction(idx).setDirector(directorName, directorSurame);
				getProduction(idx).setID(id);
				
				//Actors
				radek = in.readLine();
				castiTextu = radek.split(oddelovac);
				String splitActors = ",";			//  brad pitt, tom hanks, ade adel, 
				String splitNames = " ";			//brad pitt 
				
				//if ()              // dodelat, kdyt nejsou zadani herci (jakoze nemusi) tak aby to fungovalo i takto
				for (String s : castiTextu[1].split(splitActors))
				{
					
					String []string = s.trim().split(splitNames);
					if (string.length > 1) {
						getProduction(idx).addActor(string[0], string[1]); }
					
				}
				
				//Feedbacks
				radek = in.readLine();
				while ((radek = in.readLine()) != null)
				{
					castiTextu = radek.split(" - ");
					getProduction(idx).setFeedback(Byte.parseByte(castiTextu[0].trim()), castiTextu[1].trim());
				}
					in.close(); 
					fr.close();
			}
			
		}
		else 
		{
			System.out.println("Adresar: \"" + System.getProperty("user.dir") + File.separator +"database"+"\" - nenalezen");
		}
	 
	}

	public void ClearDatabaseFile()
	{
		File directory = new File(System.getProperty("user.dir") + File.separator +"database");
		if (directory.exists())
		{
			
		}
	}
	
	//------------------------------------------------

	private Connection conn; 
	public boolean connect() 
	{ 
       conn= null; 
       try 
       {
              conn = DriverManager.getConnection("jdbc:sqlite:myDB.db");                       
       } 
       catch (SQLException e) 
       { 
            System.out.println(e.getMessage());
            return false;
       }
       return true;
	}
	public void disconnect() 
	{ 
		if (conn != null) 
		{
	       
			try 
			{     
				conn.close();  
			} 
		
			catch (SQLException ex) 
			{ 
				System.out.println(ex.getMessage()); 
			}
		}
	}
	
	public boolean createTable()
	{
	    if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS SQLDAtabase (" + "id integer PRIMARY KEY," +"type varchar(255) NOT NULL,"+ "name varchar(255) NOT NULL,"+"director varchar(255) NOT NULL,"+ "year int,"+ "recomAge int,"+"performers varchar(255),"+"feedback varchar(255)"+ ");";
	    try
	    {
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) 
	    {
	    	System.out.println(e.getMessage());
	    }
	    return false;
	}
	
	public String getPerformers(Integer item)
	{
		String performers = "";
			for (Human film : databaseItems.get(item).getActors()) 
			{
				performers += new String( film.getFullName() + "," );
			}
		return performers;		
	}
	
	public String getFeedback(Integer item)
	{
		String itemFeedback = "";
			for (Feedback feedback : databaseItems.get(item).getFeedback())
			{
				itemFeedback += new String(feedback.getNumber() + "-" + feedback.getComment()+",");
			}
		return itemFeedback;		
	}
	
	public void insertRecords()
	{
		for (Integer item : databaseItems.keySet()) 
		{
			
			String sql = "INSERT INTO SQLDAtabase(type,name,director,year,recomAge,performers,feedback) VALUES(?,?,?,?,?,?,?)";
			try 
			{
				PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, databaseItems.get(item).getType());
				pstmt.setString(2, databaseItems.get(item).getName());
				pstmt.setString(3, databaseItems.get(item).getDirector());
				pstmt.setInt(4, databaseItems.get(item).getYearOfPublication());
				if (databaseItems.get(item).getClass() == Anime.class)
				{
					pstmt.setInt(5, databaseItems.get(item).getAge());
				}
				else
				{
					pstmt.setInt(5, 0);
				}
				pstmt.setString(6, getPerformers(item));
				pstmt.setString(7, getFeedback(item));
				pstmt.executeUpdate();
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	
	public void loadRecordsFromDatabase()
	{
        String sql = "SELECT id, type, name, director, year, recomAge, performers, feedback FROM SQLDAtabase";
        try 
        {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
             int ID = 0;
             while (rs.next()) 
             {            	
              		String name;
             		String director;
      				String directorName;
      				String directorSurname;
      				String performers;
      				short year;
      				byte age;
      				
      				name = rs.getString("name");
      				director = rs.getString("director");
      				String[] parts = director.split(" ");
      				directorName = parts[0];
      				directorSurname = parts[1];
      				year = rs.getShort("year");
      				if(rs.getString("type").equals("Film"))
      				{
      					ID = addFilm(name, year);
      				}
      				else
      				{
      					age = rs.getByte("recomAge");
      					ID = addAnime(name, year, age);
      				}
      					
      				performers = rs.getString("performers");     				
     				String[] parts2 = performers.split(",");     				
      				
      				for(int i = 0; i < parts2.length; i++)
     				{
     					String performer=parts2[i];
     					String performerName;
     					String performerSurname;
     					String[] parts3 = performer.split(" ");
     					performerName=parts3[0];
     					performerSurname=parts3[1];     					    					     					
     					getProduction(ID).addActor(performerName, performerSurname);
     				}
      				getProduction(ID).setDirector(directorName,directorSurname);
      				
      				Production insertFeedback = FindByName(rs.getString("name"));
      				
      				
      				String evaluation;
      				evaluation = rs.getString("feedback");
      				String[] parts4 = evaluation.split(",");
      				for(int i = 0; i < parts4.length; i++)
     				{
     					String evaluation2=parts4[i];
     					byte number;
          				String comment;
     					String[] parts5 = evaluation2.split("-");
     					try 
     					{
     					    number = Byte.parseByte(parts5[0]);
     					    comment = parts5[1];
     					    insertFeedback.setFeedback(number, comment);
     					} catch (NumberFormatException e) 
     					{
     					   
     					}
     				}     				     			      				     				
            	 }
            		
             
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
	}
	public void selectAll()
	{
		String sql = "SELECT id, type, name, director, year, recomAge, performers, feedback FROM SQLDAtabase";
        try 
        {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
             while (rs.next()) 
             {
            		 System.out.println(rs.getInt("id")+  "\t"   +
                			rs.getString("type")+ "\t"+ 
                			rs.getString("name") + "\t" + 
                			rs.getString("director") + "\t" + 
                			rs.getInt("year") + "\t" +                 			
                			rs.getInt("recomAge") + "\t" + 
                			rs.getString("performers") + "\t" + 
                			rs.getString("feedback"));

             }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        
	}
	public void deleteSQLDatabase() 
	{
        String sql = "DELETE FROM SQLDAtabase";
        
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

	//---------------------------------------
	    
	
		
		
	
	
}  // end of database class