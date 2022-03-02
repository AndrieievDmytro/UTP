package Utils;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Scanner;

public class FilesSearch {

    public FilesSearch() {
    }
 
    public List <File> findByName(File file,String name )throws Exception {

        if( !file.exists() || !file.isDirectory() || !file.canRead() ) {return null;}

        Path path  = file.toPath();
        Predicate<Path> isFile = Files::isRegularFile;
        Predicate<Path> matchFile =  p -> compareFileNames(p, name);
        
        try{
            List <File> files = Files 
                .walk(Paths.get(path.toString()))
                .parallel()
                .filter(isFile.and(matchFile))
                .map(p -> p.toFile())
                .collect(Collectors.toList());
            return files;
        }catch (Throwable exception){
            throw new Exception("Error during search by File name occured");
        }
    }

    public boolean compareFileNames(Path path, String name){
        File file = path.toFile();
        String nameOfFile = file.getName();
        return nameOfFile.contains(name);
    }

    public List <File> findByContent(File file,String name )throws Exception {

        if( !file.exists() || !file.isDirectory() || !file.canRead() ) {return null;}

        Path path  = file.toPath();
        Predicate<Path> isFile = Files::isRegularFile;
        Predicate<Path> matchContent =   p -> { try{
                                                    return findContentInFile(p, name);
                                                }catch (Throwable exception){
                                                    return false;}
                                                };

        try{
            List <File> files = Files 
                .walk(Paths.get(path.toString()))
                .parallel()
                .filter(isFile.and(matchContent))
                .map(p -> p.toFile())
                .collect(Collectors.toList());
            return files;
        }catch (Throwable exception){
            throw new Exception("Error during search of a content in File occured");
        }
    }


    public  boolean findContentInFile(Path path, String searchString) throws Exception{
        try{
            File file = path.toFile();
            if(file.isDirectory()){
            return false;
        }
        InputStream input  = new FileInputStream(path.toFile());
        Scanner scan = new Scanner(input);
        String scaned = (scan.findWithinHorizon( searchString, (int)file.length())) ;
        scan.close();
        
        return scaned != null;
        
        }catch (Throwable exception) {
            throw new Exception("Error during search of a content in File occured");
        }
    }
}