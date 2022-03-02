package Objects;

import java.util.List;

import ObjectsExtensions.StudentGroups;


public class StudentGroup {
    
    private String _name;
    private List<Student> _students;

    public StudentGroup (String name, List <Student> students ){
        _name = name;
        _students = students;   
        StudentGroups.add(this);
    }

    public String getName(){
        return _name;
    }

    public List <Student> getStudentsList(){
        return _students;
    }

    @Override
    public int hashCode(){

        final int prime = 31;
        int result = 1;
        result = prime * result + (( _name == null ) ? 0 : _name.hashCode()); 
        result = prime * result + (( _students == null ) ? 0 : _students.hashCode()); 
        
        return result;
    }

    @Override
    public boolean equals(Object object){

        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        StudentGroup other = (StudentGroup) object;
        
        if ( _name == null ) {
            if ( other._name != null )
                return false;
        } else if ( !_name.equals( other._name ) ) 
            return false;
        
        if ( _students == null ) {
            if ( other._students != null )
                return false;
        } else if ( !_students.equals( other._students ) ) 
            return false;
        
        return true;
    }
}
