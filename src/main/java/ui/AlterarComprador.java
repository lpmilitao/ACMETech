package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import entidades.Comprador;
import entidades.IdentificadorJaExistenteException;
import ui.components.*;

import javax.swing.*;

public class AlterarComprador extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private JPanel panelAlterarComprador;
    private JLabel titulo;
    private JPanel panelDescricao;
    private JLabel descricao;
    private JButton botaoBuscar;
    private JButton botaoSalvar;
    private JButton botaoVoltar;
    private JTextField cod;
    private JPanel panelCod;
    private JLabel labelCod;
    private JPanel panelL1;
    private JPanel panelL2;
    private JPanel panelNome;
    private JTextField nome;
    private JLabel labelNome;
    private JTextField email;
    private JLabel labelEmail;
    private JTextField pais;
    private JLabel labelPais;
    private JPanel panelEmail;
    private JPanel panelPais;
    private JButton botaoCancelar;

    public AlterarComprador(ACMETech APLICACAO, CatalogoCompradores COMPRADORES) {
        this.APLICACAO = APLICACAO;
        this.COMPRADORES = COMPRADORES;
    }

    @Override
    public JPanel getPanel() {
        return panelAlterarComprador;
    }

    private void createUIComponents() {
        botaoSalvar = new Botao("Salvar Alterações");
        botaoBuscar = new Botao("Buscar");
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoCancelar = new Botao("Cancelar");

        botaoSalvar.addActionListener(e -> salvarAlteracoes());
        botaoBuscar.addActionListener(e -> buscarDadosComprador());
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));
        botaoCancelar.addActionListener(e -> {
            limparCampos();
            botaoSalvar.setEnabled(false);
            botaoBuscar.setEnabled(true);
        });

        botaoSalvar.setEnabled(false);

        labelCod = new Texto("Código do comprador", false);
        labelNome = new Texto("Nome", false);
        labelPais = new Texto("País", false);
        labelEmail = new Texto("Email", false);
        titulo = new Texto("Alterar dados de um comprador", true);
        descricao = new Texto("Digite o código do comprador desejado e depois atualize as informações", false);

        cod = new CampoTexto();
        nome = new CampoTexto();
        email = new CampoTexto();
        pais = new CampoTexto();

        nome.setEnabled(false);
        email.setEnabled(false);
        pais.setEnabled(false);
    }

    private void buscarDadosComprador() {
        long codComprador;

        if (cod.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Preencha o código do comprador.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            codComprador = Long.parseLong(cod.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O código do comprador deve ser numérico",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            cod.setText("");
            return;
        }

        Comprador comprador = this.COMPRADORES.getCompradorByCod(codComprador);

        if (comprador == null) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Não existe um comprador com o código '" + codComprador + "'.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            cod.setText("");
            return;
        }

        nome.setText(comprador.getNome());
        email.setText(comprador.getEmail());
        pais.setText(comprador.getPais());

        botaoSalvar.setEnabled(true);
        botaoBuscar.setEnabled(false);

        cod.setEnabled(false);
        nome.setEnabled(true);
        email.setEnabled(true);
        pais.setEnabled(true);
    }

    private void limparCampos() {
        cod.setText("");
        nome.setText("");
        email.setText("");
        pais.setText("");
    }

    private void salvarAlteracoes() {
        try {
            this.COMPRADORES.alterar(cod.getText(), nome.getText(), pais.getText(), email.getText());

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "As alteraçõs foram salvas com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();
            botaoSalvar.setEnabled(false);
            botaoBuscar.setEnabled(true);

        }  catch (IllegalArgumentException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    excp.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
