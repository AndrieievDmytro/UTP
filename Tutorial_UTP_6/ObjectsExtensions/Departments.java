package ObjectsExtensions;

import java.util.Set;
import Objects.Department;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Departments {
 
    private static Set <Department> _instance;
    private static List <Department> _depList;
    
    static {
        _instance = new HashSet<>();
        _depList =new ArrayList<>();
    }

    public static void add(Department d){
        _instance.add(d);
        _depList.add(d);
    }

    public static Set <Department> getInstance(){
        return _instance;
    }
    
}
