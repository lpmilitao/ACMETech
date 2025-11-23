package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import entidades.ParticipanteJaExistenteException;
import ui.components.*;

import javax.swing.*;
import java.text.ParseException;

public class CadastroComprador extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private JPanel panelCadastroComprador;
    private JPanel panelL1;
    private JPanel panelL2;
    private JPanel panelCod;
    private JPanel panelNome;
    private JPanel panelPais;
    private JPanel panelEmail;
    private JTextField email;
    private JTextField nome;
    private JTextField pais;
    private JTextField cod;
    private JLabel labelCod;
    private JLabel labelPais;
    private JLabel labelNome;
    private JLabel labelEmail;
    private JButton botaoCadastrar;
    private JButton botaoLimpar;
    private JButton botaoVoltar;
    private JLabel titulo;

    public CadastroComprador(ACMETech APLICACAO, CatalogoCompradores COMPRADORES) {
        this.APLICACAO = APLICACAO;
        this.COMPRADORES = COMPRADORES;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroComprador;
    }

    private void createUIComponents() {
        botaoCadastrar = new Botao("Cadastrar");
        botaoLimpar = new Botao("Limpar Campos");
        botaoVoltar = new Botao("Voltar ao Menu");

        botaoCadastrar.addActionListener(e -> cadastrar());
        botaoLimpar.addActionListener(e -> limparCampos());
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        labelCod = new Texto("Código do comprador", false);
        labelNome = new Texto("Nome do comprador", false);
        labelPais = new Texto("País de nascimento", false);
        labelEmail = new Texto("Email do comprador", false);
        titulo = new Texto("Cadastro de compradores", true);

        int colunas = 20;
        cod = new CampoTexto(colunas);
        nome = new CampoTexto(colunas);
        email = new CampoTexto(colunas);
        pais = new CampoTexto(colunas);
    }

    private void limparCampos() {
        cod.setText("");
        nome.setText("");
        email.setText("");
        pais.setText("");
    }

    private void cadastrar() {
        try {
            this.COMPRADORES.cadastrarComprador(cod.getText(), nome.getText(), pais.getText(), email.getText());

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O novo comprador foi cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();

        }  catch (NumberFormatException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O código do comprador deve ser numérico",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            this.cod.setText("");
        } catch (ParticipanteJaExistenteException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    excp.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            cod.setText("");

        } catch (IllegalArgumentException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    excp.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
