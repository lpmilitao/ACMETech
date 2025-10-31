package entidades;

public enum Area {
    TI(20),
    ANDROIDES(15),
    EMERGENTE(25),
    ALIMENTOS(10);

    private final double acrescimo;

    Area(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public double getAcrescimo() {
        return acrescimo;
    }
}
