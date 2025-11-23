package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;
import ui.components.TelaBase;

import javax.swing.*;

public class Consulta extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelConsulta;

    public Consulta(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES, CatalogoCompradores COMPRADORES,
                    CatalogoTecnologias TECNOLOGIAS, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
        this.COMPRADORES = COMPRADORES;
        this.TECNOLOGIAS = TECNOLOGIAS;
        this.VENDAS = VENDAS;
    }

    @Override
    public JPanel getPanel() {
        return panelConsulta;
    }
}
