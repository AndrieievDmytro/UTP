package ObjectsExtensions;

import java.util.Set;
import Objects.StudentGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StudentGroups {
    private static Set <StudentGroup> _instance;
    private static List <StudentGroup> _stGrList;
    
    static {
        _instance = new HashSet<>();
        _stGrList = new ArrayList<>();
    }

    public static void add(StudentGroup s){
        _instance.add(s);
        _stGrList.add(s);
    }

    public static Set <StudentGroup> getInstance(){
        return _instance;
    }
}
