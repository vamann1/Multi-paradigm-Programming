
public class Produs implements Comparable<Produs>{
    private int cod;
    private String denumire;
    private double pret;
    private int nrTranzactii = 0;
    private int stoc;

    public Produs(int cod, String denumire, double pret) {
        this.cod = cod;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public int compareTo(Produs prod) {
        return this.denumire.compareTo(prod.getDenumire());
    }

    @Override
    public String toString() {
        return "Cod: " + cod + " Denumire: " + denumire + " Pret: " + pret;
    }

    public int getNrTranzactii() {
        return nrTranzactii;
    }

    public void setNrTranzactii(int nrTranzactii) {
        this.nrTranzactii = nrTranzactii;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc += stoc;
    }
}

