import java.util.Date;

public class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	public String  getFirstName() {
		return this._firstName;
	}

	public String getSurname(){
		return this._surname;
	}

	public Date getBirthDate(){
		return this._birthdate;
	}

	@Override
	public int compareTo(Person otherPerson) {
		// natural order based on:
		// (1) surname;
		// (2) first name;
		// (3) birth date.
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

	@Override
	public String toString(){
		return this._firstName + " " + this._surname + " " + this._birthdate;
	}
}