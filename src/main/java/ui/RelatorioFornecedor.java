package ui;

import aplicacao.ACMETech;
import dados.CatalogoFornecedores;
import ui.components.TelaBase;

import javax.swing.*;

public class RelatorioFornecedor extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private JPanel panelRelatorioFornecedor;

    public RelatorioFornecedor(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioFornecedor;
    }
}
