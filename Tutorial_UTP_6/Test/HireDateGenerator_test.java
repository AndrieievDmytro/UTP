package Test;

import java.util.Date;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

import Utils.HireDateGenerator;

public class HireDateGenerator_test {
    
    @Test
    public void hireDateTest(){
        Date hireDate = HireDateGenerator.generateHireDate();
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(hireDate);
        int hireD = calendar.get(Calendar.YEAR);
        Assert.assertTrue(hireD >= 1990 || hireD <=2010);
    }

}
