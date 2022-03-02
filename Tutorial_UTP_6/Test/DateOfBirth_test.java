package Test;

import java.util.Date;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

import Utils.DateOfBirthGenerator;


public class DateOfBirth_test {

    @Test
    public void birthDateTest(){
        Date birthDate = DateOfBirthGenerator.generateBirthDate();
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(birthDate);
        int birthD = calendar.get(Calendar.YEAR);
        Assert.assertTrue(birthD >= 1950 || birthD <=1995);
    }

}
