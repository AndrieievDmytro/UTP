package Task;

public enum Status {
    PENDING,
    RUNNING,
    ACCOMPLISHED,
    FAILED;

    public String getName(){
        return name();
    }
}
