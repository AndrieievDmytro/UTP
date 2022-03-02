import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Trainee extends Employee {

	/*
	attributes:
	* apprenticeship start date
	* apprenticeship length (in days)
	*/

	private Date 	 apprStartDate;

	public Trainee(String firstName , String surName , Date birthDate, BigDecimal salary,Date apprenticeshipStartDate,Employee manager ){
		super( firstName, surName, birthDate, salary, manager );
		this.apprStartDate = apprenticeshipStartDate;
	}

	public Date getApprStartDate(){
		 return this.apprStartDate;
	}

	public long getApprLength(){	
		Calendar _calendar = Calendar.getInstance();
		Date currentDate  = new Date();
        // _calendar.setTime(currentDate);
        _calendar.setTime( apprStartDate );
        Date startDate = _calendar.getTime();
        long diffInMillies = Math.abs(currentDate.getTime() - startDate.getTime());
        long lengthOfApprenticeship = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return lengthOfApprenticeship;
	}
}
		