package Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.swing.table.DefaultTableModel;
import Exception.HandleException;
import Task.TaskFuture;
import Task.Result;
import Task.Task;

public class TableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    private static int _columnCount = 3;
    private static int _rawCount = 50;
    private static String cName = "Name";
    private static String cStatus = "Status";
    private static String cResult = "Result";
    private static final int cNameIndex = 0;
    public static final int cStatusIndex = 1;
    private static final int cResultIndex = 2;

    private List<TaskFuture> _tasks;

    public  TableModel(){
        _tasks = new ArrayList<TaskFuture>(_rawCount);
    }

    @Override
    public int getColumnCount() {
        return _columnCount;
    }

    @Override
    public int getRowCount() {
        return _rawCount;
    }

    @Override
    public synchronized Object getValueAt(int row, int column)  {
        Object ob = new Object();
        try{    
        if (_tasks == null || row < 0 || row > getRowCount() || column < 0 || column > getColumnCount()) {
            return null;
        }
        TaskFuture task = null;
        if (row < _tasks.size()) {
            task = _tasks.get(row);
        }
            ob = getColumnValue(task, column);
        }catch(Throwable e){}   
        return ob;
    }
    

    private Object getColumnValue(TaskFuture futureTask, int columnIndex) throws Throwable {
        if(futureTask == null) {
            return null;
        }
        switch (columnIndex){
            case cNameIndex:
                return futureTask.getName();
            case cStatusIndex :
                return futureTask.getStatus();
            case cResultIndex :
                return futureTask.getResult();
            default:
                throw new HandleException("Code","Invalid column name");
        }
    }

    public synchronized void addTask(Task task, Future <Result> future){
        int index = insertAtIndex();  
        if( index >= 0 ) {
            TaskFuture taskFuture = new TaskFuture(task, future);
            if( index < _tasks.size()){
                _tasks.set(index, taskFuture);
            }else {
                _tasks.add(index,taskFuture);
            }
            fireTableDataChanged();
        }   
    }

    public String getColumnName(int column)  {
        String s = null;
        try{        
            switch(column){
                case cNameIndex :
                return cName;

                case cStatusIndex :
                return cStatus;

                case cResultIndex :
                return cResult;
                default: 
                    throw new Exception();
            }
        }catch(Throwable e){}
        return s;
    }   
 
    public int insertAtIndex(){
        int index = -1;
        if(_tasks.size() == _rawCount){
            for (int i = 0 ; i < _tasks.size() ; i++){
                TaskFuture futureTask = _tasks.get(i);
                if ( futureTask.isDone()){
                    index = i;
                    break;
                }
            }
        }else {
            index = _tasks.size();
        }
    return index;
    }
}

