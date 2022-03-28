package exception;

public class SelectorNotFoundException extends RuntimeException {
    public SelectorNotFoundException(Throwable err) {
        super(err);
    }

    public SelectorNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public SelectorNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
