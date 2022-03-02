package Utils;

import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Scanner;

public class ZIP {
    
    public ZIP() {
    }

    public List <String> findByName(File file,String name)throws Exception {
        if (!file.isFile()) {return null;}

        try{    
            ZipFile zip = new ZipFile(file);
            List <String> files = zip 
                .stream()
                .filter((p -> compareFileNames(p, name)))
                .map(ZipEntry::getName)
                .collect(Collectors.toList());
            zip.close();
            return files;
        }catch (Throwable exception){
            throw new Exception("Error during search ZIP by File name occured");
        }
    }

    public boolean compareFileNames(ZipEntry entry, String name){
        return entry.getName().contains(name);
    }

    public List <String> findByContent(File file,String name )throws Exception {

        if( !file.isFile()) {return null;}

        ZipFile zip = new ZipFile(file);     
        Predicate<ZipEntry> matchContent =   p -> { try{
                                                    return findContentInFile(p,zip, name);
                                                }catch (Throwable exception){
                                                    return false;}
                                                };
        try{
            List <String> files = zip 
                .stream()
                .filter(matchContent)
                .map(ZipEntry::getName)
                .collect(Collectors.toList());
            return files;
        }catch (Throwable exception){
            throw new Exception("Error during search of a content in File occured");
        }
    }


    public  boolean findContentInFile(ZipEntry entry, ZipFile file, String searchString) throws Exception{
        try{
            InputStream input  = file.getInputStream(entry);
            Scanner scan = new Scanner(input);
            String scaned = (scan.findWithinHorizon( searchString, (int)file.size()));
            scan.close();
            return scaned != null;
        }catch (Throwable exception) {
            throw new Exception("Error during search of a content in ZIP occured");
        }
    }

}
