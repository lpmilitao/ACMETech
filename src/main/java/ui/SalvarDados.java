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

public class SalvarDados extends TelaBase {
    private final ACMETech APLICACAO;
    private final CatalogoFornecedores FORNECEDORES;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelSalvarDados;
    private JLabel titulo;
    private JPanel panelArquivo;
    private JLabel labelArquivo;
    private JTextField arquivo;
    private JButton botaoVoltar;
    private JButton botaoSalvar;
    private JPanel panelBotoes;

    public SalvarDados(ACMETech aplicacao, CatalogoFornecedores fornecedores, CatalogoCompradores compradores,
                       CatalogoTecnologias tecnologias, CatalogoVendas vendas) {
        this.APLICACAO = aplicacao;
        this.FORNECEDORES = fornecedores;
        this.COMPRADORES = compradores;
        this.TECNOLOGIAS = tecnologias;
        this.VENDAS = vendas;
    }

    @Override
    public JPanel getPanel() {
        return panelSalvarDados;
    }

    private void createUIComponents() {
        botaoVoltar = new Botao("Voltar ao Menu");
        botaoSalvar = new Botao("Salvar");

        botaoVoltar.addActionListener(e -> APLICACAO.mudarTela(Telas.MENU));
        botaoSalvar.addActionListener(e -> salvar());

        titulo = new Texto(true);
        labelArquivo = new Texto(false);

        arquivo = new CampoTexto();
    }

    private void salvar() {
        String nomeArquivo = arquivo.getText();

        if (!verificaNomeArquivoValido(nomeArquivo)) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Nome inválido!\nUse apenas letras, números, espaços, hífens e underlines.\nNão digite a extensão (.json).",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        nomeArquivo += ".json";

        File diretorio = new File("persistencia");
        if (!diretorio.exists()) diretorio.mkdirs();

        File arquivoDestino = new File(diretorio, nomeArquivo);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Persistencia dados = mapeiaParaDados();

        try {
            mapper.writeValue(arquivoDestino, dados);

            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Dados salvos com sucesso no arquivo '" + nomeArquivo + "'",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            arquivo.setText("");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this.getPanel(),
                    "Ocorreu um erro durante o salvamento de dados.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.out.println(e.getMessage()); //mensagem de erro detalhada para o "backend"/administrador e não para o usuario
        }
    }

    private Persistencia mapeiaParaDados() {
        Persistencia dados = new Persistencia();

        dados.setCompradores(COMPRADORES.getCompradores());
        dados.setFornecedores(FORNECEDORES.getFornecedores());
        dados.setTecnologias(TECNOLOGIAS.getTecnologias());
        dados.setVendas(VENDAS.getVendas());

        return dados;
    }

    public boolean verificaNomeArquivoValido(String nome) {
        if (nome.trim().isEmpty()) return false;

        String regex = "^[a-zA-Z0-9_\\-\\s()]+$";

        return nome.matches(regex) && !nome.contains(".");
    }
}
