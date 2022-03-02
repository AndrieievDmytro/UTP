import java.util.Calendar;
import java.util.Date;


public abstract class Person {

	// (assignment 02)
	// attributes:
	// * first name
	// * surname
	// * birth date
	// * age (derived --- computed based on birth date)
	
	// (assignment 03)
	// methods:
	// * is older than other person
	// * is younger than other person
	// * compare age with other person's age

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

	public Short getAge(){
		Date currentDate  = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int currDate = calendar.get(Calendar.YEAR);
		calendar.setTime( this._birthDate);
		int birthDate = calendar.get(Calendar.YEAR);
		return (short)(currDate - birthDate);
	}

		public boolean isOlder(Person p){
		return comaparePersonAge(p) > 0;
	}
	
	public Person getYounger(Person p){
		return comaparePersonAge(p) < 0 ?  p : this;
	}

	public boolean isYounger(Person p){
		return comaparePersonAge(p) < 0;
	}
	
	public int comaparePersonAge(Person p){
		return getAge().compareTo(p.getAge());
	}

	@Override
	public String toString(){
		return _birthDate.toString() + " - " + this._firstName + " " + this._surName;
	}
}
