import java.util.PriorityQueue;
import java.util.Queue;


public class MessageQueue {

    private Queue<Message> _messageQPriority;

    public MessageQueue(){
        _messageQPriority = new PriorityQueue<Message>();
    }

    public synchronized void addRequestInTheQueue(Message message){
            if ( _messageQPriority.offer(message) ){
                return;
            } else {
                throw new RuntimeException( "Can't add an element" );
            }
    }

    public synchronized Request removeRequest(){
        Request request = (Request)_messageQPriority.poll();
        return request;
    }

    public synchronized void addResponceInTheQueue(Message message){
        if ( _messageQPriority.offer(message) ){
            return;
        } else {
            throw new RuntimeException( "Can't add an element" );
        }
}

    public synchronized Response removeResponse(Requestor requestor){
        Response r = null;
        Message message = _messageQPriority.peek();
        if (message != null){
            if (message.getRequestor() == requestor){
                r = removeResponse();
            } 
        } 
        return r;
    } 

    public synchronized Response removeResponse(){
        return (Response) _messageQPriority.poll();
    }

}
