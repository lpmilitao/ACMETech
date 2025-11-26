package dados;

import entidades.Comprador;
import entidades.Fornecedor;
import entidades.Tecnologia;
import entidades.Venda;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CatalogoVendas {
    private List<Venda> vendas;

    public CatalogoVendas() {
        this.vendas = new ArrayList<>();
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Venda gerarVenda(String numRaw, String dataRaw, Tecnologia tecnologia, Comprador comprador)
            throws ParseException, IllegalArgumentException {

        if (numRaw.trim().isBlank() || dataRaw.trim().isBlank() || tecnologia == null || comprador == null)
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos");

        long num = Long.parseLong(numRaw.trim());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataRaw.trim(), formato);

        if (tecnologia.isVendida()) {
            throw new IllegalArgumentException("A tecnologia '" + tecnologia.getModelo() + "' já foi vendida.");
        }

        Venda novaVenda = new Venda(num, data, tecnologia, comprador);
        tecnologia.setVendida(true);
        comprador.registrarNovaCompra();

        return novaVenda;
    }

    public void cadastrarVenda(Venda venda) {
        this.vendas.add(venda);
        sortVendas();
    }

    private void sortVendas() {
        this.vendas.sort(Comparator.comparingLong(Venda::getNum).reversed());
    }

    public void excluirVenda(String numRaw) {
        if (numRaw.trim().isBlank()) throw new IllegalArgumentException("O número da venda precisa ser preenchido.");

        long num = Long.parseLong(numRaw.trim());

        Optional<Venda> venda = this.vendas.stream().filter(v -> v.getNum() == num)
                .findFirst();

        if (venda.isEmpty()) throw new IllegalArgumentException("A venda de no. '" + numRaw + "' não existe.");

        venda.get().getTecnologia().setVendida(false);
        vendas.remove(venda.get());
    }

    public List<Venda> getVendasMaisCaras() {
        if (this.vendas.isEmpty()) return new ArrayList<>();

        this.vendas.sort(Comparator.comparingDouble(Venda::getValorFinal).reversed());
        double maiorValor = this.vendas.getFirst().getValorFinal();

        List<Venda> caras = this.vendas.stream()
                .filter(v -> v.getValorFinal() >= maiorValor)
                .toList();

        sortVendas(); //ajustando a ordenação normal

        return caras;

    }
}
