package application;

import java.util.Date;

public class Patient {
	
	private Integer id_patient;
	private String lastname;
	private String firstname;
	private String othername;
	private Date naissance;
	private Integer age;
	
	private String gender;
	private String address;
	private String region;
	private String city;
	private String postal_code;
	
	private String num_tel;
	private String num_mobil;
	private String num_fax;
	private String num_tel_pro;
	private String mail;
	private String site;
	
	
	
	public Patient(int id, String nom, String prenom, int ag)
	{
		id_patient = id;
		lastname = new String(nom);
		firstname = new String(prenom);
		age = new Integer(ag);
	}
	
	
	
	@Override
	public String toString() {
		return lastname + "," + firstname;
	}



	public Integer getId_patient() {
		return id_patient;
	}



	public String getLastname() {
		return lastname;
	}



	public String getFirstname() {
		return firstname;
	}



	public String getOthername() {
		return othername;
	}



	public Date getNaissance() {
		return naissance;
	}



	public Integer getAge() {
		return age;
	}



	public String getGender() {
		return gender;
	}



	public String getAddress() {
		return address;
	}



	public String getRegion() {
		return region;
	}



	public String getCity() {
		return city;
	}



	public String getPostal_code() {
		return postal_code;
	}



	public String getNum_tel() {
		return num_tel;
	}



	public String getNum_mobil() {
		return num_mobil;
	}



	public String getNum_fax() {
		return num_fax;
	}



	public String getNum_tel_pro() {
		return num_tel_pro;
	}



	public String getMail() {
		return mail;
	}



	public String getSite() {
		return site;
	}


	

	
	
	
	

}