import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person 
{
	/*
	attributes:
	* salary (use BigDecimal type for representing currency values)
	* manager (empty if at top of hierarchy)
	*/
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
	
	public Employee getManager(){
		return this._manager;
	}

	@Override
	public String toString(){
		String r = super.toString();
		String mp = _manager == null ? "<noman>" : _manager.getFirstName() + " " + _manager.getSurName();
		return r + ": " + this._salary + ", manager: " + mp;
	}
}