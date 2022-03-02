package Tests;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import org.junit.Assert;
import Utils.FilesSearch;
import java.util.List;

public class FileTest {

    private File file = new File("c:/PJAIT/test");

    @Before
    public void before(){
        Assert.assertTrue(file.exists());
    }


    @Test
    public void searchByName() throws Exception{
        FilesSearch searchFile = new FilesSearch();
        List<File> files = searchFile.findByName(file, "test");
        Assert.assertEquals(4, files.size());
        Assert.assertEquals("test2.txt", files.get(1).getName());
    }

    @Test
    public void searchByCOntent() throws Exception{
        FilesSearch searchFile = new FilesSearch();
        List<File> files = searchFile.findByContent(file, "Du wirst dieses Project ubergeben");
        Assert.assertEquals(1, files.size());
        Assert.assertEquals("test1.txt", files.get(0).getName());
    }
}
