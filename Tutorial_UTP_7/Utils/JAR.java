package Utils;

import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.util.function.Predicate;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.jar.JarEntry;

import java.util.Scanner;

public class JAR {
    
    public JAR(){};



    public List <String > findByName(File file,String name,Predicate<? super JarEntry> predicate)throws Exception {
        if( !file.isFile() ) {return null;}

        JarFile jar = null;
        try{
            jar = new JarFile(file);
            List <String> files = jar 
                .stream()
                .filter((predicate))
                .map(JarEntry::getName)
                .collect(Collectors.toList());
            jar.close();
            return files;
        }catch (Throwable exception){
            throw new Exception("Error during search by File name occured");
        }
    }

    public boolean compareFileNames(JarEntry entry, String name){
        return entry.getName().contains(name);
    }

    public List <String> findByContent(File file,String name )throws Exception {

        if( !file.isDirectory() ) {return null;}

        JarFile zip = new JarFile(file);     
        Predicate<JarEntry> matchContent =   p -> { try{
                                                    return findContentInFile(p,zip, name);
                                                }catch (Throwable exception){
                                                    return false;}
                                                };
        try{
  
            List <String> files = zip 
                .stream()
                .filter(matchContent)
                .map(JarEntry::getName)
                .collect(Collectors.toList());
            return files;
        }catch (Throwable exception){
            throw new Exception("Error during search of a content in File occured");
        }
    }


    public  boolean findContentInFile(JarEntry entry, JarFile file, String searchString) throws Exception{
        try{
            InputStream input  = file.getInputStream(entry);
            Scanner scan = new Scanner(input);
            String scaned = (scan.findWithinHorizon( searchString, (int)file.size())) ;
            scan.close();
            return scaned != null;
        }catch (Throwable exception) {
            throw new Exception("Error during search of a content in File occured");
        }
    }
}