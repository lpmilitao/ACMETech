package ui;

import aplicacao.ACMETech;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;
import entidades.DTO.Persistencia;
import ui.components.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CarregarDados extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelCarregarDados;
    private JLabel titulo;
    private JTextField arquivo;
    private JButton botaoCarregar;
    private JButton botaoVoltar;
    private JLabel labelArquivo;

    public CarregarDados(ACMETech APLICACAO, CatalogoFornecedores FORNECEDORES, CatalogoCompradores COMPRADORES,
                         CatalogoTecnologias TECNOLOGIAS, CatalogoVendas VENDAS) {
        this.APLICACAO = APLICACAO;
        this.FORNECEDORES = FORNECEDORES;
        this.COMPRADORES = COMPRADORES;
        this.TECNOLOGIAS = TECNOLOGIAS;
        this.VENDAS = VENDAS;
    }

    @Override
    public JPanel getPanel() {
        return panelCarregarDados;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoCarregar = new Botao("Carregar");

        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));
        botaoCarregar.addActionListener(e -> carregar());

        titulo = new Texto(true);
        labelArquivo = new Texto(false);

        arquivo = new CampoTexto();
    }

    private void carregar() {
        File diretorio = new File("persistencia");
        String nomeArquivo = arquivo.getText();

        if (!verificaNomeArquivoValido(nomeArquivo)) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Nome de arquivo inválido!\nUse apenas letras, números, espaços, hífens e underlines.\nNão digite a extensão (.json).",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        nomeArquivo += ".json";


        File arquivoFonte = new File(diretorio, nomeArquivo);

        if (!arquivoFonte.exists()) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Arquivo não encontrado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Persistencia dados;

        try {
            dados = mapper.readValue(arquivoFonte, Persistencia.class);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Erro ao carregar dados de arquivo.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println(e.getMessage()); //mensagem de erro detalhada para o "backend"/administrador e não para o usuario
            return;
        }

        mapeiaAPartirDeDados(dados);
        JOptionPane.showMessageDialog(
                this.getPanel(),
                "Dados carregados com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );
        arquivo.setText("");
    }

    private void mapeiaAPartirDeDados(Persistencia dados) {

        this.COMPRADORES.setCompradores(dados.getCompradores());
        this.FORNECEDORES.setFornecedores(dados.getFornecedores());
        this.TECNOLOGIAS.setTecnologias(dados.getTecnologias());
        this.VENDAS.setVendas(dados.getVendas());
    }

    private boolean verificaNomeArquivoValido(String nome) {
        if (nome.trim().isEmpty()) return false;

        String regex = "^[a-zA-Z0-9_\\-\\s()]+$";

        return nome.matches(regex) && !nome.contains(".");
    }
}
