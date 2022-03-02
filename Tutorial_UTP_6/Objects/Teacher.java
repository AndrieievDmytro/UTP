package Objects;

import java.util.Date;

import ObjectsExtensions.Teachers;
import Utils.*;


public class Teacher extends Person {
    
    private AcademicDegree _degree;
    private Date _hireDate;

    public Teacher (String pesel,String surName,String firstName, Date birthDate, Nationality nationality, AcademicDegree degree, Date hireDate){

        super(pesel, surName, firstName, birthDate, nationality);
        _degree = degree;
        _hireDate = hireDate;
        Teachers.add(this);
    }

    public AcademicDegree getDegree(){
        return _degree;
    }

    public Date getHireDate(){
        return _hireDate;
    }

    @Override
    public int hashCode(){

        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (( _degree == null ) ? 0 : _degree.hashCode()); 
        result = prime * result + (( _hireDate == null ) ? 0 : _hireDate.hashCode()); 
        
        return result;
    }

    @Override
    public boolean equals(Object object){

        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (getClass() != object.getClass()) return false;
        Teacher other = (Teacher) object;
        
        if ( _degree == null ) {
            if ( other._degree != null )
                return false;
        } else if ( !_degree.equals( other._degree ) ) 
            return false;
        
        if ( _hireDate == null ) {
            if ( other._hireDate != null )
                return false;
        } else if ( !_hireDate.equals( other._hireDate ) ) 
            return false;
        
        return true;
    }
}
