import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class HumanResourceStatistics {
	
	private HumanResourceStatistics() {}

	// The best solution is to impleent the below features as static methods having a list of Employee as the first input argument.
	// In addition the list of arguments may be augmented with parameters required for the given feature.
	
	// (assignment 03)
	// methods:

	//1 * search for Employees older than given employee and earning less than him
	public static List<Employee> olderThanAndEarnLess(List<Employee> allEmployees, Employee employee) {
		
		if(employee == null) return null;

		final Predicate<Employee> olderThan = e -> e.isOlder(employee);
		final Predicate<Employee> EarnMore = e ->  e.salaryIsLess(employee);
		final Predicate<Employee> olderThanAndEarnMore = olderThan.and(EarnMore);

		
		return allEmployees 
		.stream()
		.filter(olderThanAndEarnMore)
		.collect(Collectors.toList());
	}
	
	//2 * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
		
		final Predicate<Trainee> practiceLengthLongerThan = e -> e.practiceIsLonger(Long.valueOf(daysCount));
		
		return  allEmployees
		.stream()
		.filter(e->(e instanceof Trainee))
		.map(em -> (Trainee) em)
		.filter( practiceLengthLongerThan )
		.peek(emp -> emp.setSalary( (emp.getSalary())
						.add(emp.getSalary()
							.multiply(BigDecimal.valueOf(0.05)))))
		.collect(Collectors.toList());
	}

	
	//3 * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	public static List<Worker> seniorityLongerThanGivenMonths(List<Employee> allEmployees, int monthCount) {
		
		final BigDecimal minBonus = new BigDecimal(300);
		
		final Predicate<Worker> seniorityLongerThan = e -> e.seniorityIsLongerMonth(Long.valueOf(monthCount));
		final Predicate<Worker> minBonuS = e -> e.getBonus().compareTo(minBonus) < 0;
		final Predicate<Worker> seniorityLongerThanAndMinBonus = seniorityLongerThan.and(minBonuS);
		
		return allEmployees
		.stream()
		.filter(e->(e instanceof Worker))
		.map(em -> (Worker) em)
		.filter(seniorityLongerThanAndMinBonus)
		.peek(e -> e.setBonus(minBonus))
		.collect(Collectors.toList());
	}
	
	//4 * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
		
		final Predicate<Worker> seniorityLongerThan1Year = e ->  e.seniorityIsLongerYears(Long.valueOf(1));
		final Predicate<Worker> seniorityLongerThan3Years = e ->  e.seniorityIsLessYears(Long.valueOf(3));
		final Predicate<Worker> seniorityBetweenOneAndThreeYears = seniorityLongerThan1Year.and(seniorityLongerThan3Years);
		
		return allEmployees
		.stream()
		.filter(e->(e instanceof Worker))
		.map(em -> (Worker) em)
		.filter(seniorityBetweenOneAndThreeYears)
		.peek(e -> e.setSalary(e.getSalary()
					.add(e.getSalary()	
						.multiply(BigDecimal.valueOf(0.1)))))
		.collect(Collectors.toList());
	}
	
	//5 * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	public static List<Worker> seniorityLongerThanOtherEmployee(List<Employee> allEmployees, Employee employee) {
		
		if (employee == null || !(employee instanceof Worker)) return null;
			
		final Predicate<Worker> seniorityLongerThan = e -> e.seniorityGreaterThanOtherSeniority((Worker)employee);
		final Predicate<Worker> salaryIsLess = e -> e.salaryIsLess(employee);
		final Predicate<Worker> seniorityLongerThanAndsalaryIsLess = seniorityLongerThan.and(salaryIsLess);

		return allEmployees
		.stream()
		.filter(e->(e instanceof Worker))
		.map(em -> (Worker) em)
		.filter(seniorityLongerThanAndsalaryIsLess)
		.peek(emp -> emp.setSalary(employee.getSalary()))
		.collect(Collectors.toList());
	}
	
	//6 * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
	
		final Predicate<Worker> seniorityBetweenTwo = e -> e.seniorityIsLongerYears(Long.valueOf(2));
		final Predicate<Worker> seniorityBetweenFour = e -> e.seniorityIsLessYears(Long.valueOf(4));
		final Predicate<Worker> ageGreaterThan = e ->  e.getAge() > age;
		final Predicate<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan =  seniorityBetweenTwo.and(seniorityBetweenFour).and(ageGreaterThan);

		return allEmployees
		.stream()
		.filter(e->(e instanceof Worker))
		.map(em -> (Worker) em)
		.filter(seniorityBetweenTwoAndFourYearsAndAgeGreaterThan)
		.collect(Collectors.toList());
	}
}