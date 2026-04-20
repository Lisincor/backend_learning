package ExceptionPractice02;

public class BelowZeroException extends  Exception{
    public BelowZeroException() {
    }

    public BelowZeroException(String message) {
        super(message);
    }
}
