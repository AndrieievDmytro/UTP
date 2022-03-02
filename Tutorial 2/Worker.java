import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Worker extends Employee {
	/*
	attributes
	* employment date
	* bonus
	*/	
	private BigDecimal _bonus;
	private Date	   empDate;
	
	public Worker( String firstName , String surName , Date birthDate, BigDecimal salary, BigDecimal bonus, Date employmentDate, Employee manager) {
		super( firstName, surName, birthDate, salary, manager);
		this._bonus = bonus;
		this.empDate = employmentDate;
	}

	public BigDecimal getBonus(){
		return this._bonus;
	}

	public Date getEmpDate(){
		return this.empDate;
	}

	public long getEmpLength(){	
		Date currentDate  = new Date();
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(currentDate);
        calendar.setTime( empDate );
		// empDate = calendar.getTime();
		Date eDate = calendar.getTime();
        long diffInMillies = Math.abs(currentDate.getTime() - eDate.getTime());
        long empLength = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return empLength;
	}
}