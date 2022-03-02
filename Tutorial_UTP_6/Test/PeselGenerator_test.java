package Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import Utils.DateOfBirthGenerator;
import Utils.PeselGenerator;

public class PeselGenerator_test {
    
    private static Pattern pattern = Pattern.compile("^[0-9]{11}$");

    @Test
    public void generatePeselTest()throws Exception{
        String PESEL = PeselGenerator.generatePesel(DateOfBirthGenerator.generateBirthDate());
        Matcher matcher = pattern.matcher(PESEL);
        System.out.println(PESEL);
        Assert.assertEquals(11, PESEL.length());
        Assert.assertTrue(matcher.matches());
    }
}
