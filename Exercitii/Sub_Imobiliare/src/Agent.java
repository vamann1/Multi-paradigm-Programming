public class Agent {

    private int cod;
    private String nume;
    private String telefon;

    public Agent(int cod, String nume, String telefon) {
        this.cod = cod;
        this.nume = nume;
        this.telefon = telefon;
    }

    public int getCod() {
        return cod;
    }

    public String getNume() {
        return nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Cod: " + cod + " Nume: " + nume + " Telefon: " + telefon;
    }
}
