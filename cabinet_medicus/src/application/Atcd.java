package application;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Atcd {
	
	private Integer id_atcd;
	private Integer id_patient;
	private String label;
	private String type;
	private Date date;
	private String content;
	private String str_from_date;
	
	public Atcd()
	{
		
	}
	
	public Atcd(String lbl, String tp, Date dt, String cntnt)
	{
		label = lbl;
		setType(tp);
		date = dt;
		content = cntnt;
	}
	
	public Atcd(Integer id_patient_detailed) {

		id_patient = id_patient_detailed;
	}

	public String getLabel() {
		return label;
	}
	public Date getDate() {
		return date;
	}
	public String getContent() {
		return content;
	}
	
	public ArrayList<Atcd> load_antcdent()
	{
		Statement stmt = null;
		String sql = "";
		try
		{
			if(stmt == null)
				stmt = Db_manage.c.createStatement();
			
			sql = "SELECT * FROM antcdent WHERE id_patient = "+id_patient; 
			ResultSet rs = stmt.executeQuery(sql);
				
			ArrayList<Atcd> all_result_tab = new ArrayList<Atcd>(); 
			int i = 0;
			while (rs.next())
			{
				Atcd temp = new Atcd();// rs.getInt("id_patient"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("age")
				temp.id_atcd = rs.getInt("id_atcd");
				temp.id_patient = id_patient;
				temp.label = rs.getString("label");
				temp.type = rs.getString("type");
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date date_temp = formatter.parse(rs.getString("date"));
				temp.date = date_temp;// formatter.format(date)
				temp.str_from_date = formatter.format(date_temp);
				temp.content = rs.getString("content");
				System.out.println(temp);
				
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
	
	public void save_antcd()
	{
		Statement stmt = null;
		String sql = "";
		try
		{
			if(stmt == null)
				stmt = Db_manage.c.createStatement();
			
			// convert date to string
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String reportDate = df.format(date.getTime());
			// end convert date to string
			
			sql = "INSERT INTO antcdent(id_patient, label, type, date, content ) VALUES("+id_patient+", '"+label+"', '"+type+"', '"+reportDate+"', '"+content+"')"; 
			
			stmt.executeUpdate(sql);
		    stmt.close();
			
		}catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		
	}
	
	public int getId_atcd() {
		return id_atcd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId_atcd(Integer id_atcd) {
		this.id_atcd = id_atcd;
	}

	public void setId_patient(Integer id_patient) {
		this.id_patient = id_patient;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStr_from_date() {
		return str_from_date;
	}

	public void setStr_from_date(String str_from_date) {
		this.str_from_date = str_from_date;
	}

	@Override
	public String toString() {
		return "Atcd [id_atcd=" + id_atcd + ", id_patient=" + id_patient
				+ ", label=" + label + ", type=" + type + ", date=" + date
				+ ", content=" + content + ", str_from_date=" + str_from_date
				+ "]";
	}

}
