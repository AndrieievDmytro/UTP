import java.util.Calendar;
import java.util.Date;

public class ExtractDateFromPesel {

    public static int calculateYear(String PESEL){
        
        int year = 0;
        int yearField = Integer.valueOf(PESEL.substring(0, 2)) ; 
        int firstNumMonth = Integer.valueOf(PESEL.substring(2, 3));

        if (firstNumMonth == 8 || firstNumMonth == 9){
            year = 1800 + yearField;
        }else if(firstNumMonth == 0 || firstNumMonth == 1){
            year = 1900 + yearField;
        }else if(firstNumMonth == 2 || firstNumMonth == 3){
            year = 2000 + yearField;
        }else if(firstNumMonth == 4 || firstNumMonth == 5){
            year = 2100 + yearField;
        }else if(firstNumMonth == 6 || firstNumMonth == 7){
            year = 2200 + yearField;
        }
        return year;
    }
    public static int calculateMonth(String PESEL){
        int month = 0;
        int monthField = Integer.valueOf(PESEL.substring(2, 4)) ; 
        int firstNumMonth = Integer.valueOf(PESEL.substring(2, 3));

        if (firstNumMonth == 8 || firstNumMonth == 9){
            month = monthField - 80;
        }else if(firstNumMonth == 0 || firstNumMonth == 1){
            month =  monthField;
        }else if(firstNumMonth == 2 || firstNumMonth == 3){
            month = monthField -20;
        }else if(firstNumMonth == 4 || firstNumMonth == 5){
            month = monthField - 40;
        }else if(firstNumMonth == 6 || firstNumMonth == 7){
            month = monthField - 60;
        }
        return month;
    }
    public static int calculateDay(String PESEL){
        return Integer.valueOf(PESEL.substring(4, 6)) ; 
    }

    public static Date birthdate(String PESEL){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calculateYear(PESEL), calculateMonth(PESEL)-1,calculateDay(PESEL));
        return calendar.getTime();
    }

}
