package ui;

import aplicacao.ACMETech;
import dados.CatalogoFornecedores;
import dados.CatalogoVendas;
import entidades.Fornecedor;
import entidades.Venda;
import ui.components.*;

import javax.swing.*;
import java.awt.*;

public class RelatorioVenda extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoVendas VENDAS;
    private JPanel panelRelatorioVenda;
    private JButton botaoVoltar;
    private JLabel titulo;
    private JTextPane relatorio;
    private JScrollPane panelArea;

    public RelatorioVenda(ACMETech APLICACAO, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.VENDAS = VENDAS;
        ajustarScrollPane();
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioVenda;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        titulo = new Texto(true);

        relatorio = new AreaTexto();
    }

    public void atualizarLista() {
        relatorio.setText("");

        if (VENDAS.getVendas().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Não há nenhuma venda cadastrado no sistema!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        StringBuilder lista = new StringBuilder();

        for (Venda venda : VENDAS.getVendas()) {
            lista.append(venda.getRelatorio() + "\n");
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
