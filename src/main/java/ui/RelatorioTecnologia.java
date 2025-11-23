package ui;

import aplicacao.ACMETech;
import dados.CatalogoTecnologias;
import ui.components.TelaBase;

import javax.swing.*;

public class RelatorioTecnologia extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoTecnologias TECNOLOGIAS;
    private JPanel panelRelatorioTecnologia;


    public RelatorioTecnologia(ACMETech APLICACAO, CatalogoTecnologias TECNOLOGIAS) {
        this.APLICACAO = APLICACAO;
        this.TECNOLOGIAS = TECNOLOGIAS;
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioTecnologia;
    }
}
