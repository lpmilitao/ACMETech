package ui;

import aplicacao.ACMETech;

import javax.swing.*;

public class Menu extends TelaBase {
    private ACMETech aplicacao;
    private JPanel panelMenu;
    private JPanel panelL1;
    private JButton botaoCadastroFornecedor;
    private JButton botaoCadastroTecnologia;
    private JButton botaoCadastroComprador;
    private JButton botaoCadastrarVenda;
    private JButton botaoRelatorioFornecedores;
    private JButton botaoRelatorioTecnologias;
    private JButton botaoRelatorioCompradores;
    private JButton botaoRelatorioVendas;
    private JButton botaoRemoverVenda;
    private JButton botaoAlterarComprador;
    private JButton botaoConsulta;
    private JButton botaoSalvarDados;
    private JButton botaoCarregarDados;
    private JButton botaoFinalizar;
    private JPanel panelL2;

    public Menu(ACMETech aplicacao) {
        this.aplicacao = aplicacao;
    }

    @Override
    public JPanel getPanel() {
        return panelMenu;
    }

    private void inicializarLayout() {

    }

    private void createUIComponents() {
        botaoCadastroFornecedor = new Botao("Cadastrar Fornecedor");
        botaoCadastroTecnologia= new Botao("Cadastrar Tecnologia");
        botaoCadastroComprador = new Botao("Cadastrar Comprador");
        botaoCadastrarVenda = new Botao("Cadastrar Venda");
        botaoRelatorioFornecedores = new Botao("Relatório de Fornecedores");
        botaoRelatorioTecnologias = new Botao("Relatório de Tecnologias");
        botaoRelatorioCompradores = new Botao("Relatório de Compradores");
        botaoRelatorioVendas = new Botao("Relatório de Vendas");
        botaoRemoverVenda = new Botao("Remover uma Venda");
        botaoAlterarComprador = new Botao("Alterar Comprador");
        botaoConsulta = new Botao("Consultar");
        botaoSalvarDados = new Botao("Salvar Dados");
        botaoCarregarDados = new Botao("Carregar Dados");
        botaoFinalizar = new Botao("Finalizar Programa");
    }
}
