package PersonUtility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import Exception.HandleException;
import java.io.File;
import java.io.IOException;

public final class PersonDatabase {
	
	private final List <Person> sortByFirstName;
	private final List <Person> sortBySurnameFirstNameBirthdate;
	private final List <Person> sortByBirthdate;
	private final Map<Date, List <Person>> findByBirthDate;
	private static final Comparator<Person> compareBySurnameFirstNameBirthdate = Comparator.naturalOrder();
	
	private PersonDatabase(List<Person> p){
		sortByFirstName = new ArrayList<>(p);
		sortBySurnameFirstNameBirthdate = p;
		sortByBirthdate = new ArrayList<>(p);
		findByBirthDate = p.stream().collect(
											Collectors.groupingBy(
											Person::getBirthDate,
											TreeMap::new, 
											Collectors.toList())
											);
		sortByFirstName.sort(new FirstNameComparator());
		sortBySurnameFirstNameBirthdate.sort(compareBySurnameFirstNameBirthdate);
		sortByBirthdate.sort(new BirthdateComparator());
	}

	public int listSize(){
		return sortBySurnameFirstNameBirthdate.size();
	}

	
	public PersonDatabase (File file) throws IOException {
		this(InputParser.parser(file));
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws HandleException{
		try{
			output.writeInt(sortBySurnameFirstNameBirthdate.size());
			
			sortByFirstName.forEach( p-> {
				try {
					p.serialize(output);
				} catch (HandleException e) {
					e.printStackTrace();
				}
			});

		}catch(Throwable exception){
			throw new HandleException(exception);
		}
	}

	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws HandleException {
		try{
			int size = input.readInt();
			List <Person> personDB = new ArrayList<>();
			for(int i = 0; input.available() > 0 && i < size; i++ ){
				Person person = Person.deserialize(input);
				personDB.add(person);
			}
			return new PersonDatabase(personDB);
		}catch(Throwable e){
			throw new HandleException(e);
		}
	}

	// assignment 4
	public List<Person> sortedByFirstName() {
		return sortByFirstName;
	}

	// assignment 4
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		return sortBySurnameFirstNameBirthdate;
	}

	// assignment 4
	public List<Person> sortedByBirthdate() {
		return sortByBirthdate;
	}

	// assignment 4
	public List<Person> bornOnDay(Date date) {
		List<Person> person = findByBirthDate.get(date);
		return  person;
	}
}