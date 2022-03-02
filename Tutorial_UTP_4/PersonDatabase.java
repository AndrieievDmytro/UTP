import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.io.File;

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
											Collectors.toList()));
		
		sortByFirstName.sort(new FirstNameComparator());
		sortBySurnameFirstNameBirthdate.sort(compareBySurnameFirstNameBirthdate);
		sortByBirthdate.sort(new BirthdateComparator());
	}

	public PersonDatabase (File file) throws IOException {
		this(InputParser.parser(file));
	}

	public int mapSize(){
		return findByBirthDate.size();
	}

	public List<Person> sortedByFirstName() {
		return sortByFirstName; // external rule for ordering (based on Comparator --- FirstNameComparator)
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		return sortBySurnameFirstNameBirthdate; // natural order (Comparable)
	}
	
	public List<Person> sortedByBirthdate() {
		return sortByBirthdate; // external rule for ordering (based on Comparator --- BirthdateComparator)
	}
	
	public List<Person> bornOnDay(Date date) {
		List<Person> person = findByBirthDate.get(date);
		return  person;
	}
}