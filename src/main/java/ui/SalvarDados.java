package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

import javax.swing.*;

public class SalvarDados extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelSalvarDados;

    public SalvarDados(ACMETech aplicacao, CatalogoFornecedores fornecedores, CatalogoCompradores compradores,
                       CatalogoTecnologias tecnologias, CatalogoVendas vendas) {
        this.APLICACAO = aplicacao;
        this.FORNECEDORES = fornecedores;
        this.COMPRADORES = compradores;
        this.TECNOLOGIAS = tecnologias;
        this.VENDAS = vendas;
    }

    @Override
    public JPanel getPanel() {
        return panelSalvarDados;
    }
}
