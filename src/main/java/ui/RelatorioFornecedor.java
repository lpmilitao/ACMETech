package ui;

import aplicacao.ACMETech;
import dados.CatalogoFornecedores;
import entidades.Fornecedor;
import entidades.Tecnologia;
import ui.components.*;

import javax.swing.*;
import java.awt.*;

public class RelatorioFornecedor extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private JPanel panelRelatorioFornecedor;
    private JLabel titulo;
    private JScrollPane panelArea;
    private JTextPane relatorio;
    private JButton botaoVoltar;

    public RelatorioFornecedor(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
        ajustarScrollPane();
    }

    @Override
    public JPanel getPanel() {
        return panelRelatorioFornecedor;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        titulo = new Texto(true);

        relatorio = new AreaTexto();
    }

    public void atualizarLista() {
        relatorio.setText("");

        if (FORNECEDORES.getFornecedores().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Não há nenhum fornecedor cadastrado no sistema!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        StringBuilder lista = new StringBuilder();

        for (Fornecedor fornecedor : FORNECEDORES.getFornecedores()) {
            lista.append(fornecedor.toString() + "\n");
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
