package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fornecedor extends Participante {
    private LocalDate fundacao;
    private Area area;

    public Fornecedor(long cod, String nome, LocalDate fundacao, Area area) {
        super(cod, nome);
        this.fundacao = fundacao;
        this.area = area;
    }

    public String getFundacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fundacao.format(formatter);
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

    @Override
    public String geraDescricao() {
        return getCod() + ";" + getNome() + ";" + getFundacao() + ";" + getArea();
    }

    @Override
    public String toString() {
        return "(" + getCod() + ") " + getNome() + " - "+ getFundacao() + " - " + getArea().toString();
    }
}
