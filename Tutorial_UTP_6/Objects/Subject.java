package Objects;

import java.util.List;

import ObjectsExtensions.Subjects;

public class Subject {

    private String _name;
    private Department _department;
    private Teacher _lecturer;
    private List <Student> _students;

    public Subject(String name, Department department, Teacher lecturer, List <Student> students) {
        _name = name;
        _department = department;
        _lecturer = lecturer;
        _students = students;
        Subjects.add(this);
    }

    public String getName(){
        return _name;
    }

    public Department getDepartment(){
        return _department;
    }

    public Teacher getLecturer(){
        return _lecturer;
    }

    public List <Student> getListOfStudents(){
        return _students;
    }

    @Override
    public int hashCode(){

        final int prime = 31;
        int result = 1;
        result = prime * result + (( _name == null ) ? 0 : _name.hashCode()); 
        result = prime * result + (( _department == null ) ? 0 : _department.hashCode()); 
        result = prime * result + (( _lecturer == null ) ? 0 : _lecturer.hashCode()); 
        result = prime * result + (( _students == null ) ? 0 : _students.hashCode()); 
        
        return result;
    }

    @Override
    public boolean equals(Object object){

        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Subject other = (Subject) object;
        
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
        
        if ( _lecturer == null ) {
            if ( other._lecturer != null )
                return false;
        } else if ( !_lecturer.equals( other._lecturer ) ) 
            return false;

        if ( _department == null ) {
            if ( other._department != null )
                return false;
        } else if ( !_department.equals( other._department ) ) 
            return false;

        return true;
    }

}
