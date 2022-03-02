import java.util.Random;

public class Request extends Message {

    private static final  Random _randomInt; 
    private final String _request;
    private final int _request_num;


    static{
    _randomInt = new Random();
    }


    public Request(Requestor requestor){
        super( Priority.getRandomPriority(), requestor );
        _request = "Some Task ";
        _request_num = _randomInt.nextInt(10000);
    }

    public String getRequest(){
        return this._request;
    }

    public int getRequestNum(){
        return this._request_num;
    }

    @Override
    public String toString(){
        return _request+" #"+_request_num;
    }

}
