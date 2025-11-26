package entidades.DTO;

import entidades.Comprador;
import entidades.Fornecedor;
import entidades.Tecnologia;
import entidades.Venda;
import ui.components.Telas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Persistencia {
    private List<Comprador> compradores;
    private List<Fornecedor> fornecedores;
    private List<Tecnologia> tecnologias;
    private List<Venda> vendas;

    public Persistencia() {
        this.compradores = new ArrayList<>();
        this.fornecedores = new ArrayList<>();
        this.tecnologias = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public List<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<Tecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
