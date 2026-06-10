import java.util.Arrays;

public class Aventura implements Comparable<Aventura> {
    private int cod;
    private String denumire;
    private double tarif;
    private int locuriDisponibile;

    public Aventura(int cod, String denumire, double tarif, int locuriDisponibile) {
        this.cod = cod;
        this.tarif = tarif;
        this.denumire = denumire;
        this.locuriDisponibile = locuriDisponibile;
    }

    public Aventura() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getLocuriDisponibile() {
        return locuriDisponibile;
    }

    public void setLocuriDisponibile(int locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }

    @Override
    public String toString() {
        return  cod + " " + denumire + " " + locuriDisponibile;
    }

    @Override
    public int compareTo(Aventura o) {
        return this.getDenumire().compareTo(o.getDenumire());
    }
}
