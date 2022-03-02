public class Service implements Runnable {

    private MessageQueue _response;
    private MessageQueue _request;
    private  int id;
    private static int serviceCounter;
    private static int sleep = 0; 
    
    public Service( MessageQueue resp, MessageQueue req ){
        _response = resp;
        _request = req;
        id = getRequestorCounter();
    } 

    private static int getRequestorCounter(){
        return serviceCounter++;
    }

    public int getID(){
        return this.id;
    }

    public void run(){
        System.out.println("Response" +" "+ id + " " +"is proccesing");
        while(true){
            Request r = _request.removeRequest();
            if(r != null){
                System.out.println("Responce "+ id + " " + r + " "+" is proceed" );
                _response.addResponceInTheQueue(new Response(r));
            }
            sleep();
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
