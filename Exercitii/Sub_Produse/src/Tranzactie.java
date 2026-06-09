public class Tranzactie{
    private int cod_produs;
    private int cantitate;
    private String tip;

    public Tranzactie(int cod_produs, int cantitate, String tip) {
        this.cod_produs = cod_produs;
        this.cantitate = cantitate;
        this.tip = tip;
    }

    public int getCod_produs() {
        return cod_produs;
    }

    public void setCod_produs(int cod_produs) {
        this.cod_produs = cod_produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
