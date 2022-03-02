import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person {

	// (assignment 02)
	// attributes:
	// * salary
	// * manager (empty if at top of hierarchy)

	// (assignment 03)
	// methods:
	// * salary is greater than given amount of money
	// * salary is less than given amount of money
	// * compare salary with other employee salary	
	
	private BigDecimal _salary;
	private Employee _manager;
	
	protected Employee ( String firstName , String surName , Date birthDate, BigDecimal salary,Employee manager ){
		super(firstName,surName,birthDate);
		this._salary = salary;
		this._manager = manager;
		if ( _manager != null ) _manager.addEmploy(this);
	}
	
	public void addEmploy(Employee employee){}

	public BigDecimal getSalary(){
		return this._salary;
	}

	public BigDecimal setSalary(BigDecimal salary){
		return _salary = salary;
	}
	
	public Employee getManager(){
		return this._manager;
	}

	public boolean salaryIsGreater(Employee e){
		return compareSalary(e) > 0;
	}

	public boolean salaryIsLess(Employee e){
		return compareSalary(e) < 0;
	}

	public int compareSalary(Employee e){
		return getSalary().compareTo(e.getSalary());
	}

	@Override
	public String toString(){
		String r = super.toString();
		String mp = _manager == null ? "<noman>" : _manager.getFirstName() + " " + _manager.getSurName();
		return r + ": " + this._salary + ", manager: " + mp;
	}
}