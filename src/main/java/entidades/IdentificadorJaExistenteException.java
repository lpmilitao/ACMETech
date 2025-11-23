package entidades;

public class IdentificadorJaExistenteException extends RuntimeException {
    public IdentificadorJaExistenteException(String message) {
        super(message);
    }
}
