public class Rezervare {
    private int idRezervare;
    private int codAventura;
    private int nrLocuriRezervate;

    public Rezervare(int idRezervare, int codAventura, int nrLocuriRezervate) {
        this.idRezervare = idRezervare;
        this.codAventura = codAventura;
        this.nrLocuriRezervate = nrLocuriRezervate;
    }

    public Rezervare() {
    }

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public int getCodAventura() {
        return codAventura;
    }

    public void setCodAventura(int codAventura) {
        this.codAventura = codAventura;
    }

    public int getNrLocuriRezervate() {
        return nrLocuriRezervate;
    }

    public void setNrLocuriRezervate(int nrLocuriRezervate) {
        this.nrLocuriRezervate = nrLocuriRezervate;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "idRezervare=" + idRezervare +
                ", codAventura=" + codAventura +
                ", nrLocuriRezervate=" + nrLocuriRezervate +
                '}';
    }
}
