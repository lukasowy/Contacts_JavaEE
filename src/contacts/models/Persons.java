package contacts.models;

import java.util.Date;

public class Persons {

	private int idPersons;
	private String personName;
	private String personLastname;
	private String personEmail;
	private String personHomeTel;
	private String personJobTel;
	private String personAdressStreet;
	private String personHomeNumber;
	private String personFlatNumber;
	private String personCity;
	private String personCode;
	private Date personBirth;

	public Persons() {
	}

	public Persons(int idPersons, String personName, String personLastname, String personEmail,
			String personHomeTel, String personJobTel, String personAdressStreet, String personHomeNumber,
			String personFlatNumber, String personCity, String personCode, Date personBirth) {
		super();
		this.idPersons = idPersons;
		this.personName = personName;
		this.personLastname = personLastname;
		this.personEmail = personEmail;
		this.personHomeTel = personHomeTel;
		this.personJobTel = personJobTel;
		this.personAdressStreet = personAdressStreet;
		this.personHomeNumber = personHomeNumber;
		this.personFlatNumber = personFlatNumber;
		this.personCity = personCity;
		this.personCode = personCode;
		this.personBirth = personBirth;
	}

	public int getIdPersons() {
		return idPersons;
	}

	public void setIdPersons(int idPersons) {
		this.idPersons = idPersons;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonLastname() {
		return personLastname;
	}

	public void setPersonLastname(String personLastname) {
		this.personLastname = personLastname;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonHomeTel() {
		return personHomeTel;
	}

	public void setPersonHomeTel(String personHomeTel) {
		this.personHomeTel = personHomeTel;
	}

	public String getPersonJobTel() {
		return personJobTel;
	}

	public void setPersonJobTel(String personJobTel) {
		this.personJobTel = personJobTel;
	}

	public String getPersonAdressStreet() {
		return personAdressStreet;
	}

	public void setPersonAdressStreet(String personAdressStreet) {
		this.personAdressStreet = personAdressStreet;
	}

	public String getPersonHomeNumber() {
		return personHomeNumber;
	}

	public void setPersonHomeNumber(String personHomeNumber) {
		this.personHomeNumber = personHomeNumber;
	}

	public String getPersonFlatNumber() {
		return personFlatNumber;
	}

	public void setPersonFlatNumber(String personFlatNumber) {
		this.personFlatNumber = personFlatNumber;
	}

	public String getPersonCity() {
		return personCity;
	}

	public void setPersonCity(String personCity) {
		this.personCity = personCity;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Date getPersonBirth() {
		return personBirth;
	}

	public void setPersonBirth(Date personBirth) {
		this.personBirth = personBirth;
	}

}
