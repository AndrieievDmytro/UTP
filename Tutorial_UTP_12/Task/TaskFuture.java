package Task;

import java.util.concurrent.Future;


public class TaskFuture {
    
    private Future<Result> _future;
    private Task _task;
    private Result _result;

    public TaskFuture( Task task, Future<Result> future ){
        _task = task;
        _future = future;
    }
    
    public String getName(){
        return _task.getName();
    }

    public Status getStatus(){
        return _task.getStatus();
    }

    public boolean isDone(){
        return _future.isDone();
    }

    public Result getResult() {
        try {
            if(_future.isDone()){
                _result = _future.get();
            }                 
        } catch (Throwable e) {}
        return _result;
    }
}
