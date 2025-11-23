package ui;

import aplicacao.ACMETech;
import dados.CatalogoVendas;
import ui.components.TelaBase;

import javax.swing.*;

public class RemoverVenda extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoVendas VENDAS;
    private JPanel panelRemoverVenda;

    public RemoverVenda(ACMETech APLICACAO, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.VENDAS = VENDAS;
    }

    @Override
    public JPanel getPanel() {
        return panelRemoverVenda;
    }
}
