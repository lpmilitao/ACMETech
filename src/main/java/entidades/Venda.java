package entidades;

import java.time.LocalDate;

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

        return tecnologia.getValorBase() +
                (percentualAcrescidoPorArea * tecnologia.getValorBase()) +
                (percentualAcrescidoPorFidelidade * tecnologia.getValorBase());
    }

    @Override
    public String toString() {
        return "Venda{" +
                "num=" + num +
                ", data=" + data +
                ", valorFinal=" + valorFinal +
                ", tecnologia=" + tecnologia.getDescricao() +
                ", comprador=" + comprador.getNome() +
                '}';
    }
}
