package entidades;

public class FornecedorJaExistenteException extends RuntimeException {
    public FornecedorJaExistenteException(String message) {
        super(message);
    }
}
