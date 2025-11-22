package ui;

import aplicacao.ACMETech;
import dados.CatalogoCompradores;
import dados.CatalogoFornecedores;
import dados.CatalogoTecnologias;
import dados.CatalogoVendas;

import javax.swing.*;

public class CadastroVenda extends TelaBase{
    private final ACMETech APLICACAO;
    private final CatalogoCompradores COMPRADORES;
    private final CatalogoTecnologias TECNOLOGIAS;
    private final CatalogoVendas VENDAS;
    private JPanel panelCadastroVenda;

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
}
