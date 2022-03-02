import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PeselGenerator {

    private static Random random = new Random();

    public static String generatePesel(Date birhDate) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birhDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int monthField = 0;
        int yearField = year % 100;
        int fourDigits = random.nextInt(10000);
        String tenDigits;
        String PESEL;

        if(year < 1800 ) {
            throw new Exception("The year is less then 1800"); 
        }else if (year < 1900){
            monthField =  month +80;
        }else if (year < 2000){
            monthField = month; 
        }else if (year < 2100){
            monthField = month + 20;
        }else if (year < 2200){
            monthField = month + 40;
        }else if (year < 2300){
            monthField = month + 60;
        }else if (year > 2300) {
            throw new Exception("The year is more than 2300");
        }

        String firstSixDigits =  String.format("%02d%02d%02d", yearField, monthField+1, day) ;
        String secondFourDigits = String.format("%04d", fourDigits);
        tenDigits = firstSixDigits.concat(secondFourDigits);
        PESEL  = tenDigits.concat(checkSum(tenDigits));
                 
        return PESEL;
    } 

    private static String checkSum(String PESELtenDigits){
        int [] multipliers = {1 ,3 , 7 , 9 ,1 ,3 , 7 , 9 ,1 ,3};
        int checkSum = 0;

        for (int i = 0 ; i < multipliers.length ; i++){
            checkSum += multipliers[i] * (Integer.valueOf(PESELtenDigits.substring(i,i+1)));
        }
         int moduleCheckSum = checkSum % 10;
         String finalDigit = Integer.toString(moduleCheckSum == 0 ? 0 : 10 - moduleCheckSum);
        return finalDigit;
    }

    private static boolean validate(String PESEL){
        return Integer.valueOf(PESEL.substring(10, 11)) == Integer.valueOf(checkSum( PESEL.substring(0, 10)) ) ? true : false;
    }

    private  Date extractBirthDate(String PESEL){
        return ExtractDateFromPesel.birthdate(PESEL);
    }

    private static Sex extractSex(String PESEL){
        return Sex.getSex(PESEL);
    }
}
