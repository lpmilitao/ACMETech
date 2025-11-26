package ui;

import aplicacao.ACMETech;
import dados.CatalogoVendas;
import entidades.Venda;
import ui.components.*;

import javax.swing.*;
import java.awt.*;

public class RemoverVenda extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoVendas VENDAS;
    private JPanel panelRemoverVenda;
    private JLabel titulo;
    private JPanel panelNum;
    private JLabel labelNum;
    private JTextField num;
    private JTextPane vendas;
    private JScrollPane panelVendas;
    private JButton botaoExcluir;
    private JButton botaoVoltar;

    public RemoverVenda(ACMETech APLICACAO, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.VENDAS = VENDAS;
        ajustarScrollPane();
    }

    @Override
    public JPanel getPanel() {
        return panelRemoverVenda;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoExcluir = new Botao("Voltar ao Menu");

        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));
        botaoExcluir.addActionListener(e -> excluir());

        titulo = new Texto(true);
        labelNum = new Texto(false);

        num = new CampoTexto();

        vendas = new AreaTexto();
    }

    private void excluir() {
        try {
            this.VENDAS.excluirVenda(num.getText());

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "A venda '" + num.getText() + "' foi removida com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            num.setText("");
            atualizarLista();

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O número da venda deve ser numérico.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void atualizarLista() {
        vendas.setText("");

        StringBuilder lista = new StringBuilder();

        for (Venda venda : VENDAS.getVendas()) {
            lista.append(venda.relatorio() + "\n");
        }

        vendas.setText(lista.toString());
    }

    private void ajustarScrollPane() {
        if (panelVendas != null) {
            panelVendas.setOpaque(false);
            panelVendas.getViewport().setOpaque(false);
            panelVendas.setBorder(null);
            panelVendas.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            panelVendas.getVerticalScrollBar().setUnitIncrement(16);
        }
    }
}
