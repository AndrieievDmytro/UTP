import java.util.Calendar;
import java.util.Date;

public abstract class Person {

	/* 
	To implement an attribute means that you provide a backing field and
	getter or optionally setter for read-write properties/attributes
	 
	NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	THOSE SHOULD BE COMPUTED ON-LINE
	
	attributes:
	* first name (read-only)
	* surname (read-only)
	* birth date (read-only) --- date MUST BE represented by an instance of
	the type designed for date representation (either Date or LocalDate)
	
	* age (derived --- computed based on birth date) --- implemented as a
	getter calculating the difference between the current date and birth date
	*/

	private final String   _firstName; 
	private final String   _surName;
	private final Date 	  _birthDate;	
	
	protected Person ( String firstName , String surName , Date birthDate ){
		_firstName = firstName;
		_surName   = surName;
		_birthDate = birthDate;
	}

	public String getFirstName(){ 
		return this._firstName;
	}
	
	public String getSurName(){
		return this._surName;
	}

	public  Date getBirthDate(){
		return this._birthDate;
	}

	public short getAge(){
		Date currentDate  = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int currDate = calendar.get(Calendar.YEAR);
		calendar.setTime( this._birthDate);
		int birthDate = calendar.get(Calendar.YEAR);
		return (short)(currDate - birthDate);
	}

	@Override
	public String toString(){
		return _birthDate.toString() + " - " + this._firstName + " " + this._surName;
	}
}