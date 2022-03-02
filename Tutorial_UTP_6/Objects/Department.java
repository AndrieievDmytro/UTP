package Objects;

import java.util.List;

import ObjectsExtensions.Departments;

public class Department {
 
    private String _name;
    private List <Teacher> _teachers;

    public Department(String name, List <Teacher> teachers){
        _name = name;
        _teachers = teachers;
        Departments.add(this);
    }

    public String getName(){
        return _name;
    }

    public List <Teacher> getTeachers(){
        return _teachers;
    }
 
    @Override
    public int hashCode(){

        final int prime = 31;
        int result = 1;
        result = prime * result + (( _name == null ) ? 0 : _name.hashCode()); 
        result = prime * result + (( _teachers == null ) ? 0 : _teachers.hashCode()); 
        
        return result;
    }

    @Override
    public boolean equals(Object object){

        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Department other = (Department) object;
        
        if ( _name == null ) {
            if ( other._name != null )
                return false;
        } else if ( !_name.equals( other._name ) ) 
            return false;
        
        if ( _teachers == null ) {
            if ( other._teachers != null )
                return false;
        } else if ( !_teachers.equals( other._teachers ) ) 
            return false;
        
        return true;
    }
}
