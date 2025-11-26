package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fornecedor extends Participante {
    private LocalDate fundacao;
    private Area area;
    private int qtdTecnologiasFornecidas;

    public Fornecedor(long cod, String nome, LocalDate fundacao, Area area) {
        super(cod, nome);
        this.fundacao = fundacao;
        this.area = area;
        this.qtdTecnologiasFornecidas = 0;
    }

    public Fornecedor() {
    }

    public String dataDeFundacaoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fundacao.format(formatter);
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFundacao(LocalDate fundacao) {
        this.fundacao = fundacao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getQtdTecnologiasFornecidas() {
        return qtdTecnologiasFornecidas;
    }

    public void setQtdTecnologiasFornecidas(int qtdTecnologiasFornecidas) {
        this.qtdTecnologiasFornecidas = qtdTecnologiasFornecidas;
    }

    public void addTecnologia(){
        this.qtdTecnologiasFornecidas++;
    }

    @Override
    public String geraDescricao() {
        return getCod() + ";" + getNome() + ";" + getFundacao() + ";" + getArea() + ";" + getQtdTecnologiasFornecidas();
    }

    @Override
    public String toString() {
        return "(" + getCod() + ") " + getNome() + " - "+ dataDeFundacaoFormatada() + " - " + getArea().toString() + " - tecnologias: " + getQtdTecnologiasFornecidas();
    }
}
