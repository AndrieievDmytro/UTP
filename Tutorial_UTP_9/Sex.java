public enum Sex {

    M("Male") ,
    F("Female");

    private String _enumName;

    private Sex( String enumName ){
        _enumName = enumName;
    }

    public String getEnumName(){
        return this._enumName;
    }

    public static Sex getSex(String PESEL) {
        return isOdd(PESEL) ? Sex.M : Sex.F;
    } 

    private static boolean isOdd(String PESEL){
        return Integer.valueOf(PESEL.substring(9,10)) % 2 !=  0 ? true : false;
    }

    public String toString(){
        return getEnumName();
    }
}


