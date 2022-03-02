package Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateOfBirthGenerator {
    
    private static Random random = new Random(); 
    private static Date currDate = new Date();

    private static int yearFrom;
    
    private static int yearBack = 70;
    private static int yearInFuture = 45;
    
    private static int monthFrom = 1;
    private static int monthTo = 12;

    private static int daysFrom = 1;
    private static int daysTo = 28;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currDate);
        int currYear = calendar.get(Calendar.YEAR);
        yearFrom = currYear - yearBack;
    }

    public static Date generateBirthDate(){
        int year = yearFrom + random.nextInt(yearInFuture);
        int month = monthFrom + random.nextInt(monthTo);
        int day = daysFrom + random.nextInt(daysTo);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

}
