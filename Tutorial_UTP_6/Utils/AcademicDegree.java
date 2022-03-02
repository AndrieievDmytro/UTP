package Utils;

import java.util.Random;

public enum AcademicDegree {

    BSc("Bachelor degree"),
    Msc("Master degree"),
    PhD("Doctor of Philosophy");

    private String _enumName;

    private AcademicDegree( String enumName ){
        _enumName = enumName;
    }

    public String getEnumName(){
        return this._enumName;
    }

    private static Random random = new Random();


    public static AcademicDegree getRandomDegree(){
        AcademicDegree [] degree = AcademicDegree.values();
        int randomDegreeIndex = random.nextInt(degree.length);
        return degree[randomDegreeIndex];
    }

    public String toString(){
        return getEnumName();
    }
}
