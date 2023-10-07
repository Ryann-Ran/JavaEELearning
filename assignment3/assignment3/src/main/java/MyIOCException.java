/**
 * @author Ryann
 * @create 2023/10/7 - 10:38
 */
public class MyIOCException extends Exception{
    public enum ErrorType {FILE_NOTFOUND,CONFIG_READ_ERROR,CLASS_NOTFOUND,SET_PROP_ERROR,CREATE_OBJECT_ERROR,PROP_NOTFOUND}
    private ErrorType errorType;

    public MyIOCException(ErrorType errorType, String message){
        super(message);
        this.errorType =errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
