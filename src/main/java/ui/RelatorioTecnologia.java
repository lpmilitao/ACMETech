package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

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
