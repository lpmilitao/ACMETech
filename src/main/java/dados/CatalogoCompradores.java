package dados;

import entidades.Comprador;

import java.util.ArrayList;
import java.util.List;

public class CatalogoCompradores {
    private List<Comprador> compradores;

    public CatalogoCompradores() {
        this.compradores = new ArrayList<>();
    }

    public List<Comprador> getCompradores() {
        return this.compradores;
    }

    public void cadastrarComprador(String codRaw, String nome, String pais, String email){
        // implementação basica só para testar a leitura de arquivos
        Comprador novoComprador = new Comprador(Long.parseLong(codRaw), nome, pais, email);
        this.compradores.add(novoComprador);
    }
}
