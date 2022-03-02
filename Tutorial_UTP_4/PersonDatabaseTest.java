import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonDatabaseTest {
    private PersonDatabase personDatabaseTest;
    private static final File file = new File("C:/PJAIT/UTP/persons/date.txt");
    private static final DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Date date = setDate("1995-11-15");
    
    public static Date setDate(String sDate){
        try{
          return newFormat.parse(sDate);
        }catch(Throwable e){
            return null;
        }
    }

    @Before
    public void before() throws IOException{
        personDatabaseTest = new PersonDatabase(file);
    }

    @Test
    public void sortTests(){
        System.out.println("Sort by firstname");
        System.out.println(personDatabaseTest.sortedByFirstName());
        System.out.println("Sort by birthdate");
        System.out.println(personDatabaseTest.sortedByBirthdate());
        System.out.println("Sort by natural order");
        System.out.println(personDatabaseTest.sortedBySurnameFirstNameAndBirthdate());
    }
    
    @Test
    public void bornOnDay(){
        Assert.assertNotNull(personDatabaseTest);
        Assert.assertEquals(6, personDatabaseTest.mapSize());
        Assert.assertEquals(1, personDatabaseTest.bornOnDay(date).size());
        
    }
}