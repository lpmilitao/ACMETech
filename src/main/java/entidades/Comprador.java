package entidades;

public class Comprador extends Participante {
    private String pais;
    private String email;
    private int quantidadeComprada;

    public Comprador(long cod, String nome, String pais, String email) {
        super(cod, nome);
        this.pais = pais;
        this.email = email;
        this.quantidadeComprada = 0;
    }

    public Comprador() {
        super();
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }
    public void registrarNovaCompra() {
        this.quantidadeComprada++;
    }

    @Override
    public String geraDescricao() {
        return getCod() + ";" + getNome() + ";" + getPais() + ";" + getEmail() +  ";" + getQuantidadeComprada();
    }

    @Override
    public String toString() {
        return "(" + getCod() + ") " + getNome() + " - "+ getEmail() + " - " + getPais();
    }
}
