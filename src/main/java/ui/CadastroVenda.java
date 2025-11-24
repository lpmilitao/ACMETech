package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;
import entidades.Comprador;
import entidades.IdentificadorJaExistenteException;
import entidades.Tecnologia;
import entidades.Venda;
import ui.components.*;

import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CadastroVenda extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelCadastroVenda;
    private JLabel titulo;
    private JPanel panelL1;
    private JPanel panelL2;
    private JPanel panelNum;
    private JPanel panelData;
    private JPanel panelComprador;
    private JPanel panelTecnologia;
    private JTextField num;
    private JTextField data;
    private ComboBox comprador;
    private ComboBox tecnologia;
    private JButton botaoCadastrar;
    private JButton botaoLimpar;
    private JButton botaoVoltar;
    private JLabel labelNum;
    private JLabel labelComprador;
    private JLabel labelData;
    private JLabel labelTecnologia;

    public CadastroVenda(ACMETech APLICACAO, CatalogoCompradores COMPRADORES, CatalogoTecnologias TECNOLOGIAS, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.COMPRADORES = COMPRADORES;
        this.TECNOLOGIAS = TECNOLOGIAS;
        this.VENDAS = VENDAS;
    }

    @Override
    public JPanel getPanel() {
        return panelCadastroVenda;
    }

    public void atualizarListas() {
        List<String> compradores = new ArrayList<>();
        compradores.add(" ");

        for (Comprador c : COMPRADORES.getCompradores()) {
            compradores.add(c.geraDescricao());
        }

        comprador.atualizarLista(compradores);

        List<String> tecnologias = new ArrayList<>();
        tecnologias.add(" ");

        for (Tecnologia t : TECNOLOGIAS.getTecnologias()) {
            tecnologias.add(t.getId() + " - " + t.getModelo() + ": " + t.getDescricao() + " - R$" + t.getValorBase());
        }

        tecnologia.atualizarLista(tecnologias);
    }

    private void createUIComponents() {
        botaoCadastrar = new Botao("Cadastrar");
        botaoLimpar = new Botao("Limpar Campos");
        botaoVoltar = new Botao("Voltar ao Menu");

        botaoCadastrar.addActionListener(e -> cadastrar());
        botaoLimpar.addActionListener(e -> limparCampos());
        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));

        labelNum = new Texto(false);
        labelData = new Texto(false);
        labelComprador = new Texto(false);
        labelTecnologia = new Texto(false);
        titulo = new Texto(true);

        num = new CampoTexto();
        data = new CampoTexto();

        comprador = new ComboBox<>();
        tecnologia = new ComboBox<>();
        atualizarListas();
        comprador.setSelectedItem(" ");
        tecnologia.setSelectedItem(" ");
    }

    private void limparCampos() {
        num.setText("");
        data.setText("");
        comprador.setSelectedItem(" ");
        tecnologia.setSelectedItem(" ");
    }

    private void cadastrar() {
        try {
            Comprador compradorSelecionado = getCompradorSelecionado();
            Tecnologia tecnologiaSelecionada = getTecnologiaSelecionada();

            Venda venda = this.VENDAS.gerarVenda(num.getText(), data.getText(), tecnologiaSelecionada, compradorSelecionado);
            VENDAS.cadastrarVenda(venda);

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Venda cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limparCampos();

        } catch (NumberFormatException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "O número da venda tem que ser numérico",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
            this.num.setText("");

        } catch (IdentificadorJaExistenteException | IllegalArgumentException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    excp.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (ParseException excp) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "A data deve estar no formato dd/mm/aaaa",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private Comprador getCompradorSelecionado() {
        String[] partes = comprador.getSelectedItem().toString().split(";");
        String codRaw = partes[0].trim();

        if (codRaw.isBlank()) return null;

        long cod = Long.parseLong(codRaw);
        return this.COMPRADORES.getCompradorByCod(cod);
    }

    private Tecnologia getTecnologiaSelecionada() {
        String[] partes = tecnologia.getSelectedItem().toString().split(" - ");
        String idRaw = partes[0].trim();

        if (idRaw.isBlank()) return null;

        long id = Long.parseLong(idRaw);
        return this.TECNOLOGIAS.getTecnologiaById(id);
    }

}
