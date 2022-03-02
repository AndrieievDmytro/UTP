package Objects;

import java.text.Collator;
import java.util.Date;

import ObjectsExtensions.Persons;
import Utils.Nationality;


public abstract class Person implements Comparable<Person>{
    
    private String _pesel;
    private String _surName;
    private String _firstName;
    private Date _birthDate;
    private Nationality _nationality;

    private static Collator collator_pl = Nationality.Polish.collator();
    
    protected Person(String pesel,String surName,String firstName, Date birthDate, Nationality nationality){
        _pesel = pesel;
        _surName = surName;
        _firstName = firstName;
        _birthDate = birthDate;
        _nationality = nationality;
        Persons.add(this);
    }

    public String getPesel(){
        return this._pesel;
    }

    public String getSurName(){
        return this._surName;
    }

    public String getFirstName(){
        return this._firstName;
    }

    public Date getBirthDate(){
        return this._birthDate;
    }

    public Nationality getNationality(){
        return this._nationality;
    }

    @Override
    public final int compareTo(Person p){
        int result = collator_pl.compare(_surName, p._surName);
        if (result != 0){
            return result;
        }
        return collator_pl.compare(_firstName, p._firstName);
    }

    @Override
    public int hashCode(){

        final int prime = 31;
        int result = 1;

        result = prime + (( _pesel == null ) ? 0 : _pesel.hashCode()); 
        result = prime + (( _surName == null ) ? 0 : _surName.hashCode()); 
        result = prime + (( _firstName == null ) ? 0 : _firstName.hashCode()); 
        result = prime + (( _birthDate == null ) ? 0 : _birthDate.hashCode());
        result = prime + (( _nationality == null ) ? 0 : _nationality.hashCode()); 

        return result;
    }

    @Override

    public boolean equals(Object object){

        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Person other = (Person) object;
        
        if ( _pesel == null ) {
            if ( other._pesel != null )
                return false;
        } else if ( !_pesel.equals( other._pesel ) ) 
            return false;
        
        if ( _surName == null ) {   
            if ( other._surName != null )
                return false; 
        } else if ( !_surName.equals(other._pesel ) )    
            return false;
            
        if ( _firstName == null ) {
            if ( other._firstName != null )
                return false; 
        } else if ( !_firstName.equals(other._firstName) )
            return false;  
        
        if  ( _birthDate == null ) {
            if ( other._birthDate != null )
                return false;
        }else if ( !_birthDate.equals(other._birthDate) )
            return false;

        if ( _nationality == null ) {
            if (other._nationality != null)
                return false;
        }else if ( !_nationality.equals(other._nationality) )
            return false;

        return true;
    }
}



