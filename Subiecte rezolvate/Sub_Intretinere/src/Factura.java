public class Factura {
    private String denumire;
    private String repartizare;
    private double valoare;

    public Factura(String denumire, String repartizare, double valoare) {
        this.denumire = denumire;
        this.repartizare = repartizare;
        this.valoare = valoare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public String getRepartizae() {
        return repartizare;
    }

    public void setRepartizae(String repartizae) {
        this.repartizare = repartizae;
    }

    @Override
    public String toString() {
        return denumire + " " + repartizare + " " + valoare;
    }
}
