
import java.io.File;
import java.util.List;
import Utils.FilesSearch;
import Utils.ZIP;

public class Main {
    public static void main(String[] args)throws Exception {
        FilesSearch fileSearch = new FilesSearch();
        ZIP zip = new ZIP();
        File file = new File("c:/PJAIT/test/testzip.zip");
        System.out.println(fileSearch.findByName(file, "test"));
        System.out.println( (int)(fileSearch.findByName(file, "test").size()));
        System.out.println(fileSearch.findByContent(file, "Du wirst dieses Project ubergeben"));
        System.out.println( (int)(fileSearch.findByContent(file, "Du wirst dieses Project ubergeben").size()));
        List<File> files = fileSearch.findByContent(file, "Du wirst dieses Project ubergeben");
        System.out.println(files.get(0).getName());
        System.out.println(zip.findByContent(file, "Hello"));
    }    
}
