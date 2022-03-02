import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int serviceCounter = 10000;
    private static int requestorCounter = 10000;

    public static void main(String[] args) {
        
        MessageQueue requestQueue = new MessageQueue(); 
        MessageQueue responceQueue  = new MessageQueue();

        List<Service> services = services(requestQueue, responceQueue,serviceCounter );   
        List<Requestor> requestors = requestors(requestQueue, responceQueue, requestorCounter);
    

        services.stream().forEach( e -> new Thread(e).start());
        requestors.stream().forEach( e -> new Thread(e).start());

    }

    private  static List <Service> services(MessageQueue requestQueue,MessageQueue respoceQueue, int serviceCounter ){
        List<Service> l = new ArrayList<>();
        for ( int i = 0; i < serviceCounter; i++ ){
                l.add(new Service(requestQueue, respoceQueue));
        }
        return l;
    }

    private  static List <Requestor> requestors(MessageQueue requestQueue,MessageQueue respoceQueue, int serviceCounter ){
        List<Requestor> l = new ArrayList<>();
        for ( int i = 0; i < serviceCounter; i++ ){
                l.add(new Requestor(requestQueue, respoceQueue));
        }
        return l;
    }
}

