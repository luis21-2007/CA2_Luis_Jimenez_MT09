package mx.edu.utez.MT09_Luis_Jimenez_CA2.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
