package ui;

import aplicacao.ACMETech;
import dados.CatalogoFornecedores;
import entidades.FornecedorJaExistenteException;

import javax.swing.*;
import java.text.ParseException;

public class CadastroFornecedor extends TelaBase{
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private JPanel panelCadastroFornecedor;
    private JPanel panelL1;
    private JPanel panelL2;
    private JTextField cod;
    private JTextField nome;
    private JTextField fundacao;
    private JTextField area;
    private JButton botaoCadastrar;
    private JButton botaoLimpar;
    private JButton botaoVoltar;

    public CadastroFornecedor(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroFornecedor;
    }

    private void createUIComponents() {
        botaoCadastrar = new Botao("Cadastrar");
        botaoLimpar =  new Botao("Limpar Campos");
        botaoVoltar =  new Botao("Voltar ao Menu");

        botaoCadastrar.addActionListener(e -> cadastrar());
        botaoLimpar.addActionListener(e -> {
            cod.setText("");
            nome.setText("");
            fundacao.setText("");
            area.setText("");
        });
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));
    }

    private void cadastrar() {
        try {
            this.FORNECEDORES.cadastrarFornecedor(
                    cod.getText(), nome.getText(), fundacao.getText(), area.getText()
            );

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O novo fornecedor foi cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();

        } catch (FornecedorJaExistenteException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    excp.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O código do fornecedor deve ser numérico",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            this.cod.setText("");
        } catch (IllegalArgumentException  excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O campos código e nome do fornecedor não podem ficar em branco!",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (ParseException  excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O código deve ser númerico e a data deve estar no formato dd/mm/aaaa",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limparCampos(){
        cod.setText("");
        nome.setText("");
        fundacao.setText("");
        area.setText("");
    }
}
