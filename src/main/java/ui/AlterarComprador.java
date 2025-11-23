package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import ui.components.TelaBase;

import javax.swing.*;

public class AlterarComprador extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private JPanel panelAlterarComprador;

    public AlterarComprador(ACMETech APLICACAO, CatalogoCompradores COMPRADORES) {
        this.APLICACAO = APLICACAO;
        this.COMPRADORES = COMPRADORES;
    }

    @Override
    public JPanel getPanel() {
        return panelAlterarComprador;
    }
}
