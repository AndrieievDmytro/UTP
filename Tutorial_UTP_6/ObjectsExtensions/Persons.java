package ObjectsExtensions;

import java.util.Set;
import Objects.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Persons {
 
    private static Set <Person> _instance;
    private static List <Person> _personList;
    
    static {
        _instance = new HashSet<>();
        _personList =new ArrayList<>();
    }

    public static void add(Person p){
        _instance.add(p);
        _personList.add(p);
        _personList.sort(Comparator.naturalOrder());
    }

    public static Set <Person> getInstance(){
        return _instance;
    }

}
