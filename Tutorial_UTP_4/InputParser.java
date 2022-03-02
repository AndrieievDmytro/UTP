import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class InputParser {

	// 1. Use regular expresssions (Pattern) for validating input data
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd" 
	//    SimpleDateFormat format "yyyy-MM-dd"

	private static final DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static String noFileException = "noFileException";
	
	private static final String first_name_field = "firstName";
	private static final String surname_field = "surname";
	private static final String birthdate_field = "birthday";

	private static final String name_pattern = "(?:[A-Z]([a-z])+)";
	private static final String firts_name_pattern = "(?<" + first_name_field + ">" + name_pattern + ")";

	private static final String surname_pattern = "(?<" + surname_field + ">" + name_pattern + ")";

	private static final String year_pattern = "(?:(?:[0][0][0][1-9])|(?:[0][0][1-9][0-9])|(?:[0][1-9][0-9]{2})|(?:[1-9][0-9]{3}))";

	private static final String month_pattern = "(?:(?:[0][1-9])"
											+ "|" + "(?:[1][0-2]))";

	private static final String day_pattern = "(?:(?:[0][1-9])"
											+ "|" + "(?:[1-2][0-9])"
											+ "|" + "(?:[3][0-1]))";

	private static final String sep = "-";
	// Regex year, month, day ((?:[0-9]{4}))-((?:[0-9]{2}))-((?:[0-9]{2}))
	private static final String birthdate_pattern = "(?<" + birthdate_field + ">" +
						year_pattern + sep + month_pattern + sep + day_pattern + ")"; 
	
	private static final String whitespace_pattern = "(?:[ \t]+)";

	private static final String line_pattern = firts_name_pattern + whitespace_pattern 
											+ surname_pattern + whitespace_pattern 
											+ birthdate_pattern;

	private static final Pattern regex = Pattern.compile(line_pattern);
	
	private static Person parse(String line){
		Matcher matcher = regex.matcher(line);
		if (!matcher.matches()) {
			return null;
		}
		try { 
			String firstName = matcher.group(first_name_field);
			String surname = matcher.group(surname_field);
			Date birthdate = birthdate(matcher);
			return new Person(firstName, surname, birthdate);
		}catch (Exception exception){
			return null;
		}
	}

	private static Date birthdate (Matcher match) throws ParseException {
		try {
			String input = match.group(birthdate_field);
			return newFormat.parse(input);
		}catch (ParseException e) {
			throw new ParseException(e.getLocalizedMessage(), e.getErrorOffset());
		}
	}

	public static List<Person> parser(File file) throws IOException {
		BufferedReader read = null;
		try{
			List <Person> persons = new ArrayList<>();
			read = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String line;
			while ((line = read.readLine()) != null) {
				Person person = parse(line);
				if (person != null ){
					persons.add(person);
				}
			} 
			return persons;
		}catch (FileNotFoundException e) {
			throw new HandleException(noFileException, "There is no file", e);
		} finally {
			if(read != null) {
				read.close();
			}
		}
	}
}