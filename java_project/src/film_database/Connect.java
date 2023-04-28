package film_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect 
{
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
	    String sql = "CREATE TABLE IF NOT EXISTS zamestnanci (" + "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL,"+ "rodneCislo bigint, " + "popis varchar(50), " + "plat real" + ");";
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
	
	public void insertRecord(String jmeno, long RC, String popis,float plat ) 
	{
        String sql = "INSERT INTO zamestnanci(jmeno,rodneCislo,popis,plat) VALUES(?,?,?,?)";
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, jmeno);
            pstmt.setLong(2, RC);
            pstmt.setString(3, popis);
            pstmt.setFloat(4, plat);
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
	
	public void selectAll()
	{
        String sql = "SELECT id, jmeno,rodneCislo, popis, plat FROM zamestnanci";
        try 
        {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
             while (rs.next()) 
             {
                	System.out.println(rs.getInt("id") +  "\t" +  
                			rs.getString("jmeno") + "\t" + 
                			rs.getLong("rodneCislo") + "\t" + 
                			rs.getString("popis") + "\t" + 
                			rs.getLong("plat"));
             }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
	}			

}

