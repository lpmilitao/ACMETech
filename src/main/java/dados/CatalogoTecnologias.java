package dados;

import entidades.Fornecedor;
import entidades.IdentificadorJaExistenteException;
import entidades.Tecnologia;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CatalogoTecnologias {
    private List<Tecnologia> tecnologias;

    public CatalogoTecnologias() {
        this.tecnologias = new ArrayList<>();
    }

    public List<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void cadastrarTecnologia(String idRaw, String modelo, String descricao, String valorBaseRaw, String pesoRaw,
                                    String temperaturaRaw, Fornecedor fornecedor) {
        long id;
        double valorBase, peso, temperatura;

        if (idRaw.isBlank() || modelo.isBlank() || descricao.isBlank()|| valorBaseRaw.isBlank()
                || pesoRaw.isBlank()  || temperaturaRaw.isBlank()) throw new IllegalArgumentException("Todos os campos devem ser preenchidos");

        try {
            id = Long.parseLong(idRaw);
        } catch (NumberFormatException ex) {
            //pegando a exceção e jogando novamente para personalizar a mensagem de erro
            throw new NumberFormatException("O ID da tecnologia deve ser um número inteiro");
        }

        try {
            valorBase = Double.parseDouble(valorBaseRaw);
            peso = Double.parseDouble(pesoRaw);
            temperatura = Double.parseDouble(temperaturaRaw);

        } catch (NumberFormatException ex) {
            //pegando a exceção e jogando novamente para personalizar a mensagem de erro
            throw new NumberFormatException("Os campos valor base, peso e temperatura são decimais e devem usar o ponto (.) como separador");
        }

        Tecnologia tecnologiaExistente = getTecnologiaById(id);

        if (tecnologiaExistente != null) {
            throw new IdentificadorJaExistenteException("Já existe uma tecnologia cadastrada com o ID: " + id);
        }

        Tecnologia novaTecnologia = new Tecnologia(id, modelo, descricao, valorBase, peso, temperatura);

        if (fornecedor != null) novaTecnologia.defineFornecedor(fornecedor);

        this.tecnologias.add(novaTecnologia);
        sortTecnologias();
    }

    public Tecnologia getTecnologiaById(long id) {
        return this.tecnologias.stream()
                .filter(tecnologia -> tecnologia.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void sortTecnologias() {
        this.tecnologias.sort(Comparator.comparingLong(Tecnologia::getId));
    }
}
