package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Db_manage {
	
	static Connection c = null;
	
	public static Connection make_connection(){
	
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:C:\\UsersAyb\\git\\cabinet_medicus\\medicus_db.sqlite");
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    //System.out.println("Opened database successfully");
    
	return c;
    
	}
	
	public static void create_table(){
		Statement stmt = null;
		
	      try {
	    	  stmt = c.createStatement();
		      String sql = "CREATE TABLE Hello " +
		                   "(ID INT PRIMARY KEY     NOT NULL," +
		                   " NAME           TEXT    NOT NULL, " + 
		                   " AGE            INT     NOT NULL, " + 
		                   " ADDRESS        CHAR(50), " + 
		                   " SALARY         REAL)"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	}
	
	
	public static void insert_in_table(ArrayList<String> list_values, String table, String message){
		Statement stmt = null;
		String fields = "";
		
	      try {
	    	  
	    	  String sql_values = "" ;
	    	  
	    	  int length = list_values.size();
	    	  
	    	  for(int i=0;i<length; i++)
	    		  sql_values += "'"+list_values.get(i)+"',";
	    	  
	    	  sql_values = sql_values.substring(0, sql_values.length()-1);
	    	  
	    	  stmt = c.createStatement();
	    	  
	    	  String sql = "PRAGMA table_info("+table+")"; 
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      
		      while (rs.next()) 
		    	  if(rs.getInt("pk") == 0)
		    		  fields += rs.getString("name")+",";
		         
		      rs.close();
		      
		      fields = fields.substring(0, fields.length()-1);
		      
		      //System.out.print(fields+"//");
		      //System.out.print(sql_values+"//");
		      
		      sql = "INSERT INTO " +table+"("+fields+") VALUES("+sql_values+")"; 
		      //System.out.print(sql+"//");
		      stmt.executeUpdate(sql);
		      
		      JOptionPane.showMessageDialog(null, message);
		      stmt.close();
		      
	        } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	}
	
	public static ArrayList<Patient> load_patients(String table)
	{
		Statement stmt = null;
		String sql = "";
		try
		{
			if(stmt == null)
				stmt = c.createStatement();
			
			sql = "SELECT * FROM "+table; 
			ResultSet rs = stmt.executeQuery(sql);
				
			ArrayList<Patient> all_result_tab = new ArrayList<Patient>(); 
			int i = 0;
			while (rs.next())
			{
				Patient temp = new Patient(rs.getInt("id_patient"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"));
				all_result_tab.add(i,temp);
				i++;
			}
			   
		    stmt.close();
   
			return all_result_tab;
			
		}catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		
		
	}
	
	public static ArrayList<String> load_from_table_cond_where(String table, int id)
	{
		Statement stmt = null;
		String sql = "";
		try
		{
			stmt = c.createStatement();
			sql = "SELECT * FROM "+table+" WHERE id_patient = "+id; 
			ResultSet rs = stmt.executeQuery(sql);
				
			ArrayList<String> all_result_tab = new ArrayList<String>(); 
			int i = 0;
			while (rs.next())
			{
				all_result_tab.add(i,rs.getString("nom")+" "+rs.getString("prenom"));
				i++;
				 //System.out.print(rs.getString("nom"));
		        
			}
			   
		    stmt.close();
   
			return all_result_tab;
			
		}catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		
	}
	
	
}