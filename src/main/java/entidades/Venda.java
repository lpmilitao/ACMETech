package entidades;

import java.util.Date;

public class Venda {
    private long num;
    private Date data;
    private double valorFinal;
    private Tecnologia tecnologia;
    private Comprador comprador;

    public Venda(long num, Date data, double valorFinal,  Tecnologia tecnologia,  Comprador comprador) {
        this.num = num;
        this.data = data;
        this.valorFinal = valorFinal;
        this.tecnologia = tecnologia;
        this.comprador = comprador;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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
        // TODO
        return 0.0;
    }
}
