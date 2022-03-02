package Tests;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import org.junit.Assert;
import Utils.ZIP;
import java.util.List;

public class ZipTest {
 
    private File file = new File("c:/PJAIT/test/testzip.zip");
    private File fileWithTxt = new File("c:/PJAIT/test/testzip.zip");
    

    @Before 
    public void before(){
        Assert.assertTrue(file.exists());
        // Assert.assertTrue(fileWithTxt.exists());
    }

    @Test
    public void searchByName() throws Exception{
        ZIP zip = new ZIP();
        List <String> files = zip.findByName(file, "test");
        Assert.assertEquals(1, files.size());
        Assert.assertEquals("test.txt", files.get(0));
        System.out.println(files.get(0));
    }

    @Test
    public void searchByContent() throws Exception{
        ZIP zip = new ZIP();
        List <String> files = zip.findByContent(fileWithTxt, "anyInf");
        Assert.assertEquals(0, files.size());
        // Assert.assertEquals("test.txt", files.get(0));
    }
}
