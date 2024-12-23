package TheoryDay.Task2.exceptions;

public class InvalidData extends RuntimeException{
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public InvalidData() {
    }

    public InvalidData(String message) {
        super(message);
    }
}
