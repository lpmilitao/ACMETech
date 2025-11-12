package dados;

import entidades.Fornecedor;
import entidades.Tecnologia;

import java.util.ArrayList;
import java.util.List;

public class CatalogoTecnologias {
    private List<Tecnologia> tecnologias;

    public CatalogoTecnologias() {
        this.tecnologias = new ArrayList<>();
    }

    public List<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void cadastrarTecnologia(String id, String modelo, String descricao, String valorBase, String peso,
                                    String temperatura, Fornecedor fornecedor){
        Tecnologia novaTecnologia = new Tecnologia(
                Long.parseLong(id), modelo, descricao, Double.parseDouble(valorBase),
                Double.parseDouble(peso), Double.parseDouble(temperatura)
        );

        novaTecnologia.defineFornecedor(fornecedor);
        this.tecnologias.add(novaTecnologia);
    }
}
