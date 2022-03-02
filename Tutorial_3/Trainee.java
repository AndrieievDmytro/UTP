import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Trainee extends Employee {

	// (assignment 02)
	// attributes:
	// * practice start date
	// * practice length (in days)
	
	// (assignment 03)
	// * practice length is shorter than given number of days
	// * practice length is longer than given number of days
	private Date 	 _apprStartDate;

	public Trainee(String firstName , String surName , Date birthDate, BigDecimal salary,Date apprenticeshipStartDate,Employee manager ){
		super( firstName, surName, birthDate, salary, manager );
		this._apprStartDate = apprenticeshipStartDate;
	}

	public Date getApprStartDate(){
		 return this._apprStartDate;
	}

	public Long getApprLength(){	
		Calendar _calendar = Calendar.getInstance();
		Date currentDate  = new Date();
        _calendar.setTime( _apprStartDate );
        Date startDate = _calendar.getTime();
        long diffInMillies = Math.abs(currentDate.getTime() - startDate.getTime());
        long lengthOfApprenticeship = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return lengthOfApprenticeship;
	}


	public boolean practiceIsShorter(Long days){
		return getApprLength().compareTo(days) < 0;
	}
	
	public boolean practiceIsLonger(Long days){
		return getApprLength().compareTo(days) > 0;
	}
}