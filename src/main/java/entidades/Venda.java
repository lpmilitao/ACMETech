package entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
    private long num;
    private LocalDate data;
    private double valorFinal;
    private Tecnologia tecnologia;
    private Comprador comprador;

    public Venda(long num, LocalDate data, Tecnologia tecnologia,  Comprador comprador) {
        this.num = num;
        this.data = data;
        this.valorFinal = 0;
        this.tecnologia = tecnologia;
        this.comprador = comprador;
        this.valorFinal = calculaValorFinal();
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public double calculaValorFinal(){
        double percentualAcrescidoPorArea = this.tecnologia.getFornecedor().getArea().getAcrescimo();
        double percentualAcrescidoPorFidelidade = this.comprador.getQuantidadeComprada() * 0.01;

        if (percentualAcrescidoPorFidelidade > 0.10) percentualAcrescidoPorFidelidade = 0.10; //trava o desconto em 10%

        double valorBruto = tecnologia.getValorBase() + (percentualAcrescidoPorArea * tecnologia.getValorBase());
        double valorComDesconto = valorBruto - (percentualAcrescidoPorFidelidade * valorBruto);

        BigDecimal bd = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.HALF_UP); //deixando com duas casas decimais

        return bd.doubleValue();
    }

    public String getRelatorio() {
        return "[" + num + "] '" + tecnologia.getModelo() + ": " + tecnologia.getDescricao() +
                "' comprado por '" + comprador.getNome() + "' (" + comprador.getCod() + ") pelo valor de R$ " + valorFinal
                + " na data de " + getDataFormatada();
    }
}
