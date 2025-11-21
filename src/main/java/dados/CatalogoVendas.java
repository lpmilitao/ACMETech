package dados;

import entidades.Comprador;
import entidades.Tecnologia;
import entidades.Venda;

import java.text.ParseException;
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

    public Venda gerarVenda(String numRaw, String dataRaw, Tecnologia tecnologia, Comprador comprador) throws ParseException {

        if (numRaw.trim().isBlank() || dataRaw.trim().isBlank()) throw new IllegalArgumentException();

        long num = Long.parseLong(numRaw.trim());
        Date data = Util.transformaData(dataRaw);

        return new Venda(num, data, tecnologia, comprador);
    }

    public void cadastrarVenda(Venda venda) {
        this.vendas.add(venda);
    }
}
