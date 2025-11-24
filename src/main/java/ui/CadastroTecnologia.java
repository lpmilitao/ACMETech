package ui;

import aplicacao.ACMETech;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import entidades.Area;
import entidades.Fornecedor;
import entidades.IdentificadorJaExistenteException;
import ui.components.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroTecnologia extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private JPanel panelCadastroTecnologia;
    private JLabel titulo;
    private JPanel panelL1;
    private JPanel panelId;
    private JPanel panelModelo;
    private JPanel panelDescricao;
    private JPanel panelValorBase;
    private JPanel panelPeso;
    private JPanel panelTemperatura;
    private JPanel panelFornecedor;
    private JPanel panelL2;
    private JButton botaoCadastrar;
    private JButton botaoVoltar;
    private JButton botaoLimpar;
    private JTextField id;
    private JLabel labelId;
    private JTextField modelo;
    private JLabel labelModelo;
    private JTextField descricao;
    private JLabel labelDescricao;
    private JTextField valorBase;
    private JLabel labelValorBase;
    private JTextField peso;
    private JLabel labelPeso;
    private JTextField temperatura;
    private JLabel labelTemperatura;
    private ComboBox<String> fornecedor;
    private JLabel labelFornecedor;

    public CadastroTecnologia(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES, CatalogoTecnologias TECNOLOGIAS) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
        this.TECNOLOGIAS = TECNOLOGIAS;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroTecnologia;
    }

    private void createUIComponents() {
        botaoCadastrar = new Botao("Cadastrar");
        botaoLimpar = new Botao("Limpar Campos");
        botaoVoltar = new Botao("Voltar ao Menu");

        botaoCadastrar.addActionListener(e -> cadastrar());
        botaoLimpar.addActionListener(e -> limparCampos());
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        labelId = new Texto(false);
        labelDescricao = new Texto(false);
        labelModelo = new Texto(false);
        labelPeso = new Texto(false);
        labelTemperatura = new Texto(false);
        labelValorBase = new Texto(false);
        labelFornecedor = new Texto(false);
        titulo = new Texto(true);

        int colunas = 20;
        id = new CampoTexto(colunas);
        descricao = new CampoTexto(colunas);
        modelo = new CampoTexto(colunas);
        peso = new CampoTexto(colunas);
        temperatura = new CampoTexto(colunas);
        valorBase = new CampoTexto(colunas);


        fornecedor = new ComboBox<>();
        atualizarFornecedores();
        fornecedor.setSelectedItem(" ");
    }

    private void limparCampos() {
        id.setText("");
        modelo.setText("");
        descricao.setText("");
        valorBase.setText("");
        temperatura.setText("");
        peso.setText("");
        fornecedor.setSelectedItem(" ");
    }

    private void cadastrar() {
        try {
            Fornecedor fornecedorEscolhido = getFornecedorEscolhido();
            TECNOLOGIAS.cadastrarTecnologia(id.getText(), modelo.getText(), descricao.getText(), valorBase.getText(),
                    peso.getText(), temperatura.getText(), fornecedorEscolhido);

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "A nova tecnologia foi cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    e.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IdentificadorJaExistenteException e){
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    e.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            id.setText("");
        }
    }

    private Fornecedor getFornecedorEscolhido() {
        String[] partes = fornecedor.getSelectedItem().toString().split(" - ");
        String codRaw = partes[0].trim();

        if (codRaw.isBlank()) return null;

        long cod = Long.parseLong(codRaw);
        return this.FORNECEDORES.getFornecedorByCod(cod);
    }

    public void atualizarFornecedores() {
        List<String> fornecedores = new ArrayList<>();
        fornecedores.add(" ");

        for (Fornecedor f : FORNECEDORES.getFornecedores()) {
            fornecedores.add(f.getCod() + " - " + f.getNome());
        }

        fornecedor.atualizarLista(fornecedores);
    }
}
