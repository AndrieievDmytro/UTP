import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;
import java.util.Comparator;

public final class HumanResourcesStatistics {

	public static List<PayrollEntry> payroll(List<Employee> employees) {
		if(employees == null){
			return null;
		}
		List<PayrollEntry> payment = employees
		.stream().map(HumanResourcesStatistics::payroll)
		.filter(p -> p != null).collect(Collectors.toList());
		return payment;
	}

	private static PayrollEntry payroll(Employee employee){
		if(employee == null){
			return null;
		}
		if(employee instanceof Worker){
			Worker worker = (Worker) employee;
			return new PayrollEntry(worker); 
		}
		return new PayrollEntry((Trainee)employee);
	}


	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		if(manager == null){
			return null;
		}
		return manager.sub().stream().map( emp-> payroll(emp)).collect(Collectors.toList());
	}

	public static BigDecimal bonusTotal(List<Employee> employees) {
		if(employees == null){
			return null;
		}
		// BigDecimal result = new BigDecimal(0);
		// 
		
		// 	if (employ instanceof Worker){
		// 		result = result.add(((Worker)employ).getBonus());
		BigDecimal result = employees
				  .stream()
				  .filter(emp-> (emp instanceof Worker) )
				  .map( e-> ((Worker)e).getBonus())
				  .reduce(new BigDecimal(0), (a, b) -> a.add(b));
		return result;
	}

	public static Employee findLongestSeniority( List<Employee> employees){
		if(employees == null){
			return null;
		}
		return employees
			  .stream()
			  .filter(e-> (e instanceof Worker))
			  .map(emp-> (Worker)emp)
			  .max(Comparator.comparingLong(Worker::getEmpLength))
			  .orElseThrow(NoSuchElementException::new);
	}

	public static BigDecimal findTheBiggestSalaryWithBonus(List <Employee> employee){
		if(employee == null){
			return null;
		}
		return employee
			  .stream()
			  .filter(e-> (e instanceof Worker))
			  .map(e -> new PayrollEntry((Worker)e).getSalaryBonus())
			  .max(Comparator.naturalOrder())
			  .orElseThrow(NoSuchElementException::new);
	}

	public static BigDecimal findTheBiggestSalaryWithoutBonus(List <Employee> employee){
		if(employee == null){
			return null;
		}
		return employee
			  .stream()
			  .map(e -> e.getSalary())
			  .max(Comparator.naturalOrder())
			  .orElseThrow(NoSuchElementException::new);
	}

	public static List <Employee> findEmployeeBySurname(Manager manager){
		if(manager == null){
			return null;
		}
		return manager.sub().stream().filter(emp -> emp.getSurName().startsWith("A")).collect(Collectors.toList());
	}
	
	public static List <PayrollEntry> moreThanThousand (List<Employee> employees){
		if(employees == null){
			return null;
		}
		 List<PayrollEntry> paiedtMoreThan1000 = employees.stream().map(HumanResourcesStatistics::payroll)
		.filter(p -> (p.getSalaryBonus().compareTo(  new BigDecimal(1000) ) > 0 )).collect(Collectors.toList());
		return paiedtMoreThan1000;
	}
}
