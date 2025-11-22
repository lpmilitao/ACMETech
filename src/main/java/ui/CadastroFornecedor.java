package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

import javax.swing.*;

public class CadastroFornecedor extends TelaBase{
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private JPanel panelCadastroFornecedor;

    public CadastroFornecedor(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroFornecedor;
    }
}
