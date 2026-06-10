public class Persoana {
    private int cod;
    private String CNP;
    private String nume;

    public Persoana(int cod, String CNP, String nume) {
        this.cod = cod;
        this.CNP = CNP;
        this.nume = nume;
    }

    public Persoana() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return cod + " " + CNP + " " + nume;
    }
}
