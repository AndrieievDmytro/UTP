package Task;

import java.util.Random;

public class Result {

    private static Random _random = new Random();
    private int _result;

    private Result(int result){
        _result = result;
    }

    public int getResult(){
        return _result;
    }

    public static Result generateRandomResult(){
        int result = _random.nextInt(101);
        return new Result(result);
    }

    public String toString(){
        return "Result " + _result;
    }

}
