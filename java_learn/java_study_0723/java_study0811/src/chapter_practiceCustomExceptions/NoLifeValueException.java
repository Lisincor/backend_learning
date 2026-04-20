package chapter_practiceCustomExceptions;

public class NoLifeValueException extends RuntimeException{
    public NoLifeValueException() {
    }

    public NoLifeValueException(String message) {
        super(message);
    }
}
