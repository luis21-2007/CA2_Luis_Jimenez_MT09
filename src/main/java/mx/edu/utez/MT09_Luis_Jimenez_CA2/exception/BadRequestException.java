package mx.edu.utez.MT09_Luis_Jimenez_CA2.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
