package entidades;

public abstract class Participante {
    private long cod;
    private String nome;

    public Participante(long cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract String geraDescricao();
}
