package Exception;


public final class HandleException extends RuntimeException {

	private static final long serialVersionUID = -9136633471042484748L;

	private int code;

    public HandleException(Throwable cause){
        super( cause );
    }

    public HandleException(int code, String message){
        super(message);
        this.setCode(code);
    }

    public HandleException(int code, String message, Throwable cause){
        super(message,cause);
        this.setCode(code);
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

}