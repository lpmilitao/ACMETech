package dados;

import entidades.Area;
import entidades.Fornecedor;
import entidades.IdentificadorJaExistenteException;
import entidades.Tecnologia;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CatalogoFornecedores {
    private List<Fornecedor> fornecedores;

    public CatalogoFornecedores() {
        this.fornecedores = new ArrayList<>();
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void cadastrarFornecedor(String codRaw, String nome, String dataRaw, String areaRaw) throws ParseException {

        if (codRaw.trim().isBlank() || nome.trim().isBlank()
                || dataRaw.trim().isBlank() || areaRaw.trim().isBlank()
        ) {
            throw new IllegalArgumentException();
        }

        Area area = Area.valueOf(areaRaw.trim());

        long cod = Long.parseLong(codRaw.trim());
        checarCodigoRepetido(cod);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataRaw.trim(), formato);

        Fornecedor novoFornecedor = new Fornecedor(cod, nome, data, area);
        this.fornecedores.add(novoFornecedor);

        sortFornecedores();
    }

    private void checarCodigoRepetido(long cod){
        Optional<Fornecedor> fornecedorExistente = this.fornecedores.stream()
                .filter(fornecedor -> fornecedor.getCod() == cod)
                .findFirst();

        if (fornecedorExistente.isPresent()){
            throw new IdentificadorJaExistenteException("Fornecedor já existente com código " + cod);
        }
    }

    private void sortFornecedores() {
        this.fornecedores.sort(Comparator.comparingLong(Fornecedor::getCod));
    }

    public void deletarTodos() {
        this.fornecedores.clear();
    }

    public Fornecedor getFornecedorByCod(long cod) {
        return this.fornecedores.stream().filter(fornecedor -> fornecedor.getCod() == cod)
                .findFirst()
                .orElse(null);
    }

    public List<Fornecedor> getFornecedorComMaisTecnologias(List<Tecnologia> tecnologias) {
        if (tecnologias.isEmpty() || fornecedores.isEmpty()) return new ArrayList<>();

        this.fornecedores.sort(Comparator.comparingDouble(Fornecedor::getQtdTecnologiasFornecidas).reversed());
        double maiorQtd = this.fornecedores.getFirst().getQtdTecnologiasFornecidas();

        List<Fornecedor> vencedores = this.fornecedores.stream()
                .filter(f -> f.getQtdTecnologiasFornecidas() >= maiorQtd)
                .toList();

        sortFornecedores(); //ajustando a ordenação normal

        return vencedores;
    }
}
