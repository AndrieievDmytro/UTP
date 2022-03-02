
import java.math.BigDecimal;

public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;
	
	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus){
		_employee = employee;
		_salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
	}

	public PayrollEntry (Worker paymentWorker){
		_employee = paymentWorker;
		BigDecimal salary = paymentWorker.getSalary();
		BigDecimal bonus = paymentWorker.getBonus(); 
		_salaryPlusBonus = salary.add(bonus);
	}

	public PayrollEntry (Trainee trainee){
		_employee = trainee;
		BigDecimal salary = trainee.getSalary();
		_salaryPlusBonus = salary;
	}

	public Employee getEmployee(){
		return _employee;
	}

	public BigDecimal getSalaryBonus(){
		return _salaryPlusBonus;
	}

	@Override
	public String toString(){
		return getSalaryBonus().toString();
	}
}
