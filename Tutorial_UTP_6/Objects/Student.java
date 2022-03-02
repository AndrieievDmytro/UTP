package Objects;

import java.util.Date;

import ObjectsExtensions.Students;
import Utils.Nationality;

public class Student extends Person{
    
    private String  _studentBookNumb;

    public Student (String pesel, String surName, String firstName, Date birthDate, Nationality nationality, String studentBookNumb){
        super(pesel, surName, firstName, birthDate, nationality);
        _studentBookNumb = studentBookNumb;
        Students.add(this);
    }

    public String getStudentNumb(){
        return _studentBookNumb;
    }

    @Override
    public int hashCode(){

        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (( _studentBookNumb == null ) ? 0 : _studentBookNumb.hashCode()); 

        return result;
    }

    @Override
    public boolean equals(Object object){

        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;
        Student other = (Student) object;
        
        if ( _studentBookNumb == null ) {
            if ( other._studentBookNumb != null )
                return false;
        } else if ( !_studentBookNumb.equals( other._studentBookNumb ) ) 
            return false;
        
        return true;
    }

}
