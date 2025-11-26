package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;
import entidades.Comprador;
import ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Consulta<T> extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelConsulta;
    private JLabel titulo;
    private JPanel panelBotoes;
    private JButton botaoTecnologia;
    private JButton botaoFornecedor;
    private JButton botaoComprador;
    private JButton botaoVenda;
    private JButton botaoVoltar;
    private JScrollPane panelResultado;
    private JTextPane resultado;
    private List<?> lista;

    public Consulta(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES, CatalogoCompradores COMPRADORES,
                    CatalogoTecnologias TECNOLOGIAS, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
        this.COMPRADORES = COMPRADORES;
        this.TECNOLOGIAS = TECNOLOGIAS;
        this.VENDAS = VENDAS;
        this.lista = new ArrayList<>();
        ajustarScrollPane();
    }

    @Override
    public JPanel getPanel() {
        return panelConsulta;
    }

    private void createUIComponents() {

        botaoTecnologia = new Botao("Tecnologia com maior valor");
        botaoFornecedor = new Botao("Fornecedor com maior número de tecnologias");
        botaoComprador = new Botao("Comprador com maior número de vendas");
        botaoVenda = new Botao("Venda com maior valor");
        botaoVoltar = new Botao("Voltar ao Menu");

        botaoTecnologia.addActionListener(e -> consultarTecnologia());
        botaoFornecedor.addActionListener(e -> consultarFornecedor());
        botaoComprador.addActionListener(e -> consultarComprador());
        botaoVenda.addActionListener(e -> consultarVenda());
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        resultado = new AreaTexto();

        titulo = new Texto(true);
    }

    private void consultarTecnologia() {
        lista = TECNOLOGIAS.gedtTecnologiasComMaiorValor();
        atualizarLista();
    }

    private void consultarFornecedor() {
        lista = this.FORNECEDORES.getFornecedorComMaisTecnologias(this.TECNOLOGIAS.getTecnologias());
        atualizarLista();
    }

    private void consultarComprador() {
        lista = this.COMPRADORES.getCompradoresComMaisVendas();
        atualizarLista();
    }

    private void consultarVenda() {
        lista = this.VENDAS.getVendasMaisCaras();
        atualizarLista();
    }

    private void atualizarLista() {
        resultado.setText("");

        if (lista == null || lista.isEmpty()) {
            resultado.setText("Nenhum registro encontrado.");
            return;
        }

        StringBuilder listaFinal = new StringBuilder();

        for (Object objeto : lista) {
            listaFinal.append(objeto + "\n");
        }

        resultado.setText(listaFinal.toString());
    }

    public void limparLista(){
        resultado.setText("Selecione uma opção de consulta.");
    }

    private void ajustarScrollPane() {
        if (panelResultado != null) {
            panelResultado.setOpaque(false);
            panelResultado.getViewport().setOpaque(false);
            panelResultado.setBorder(null);
            panelResultado.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            panelResultado.getVerticalScrollBar().setUnitIncrement(16);
        }
    }
}
