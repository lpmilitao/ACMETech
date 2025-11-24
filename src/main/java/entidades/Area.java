package entidades;

public enum Area {
    TI(0.2),
    ANDROIDES(0.15),
    EMERGENTE(0.25),
    ALIMENTOS(0.1);

    private final double acrescimo;

    Area(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public double getAcrescimo() {
        return acrescimo;
    }
}
