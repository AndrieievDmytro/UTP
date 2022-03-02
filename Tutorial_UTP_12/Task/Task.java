package Task;

import java.util.concurrent.Callable;

public class Task implements Callable<Result> {
    
    private String _name;
    private Status _status;
    private Result _result;
    private static int id;
    

    public Status getStatus(){
        return _status;
    }

    public String getName(){
        return _name;
    }

    public Task (){
        this("Task #" + id++);
    }

    private Task (String name){
        _name = name;
        _status = Status.PENDING;
        _result = null;
    }
    
    public Result call() {
        try {
            _status = Status.RUNNING;
            Thread.sleep(1000);
            task();

        } catch (Throwable e) {
            _status = Status.FAILED;
        }
        return _result;
    }

    public void task(){
        boolean taskFailed = Result.generateRandomResult().getResult() < 50;
        if ( !taskFailed ){
            _result = Result.generateRandomResult();
            _status = Status.ACCOMPLISHED;
        }else{
            _status = Status.FAILED;
        }
    }
}
