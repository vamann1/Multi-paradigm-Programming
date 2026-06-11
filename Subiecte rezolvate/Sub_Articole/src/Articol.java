public class Articol {
    private int cod;
    private String numeAutor;
    private String institutie;

    public Articol(int cod, String numeAutor, String institutie) {
        this.cod = cod;
        this.numeAutor = numeAutor;
        this.institutie = institutie;
    }

    public Articol() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNumeAutor() {
        return numeAutor;
    }

    public void setNumeAutor(String numeAutor) {
        this.numeAutor = numeAutor;
    }

    public String getInstitutie() {
        return institutie;
    }

    public void setInstitutie(String institutie) {
        this.institutie = institutie;
    }

    @Override
    public String toString() {
        return cod + " " + numeAutor + " " + institutie;
    }
 }
