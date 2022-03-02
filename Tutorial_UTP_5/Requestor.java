
public class Requestor implements Runnable{
    
    private MessageQueue _response;
    private MessageQueue _request;
    private static int sleep = 0; 
    
    private int id;
    private static int requestorCounter;

    public Requestor(MessageQueue resp, MessageQueue req){
        _response = resp;
        _request = req;
        id = getRequestorCounter();
    } 

    private static int getRequestorCounter(){
        return requestorCounter++;
    }

    public int getID(){
        return this.id;
    }

    @Override
    public void run(){
        System.out.println("Request" + " " + id  + " is proccesing");
        while(true){
            _request.addRequestInTheQueue( new Request( this ) );
            boolean isProccesed = false;
            while (!isProccesed){
                Response r = _response.removeResponse(this);
                if (r != null){
                    isProccesed  = true;
                    System.out.println("Request " + id + " proceed " + r);
                }   
                sleep();
            }
        }
    }

    private void sleep(){
        try{
            Thread.sleep(sleep);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
