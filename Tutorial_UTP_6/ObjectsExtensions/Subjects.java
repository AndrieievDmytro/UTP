package ObjectsExtensions;

import java.util.Set;
import Objects.Subject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Subjects {

    private static Set <Subject> _instance;
    private static List <Subject> _subjectList;
    
    static {
        _instance = new HashSet<>();
        _subjectList =new ArrayList<>();
    }

    public static void add(Subject s){
        _instance.add(s);
        _subjectList.add(s);
    }

    public static Set <Subject> getInstance(){
        return _instance;
    }

}
