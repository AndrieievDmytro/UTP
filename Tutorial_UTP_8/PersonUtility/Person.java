package PersonUtility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;
import Exception.HandleException;

 

public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;

	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}
	
	// assignment 8
	public void serialize(DataOutputStream output)throws HandleException{
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
		try{
			output.writeUTF(_firstName);
			output.writeUTF(_surname);
			output.writeLong(_birthdate.getTime());
			
		}catch(Throwable exception){
			throw new HandleException(exception);
		};
	}
	
	public static Person deserialize(DataInputStream input) throws HandleException{
		try{
			String firstName = input.readUTF();
			String surName = input.readUTF();
			Date birthDate = new Date (input.readLong());
			return new Person(firstName, surName, birthDate);
		}catch(Throwable exception){
			throw new HandleException(exception);
		}
	}

	@Override
	public int compareTo(Person otherPerson) {
		int surnameDiff = getSurname().compareToIgnoreCase(otherPerson.getSurname());
		int firstNameDiff = getFirstName().compareToIgnoreCase(otherPerson.getFirstName());
		int birthdateDiff = getBirthDate().compareTo(otherPerson.getBirthDate());
		
		if (surnameDiff != 0) {
			return surnameDiff;
		} else if (firstNameDiff != 0) {
			return firstNameDiff;
		} else{
			return birthdateDiff;
		}
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getSurname() {
		return _surname;
	}

	public Date getBirthDate() {
		return _birthdate;
	}

	@Override
	public String toString(){
		return this._firstName + " " + this._surname + " " + this._birthdate;
	}
}