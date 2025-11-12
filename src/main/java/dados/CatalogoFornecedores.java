package dados;

import entidades.Area;
import entidades.Fornecedor;
import entidades.FornecedorJaExistenteException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        Fornecedor novoFornecedor = new Fornecedor(cod, nome,
                transformaData(dataRaw), area
        );
        this.fornecedores.add(novoFornecedor);

        sortFornecedores();
    }

    private void checarCodigoRepetido(long cod){
        Optional<Fornecedor> fornecedorExistente = this.fornecedores.stream()
                .filter(fornecedor -> fornecedor.getCod() == cod)
                .findFirst();

        if (fornecedorExistente.isPresent()){
            throw new FornecedorJaExistenteException("Fornecedor já existente com código " + cod);
        }
    }

    private void sortFornecedores() {
        this.fornecedores.sort(Comparator.comparingLong(Fornecedor::getCod));
    }

    private Date transformaData(String dataRaw) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        return formato.parse(dataRaw);
    }

    public void deletarTodos() {
        this.fornecedores.clear();
    }

    public Fornecedor getFornecedorByCod(long cod) {
        return this.fornecedores.stream().filter(fornecedor -> fornecedor.getCod() == cod)
                .findFirst()
                .orElse(null);
    }
}
