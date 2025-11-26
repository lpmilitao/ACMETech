package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import entidades.Comprador;
import entidades.Fornecedor;
import ui.components.*;

import javax.swing.*;
import java.awt.*;

public class RelatorioComprador extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private JPanel panelRelatorioComprador;
    private JLabel titulo;
    private JScrollPane panelArea;
    private JTextPane relatorio;
    private JButton botaoVoltar;

    public RelatorioComprador(ACMETech APLICACAO, CatalogoCompradores COMPRADORES) {
        this.APLICACAO = APLICACAO;
        this.COMPRADORES = COMPRADORES;
        ajustarScrollPane();
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioComprador;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        titulo = new Texto(true);

        relatorio = new AreaTexto();
    }

    public void atualizarLista() {
        relatorio.setText("");

        if (COMPRADORES.getCompradores().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Não há nenhum comprador cadastrado no sistema!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        StringBuilder lista = new StringBuilder();

        for (Comprador comprador : COMPRADORES.getCompradores()) {
            lista.append(comprador.toString() + "\n");
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
