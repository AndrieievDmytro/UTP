import java.util.Random;

public enum Priority {

    High ("High priority") ,
    Medium("Medium priority"),
    Low("Low priority");

    private String _enumName;

    private Priority( String enumName ){
        _enumName = enumName;
    }

    public String getEnumName(){
        return this._enumName;
    }

    private static final Random random;

    static {
        random = new Random();
    }

    public static Priority getRandomPriority(){
        Priority [] priorities = Priority.values();
        int randomPriorityIndex = random.nextInt(priorities.length);
        return priorities[randomPriorityIndex];
    }

    public String toString(){
        return getEnumName();
    }
}
