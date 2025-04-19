package util.ex;

public class InvalidKeyException extends RuntimeException {
    public InvalidKeyException() {
        super("Key should be grater than 0!");
    }
}
