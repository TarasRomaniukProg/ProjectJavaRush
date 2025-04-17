package util.ex;

public class InvalidKeyException extends RuntimeException {
    public InvalidKeyException() {
        super("Key is invalid!");
    }
}
