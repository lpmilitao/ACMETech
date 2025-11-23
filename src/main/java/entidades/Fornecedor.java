package entidades;

import java.time.LocalDate;

public class Fornecedor extends Participante {
    private LocalDate fundacao;
    private Area area;

    public Fornecedor(long cod, String nome, LocalDate fundacao, Area area) {
        super(cod, nome);
        this.fundacao = fundacao;
        this.area = area;
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

    @Override
    public String geraDescricao() {
        return getCod() + ";" + getNome() + ";" + getFundacao() + ";" + getArea();
    }
}
