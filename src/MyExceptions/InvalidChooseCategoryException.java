package MyExceptions;

public class InvalidChooseCategoryException extends RuntimeException {
    public InvalidChooseCategoryException(String message) {
        super(message);
    }
}