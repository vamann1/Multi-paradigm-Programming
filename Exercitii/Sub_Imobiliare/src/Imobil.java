public class Imobil {
    private int cod_imobil;
    private int cod_agent;
    private String tip_imobil;
    private double pret;

    public Imobil(int cod_imobil, double pret, int cod_agent, String tip_imobil) {
        this.cod_imobil = cod_imobil;
        this.pret = pret;
        this.cod_agent = cod_agent;
        this.tip_imobil = tip_imobil;
    }

    public int getCod_imobil() {
        return cod_imobil;
    }

    public void setCod_imobil(int cod_imobil) {
        this.cod_imobil = cod_imobil;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getTip_imobil() {
        return tip_imobil;
    }

    public void setTip_imobil(String tip_imobil) {
        this.tip_imobil = tip_imobil;
    }

    public int getCod_agent() {
        return cod_agent;
    }

    public void setCod_agent(int cod_agent) {
        this.cod_agent = cod_agent;
    }

    @Override
    public String toString() {
        return "Cod imobil: " + cod_imobil + " Pret: " + pret + " Tip imobil: " + tip_imobil;
    }
}
