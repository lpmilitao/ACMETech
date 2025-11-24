package ui;

import aplicacao.ACMETech;
import dados.CatalogoTecnologias;
import entidades.Tecnologia;
import ui.components.*;

import javax.swing.*;
import java.awt.*;

public class RelatorioTecnologia extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoTecnologias TECNOLOGIAS;
    private JPanel panelRelatorioTecnologia;
    private JLabel titulo;
    private JScrollPane panelArea;
    private JButton botaoVoltar;
    private JTextPane relatorio;


    public RelatorioTecnologia(ACMETech APLICACAO, CatalogoTecnologias TECNOLOGIAS) {
        this.APLICACAO = APLICACAO;
        this.TECNOLOGIAS = TECNOLOGIAS;
        ajustarScrollPane();
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioTecnologia;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        titulo = new Texto(true);

        relatorio = new AreaTexto();
    }

    public void atualizarLista() {
        relatorio.setText("");
        if (TECNOLOGIAS.getTecnologias().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Não há nenhuma tecnologia cadastrada no sistema!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        StringBuilder lista = new StringBuilder();

        for (Tecnologia tecnologia : TECNOLOGIAS.getTecnologias()) {
            lista.append(tecnologia.toString() + "\n");
        }

        relatorio.setText(lista.toString());
    }

    private void ajustarScrollPane() {
        if (panelArea != null) {
            panelArea.setOpaque(false);
        panelArea.getViewport().setOpaque(false);
        panelArea.setBorder(null);
        panelArea.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        panelArea.getVerticalScrollBar().setUnitIncrement(16);
        }
    }
}
