package ObjectsExtensions;

import java.util.Set;
import Objects.Teacher;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Teachers {
    
    private static Set <Teacher> _instance;
    private static List <Teacher> _teachersList;
    
    static {
        _instance = new HashSet<>();
        _teachersList =new ArrayList<>();
    }

    public static void add(Teacher t){
        _instance.add(t);
        _teachersList.add(t);
        _teachersList.sort(Comparator.naturalOrder());
    }

    public static Set <Teacher> getInstance(){
        return _instance;
    }

}
