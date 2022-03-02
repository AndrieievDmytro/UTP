package ObjectsExtensions;

import java.util.Set;
import Objects.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Students {
    
    private static Set <Student> _instance;
    private static List <Student> _studentList;
    
    static {
        _instance = new HashSet<>();
        _studentList =new ArrayList<>();
    }

    public static void add(Student s){
        _instance.add(s);
        _studentList.add(s);
        _studentList.sort(Comparator.naturalOrder());
    }

    public static Set <Student> getInstance(){
        return _instance;
    }

}
