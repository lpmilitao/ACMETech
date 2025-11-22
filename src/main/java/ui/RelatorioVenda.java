package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

import javax.swing.*;

public class RelatorioVenda extends TelaBase{
    private final ACMETech APLICACAO;
    private final CatalogoVendas VENDAS;
    private JPanel panelRelatorioVenda;

    public RelatorioVenda(ACMETech APLICACAO, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.VENDAS = VENDAS;
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioVenda;
    }
}
