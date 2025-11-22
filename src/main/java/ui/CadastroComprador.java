package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

import javax.swing.*;

public class CadastroComprador extends TelaBase{
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private JPanel panelCadastroComprador;

    public CadastroComprador(ACMETech APLICACAO, CatalogoCompradores COMPRADORES) {
        this.APLICACAO = APLICACAO;
        this.COMPRADORES = COMPRADORES;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroComprador;
    }
}
