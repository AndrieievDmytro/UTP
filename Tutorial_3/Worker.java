import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Worker extends Employee {

	// (assignment 02)
	// attributes:
	// * employment date
	// * bonus
	
	// (assignment 03)
	// attributes:
	// * has bonus
	//
	// methods:
	// * seniority is longer than given number of years (seniority - sta�)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money
	
	private BigDecimal _bonus;
	private Date	   _empDate;
	private boolean	   _hasBonus;	
	
	public Worker( String firstName , String surName , Date birthDate, BigDecimal salary, BigDecimal bonus, Date employmentDate, Employee manager) {
		super( firstName, surName, birthDate, salary, manager);
		this._bonus = bonus;
		this._hasBonus = this._bonus.intValue() > 0;
		this._empDate = employmentDate;
	}

	public BigDecimal getBonus(){
		return this._bonus;
	}
	public BigDecimal setBonus(BigDecimal bonus){
		return this._bonus = bonus;
	}

	public Date getEmpDate(){
		return this._empDate;
	}

	public boolean hasBonus(){
		return this._hasBonus;
	}

	public Long getEmpLength(){	
		Date currentDate  = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( this._empDate );
		Date eDate = calendar.getTime();
        long diffInMillies = Math.abs(currentDate.getTime() - eDate.getTime());
        Long empLength = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return empLength;
	}

	public boolean seniorityIsLongerYears(Long years){
		// Long seniorityInYears = (getEmpLength()/365);  
		// return seniorityInYears.compareTo(years) > 0; 
		return getEmpLength().compareTo(years * 365) > 0;
	}

	public boolean seniorityIsLessYears(Long years){
		// Long seniorityInYears = (getEmpLength()/365);  
		// return seniorityInYears.compareTo(years) < 0; 
		// Long additionalDays = years / 4;
		return getEmpLength().compareTo(years * 365 + years / 4) < 0;
	}

	public boolean seniorityIsLongerMonth(Long month){
		// Long seniorityInYears = (getEmpLength()/365);
		Long seniorityInMonth = (getEmpLength()*12/365);  
		return seniorityInMonth.compareTo(month) > 0; 
		// days
		// days - monthsArr[i]
		// i++
		// if i > 12 i = 1 y = y + 1
		// if 0 == y mod 4 days -= 1

		// vY = seniorityInYears / 4

		// switch
		// case 31 : 1
		// case 59 + (y mod 4 == 0 ? 1 : 0) : 2
		// case 90 + (y mod 4 == 0 ? 1 : 0) : 3
	}

	public boolean seniorityGreaterThanOtherSeniority(Worker w){
		return getEmpLength().compareTo(w.getEmpLength()) > 0;
	}

	public boolean hasBonusGreaterThen(BigDecimal money){
		return getBonus().compareTo(money) > 0;
	}
}