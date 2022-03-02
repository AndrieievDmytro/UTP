
public abstract class Message implements Comparable <Message> {

    private final int _messageId;
    private final Long _prioityByTime;
    private final Priority _messagePriority;
    private final Requestor _requestor;
    private static int thisID;

    protected Message( Priority messagePriority, Requestor requestor) {
        _messageId =  getThisMessageId();   
        _messagePriority = messagePriority; 
        _requestor = requestor;
        _prioityByTime = System.currentTimeMillis();
    }

    protected Message( Message message) {
        _messageId =  getThisMessageId();   
        _messagePriority = message._messagePriority; 
        _requestor = message._requestor;
        _prioityByTime = System.currentTimeMillis();
    }

    private synchronized static int getThisMessageId(){
        return thisID++;
    }

    public final Priority getPriority(){
        return this._messagePriority;
    }

    public final Requestor getRequestor(){
        return this._requestor;
    }
   
    public final Long getPriorityByTime(){
        return this._prioityByTime;
    }

    public final int getMessageId(){
        return this._messageId;
    }

    @Override
     public int compareTo(Message another){

        if (another == null){
            return -1;
        }

        int compareByPriority = getPriority().compareTo(another.getPriority());
        int compareBySetTime =  getPriorityByTime().compareTo(another.getPriorityByTime()); 

        if (compareByPriority != 0){
            return compareByPriority;
        }
        if (compareBySetTime != 0){
            return compareBySetTime;
        }
        return _messageId - another._messageId;
     }

     @Override
     public String toString(){
         return getClass().getName() +" Message id:"+ getMessageId() + " "; 
     }
}
