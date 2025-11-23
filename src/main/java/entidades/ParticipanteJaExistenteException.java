package entidades;

public class ParticipanteJaExistenteException extends RuntimeException {
    public ParticipanteJaExistenteException(String message) {
        super(message);
    }
}
