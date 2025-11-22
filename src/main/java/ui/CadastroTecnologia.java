package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

import javax.swing.*;

public class CadastroTecnologia extends TelaBase{
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private JPanel panelCadastroTecnologia;

    public CadastroTecnologia(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES, CatalogoTecnologias TECNOLOGIAS) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
        this.TECNOLOGIAS = TECNOLOGIAS;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroTecnologia;
    }
}
