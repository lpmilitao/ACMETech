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

        botaoCadastroFornecedor.addActionListener(e -> aplicacao.mudarTela(Telas.CADASTRO_FORNECEDOR));
        botaoCadastroTecnologia.addActionListener(e -> aplicacao.mudarTela(Telas.CADASTRO_TECNOLOGIA));
        botaoCadastroComprador.addActionListener(e -> aplicacao.mudarTela(Telas.CADASTRO_COMPRADOR));
        botaoCadastrarVenda.addActionListener(e -> aplicacao.mudarTela(Telas.CADASTRO_VENDA));
        botaoRelatorioFornecedores.addActionListener(e -> aplicacao.mudarTela(Telas.RELATORIO_FORNECEDOR));
        botaoRelatorioTecnologias.addActionListener(e -> aplicacao.mudarTela(Telas.RELATORIO_TECNOLOGIA));
        botaoRelatorioCompradores.addActionListener(e -> aplicacao.mudarTela(Telas.RELATORIO_COMPRADOR));
        botaoRelatorioVendas.addActionListener(e -> aplicacao.mudarTela(Telas.RELATORIO_VENDA));
        botaoRemoverVenda.addActionListener(e -> aplicacao.mudarTela(Telas.REMOVER_VENDA));
        botaoAlterarComprador.addActionListener(e -> aplicacao.mudarTela(Telas.ALTERAR_COMPRADOR));
        botaoConsulta.addActionListener(e -> aplicacao.mudarTela(Telas.CONSULTA));
        botaoSalvarDados.addActionListener(e -> aplicacao.mudarTela(Telas.SALVAR_DADOS));
        botaoCarregarDados.addActionListener(e -> aplicacao.mudarTela(Telas.CARREGAR_DADOS));
        botaoFinalizar.addActionListener(e -> System.exit(0));
    }
}
