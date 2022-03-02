public class Response  extends Message {

    public final String _responseResult;

    public Response (Request request){
        super(request.getPriority(), request.getRequestor());
        _responseResult = request.getRequest() + request.getRequestNum();
    }

    public String getResponceResult(){
        return this._responseResult;
    }
    @Override
    public String toString(){
        return super.toString() + " Result: " + _responseResult+ " ";
    }        
}

