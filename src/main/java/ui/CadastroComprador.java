package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import ui.components.TelaBase;

import javax.swing.*;

public class CadastroComprador extends TelaBase {
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
