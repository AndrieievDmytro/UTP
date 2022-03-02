package Utils;

import java.util.Date;
import java.util.Random;
import java.util.Calendar;

public class Test {

    private static Random random = new Random();
    public static void main(String[] args) throws Exception {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1998,01,11);
        date = calendar.getTime();
        System.out.println(generatePesel(date)); 
        // System.out.println(date);
        for (int i  = 0 ; i < 6; i++ ){
            System.out.println(generateStNu());
        }
    }

    public static String generatePesel(Date birhDate) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birhDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // int [] iBirthdate = {year, month, day};
        int monthField = 0;
        int yearField = year % 100;
        int fourDigits = random.nextInt(10000);
        int checkSum = 0;
        int [] multipliers = {1 ,3 , 7 , 9 ,1 ,3 , 7 , 9 ,1 ,3};

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
        String tenDigits = firstSixDigits + secondFourDigits;

        for (int i = 0 ; i < multipliers.length ; i++){
            checkSum += multipliers[i] * tenDigits.charAt(i);
        }
         int moduleCheckSum = checkSum % 10;
         String finalDigit = Integer.toString(moduleCheckSum == 0 ? 0 : 10 - moduleCheckSum);  
        return tenDigits+finalDigit;
    } 
    public static String generateStNu() {
        Random random = new Random();
        String studentNumber = Integer.toString(random.nextInt(100000));
        return studentNumber;
    } 
}
