package dados;

import entidades.Comprador;
import entidades.Fornecedor;
import entidades.IdentificadorJaExistenteException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CatalogoCompradores {
    private List<Comprador> compradores;

    public CatalogoCompradores() {
        this.compradores = new ArrayList<>();
    }

    public List<Comprador> getCompradores() {
        return this.compradores;
    }

    public void cadastrarComprador(String codRaw, String nome, String pais, String email) {

        if (codRaw.trim().isBlank() || pais.trim().isBlank() || email.trim().isBlank() || nome.trim().isBlank())
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");

        long cod = Long.parseLong(codRaw);
        Comprador compradorExistente = getCompradorByCod(cod);

        if (compradorExistente != null)
            throw new IdentificadorJaExistenteException("Já existe um comprador com esse código.");

        validaEmail(email);

        Comprador novoComprador = new Comprador(cod, nome, pais, email);
        this.compradores.add(novoComprador);
        sortCompradores();
    }

    public Comprador getCompradorByCod(long cod) {
        return this.compradores.stream()
                .filter(comprador -> comprador.getCod() == cod)
                .findFirst()
                .orElse(null);
    }

    private void validaEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";

        if (email.isBlank() || !email.matches(regex)) {
            throw new IllegalArgumentException("Email invalido");
        }
    }

    private void sortCompradores() {
        this.compradores.sort(Comparator.comparingLong(Comprador::getCod));
    }
}
