import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class InputParserTest{
  
    private static final File file = new File("C:/PJAIT/UTP/persons/date.txt");

    public List<Person> parser()throws IOException {
        try{
            return InputParser.parser(file);
        }catch(Throwable e){
            return null;
        }
    }

    @Before
    public void before()throws IOException{
        List<Person> p = parser();
        System.out.println(p);
    }

    @Test
    public void test()throws IOException{
        Assert.assertNotNull(parser());
        Assert.assertEquals(6, parser().size());
    }
}