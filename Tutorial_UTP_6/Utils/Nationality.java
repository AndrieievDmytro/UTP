package Utils;

import java.text.Collator;
import java.util.Locale;
import java.util.Random;

public enum Nationality {

    Polish( new Locale("pl")),
    Ukrainian( new Locale("ua")),
    Belarussian( new Locale("by")),
    Slovak( new Locale("sk")),
    Lithuanian( new Locale("lt")),
    Latvian( new Locale("lv")),
    British( new Locale("en_UK")),
    Indian( new Locale("en_IN")),
    Chinese( new Locale("cn")),
    Vietnamese( new Locale("vn"));

    private Collator _collator;
    private Locale _locale;
    private static Random _random = new Random();

    private Nationality(Locale locale){
        _locale = locale;
        _collator = Collator.getInstance(_locale);
    }

    public Locale locale(){
        return _locale;
    }

    public Collator collator(){
        return _collator;
    }

    public static Nationality randomNationality(){
        Nationality[] arr = values();
        int index = _random.nextInt(arr.length);
        return arr[index];
    }
}
