package dados;

import entidades.Comprador;
import entidades.Tecnologia;
import entidades.Venda;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatalogoVendas {
    private List<Venda> vendas;

    public CatalogoVendas() {
        this.vendas = new ArrayList<>();
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public Venda gerarVenda(String numRaw, String dataRaw, Tecnologia tecnologia, Comprador comprador)
            throws ParseException, IllegalArgumentException {

        if (numRaw.trim().isBlank() || dataRaw.trim().isBlank() || tecnologia == null || comprador == null)
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos");

        long num = Long.parseLong(numRaw.trim());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataRaw.trim(),  formato);

        return new Venda(num, data, tecnologia, comprador);
    }

    public void cadastrarVenda(Venda venda) {
        this.vendas.add(venda);
    }
}
