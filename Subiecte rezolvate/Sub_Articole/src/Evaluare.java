import java.io.Serializable;

public class Evaluare implements Serializable {
    private  int codEvaluator;
    private int codArticol;
    private int nivel;
    private int notorietate;
    private int citari;

    public Evaluare(int codEvaluator, int codArticol, int nivel, int notorietate, int citari) {
        this.codEvaluator = codEvaluator;
        this.codArticol = codArticol;
        if(nivel >= 0 && nivel <=10)
            this.nivel = nivel;
        if(notorietate >= 0 && nivel<=10)
            this.notorietate = notorietate;
        if(citari>=0 && citari <=10)
            this.citari = citari;
    }

    public Evaluare() {
    }

    public int getCodEvaluator() {
        return codEvaluator;
    }

    public void setCodEvaluator(int codEvaluator) {
        this.codEvaluator = codEvaluator;
    }

    public int getCodArticol() {
        return codArticol;
    }

    public void setCodArticol(int codArticol) {
        this.codArticol = codArticol;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if(nivel >= 0 && nivel <=10)
            this.nivel = nivel;
    }

    public int getNotorietate() {
        return notorietate;
    }

    public void setNotorietate(int notorietate) {
        if(notorietate >= 0 && nivel<=10)
            this.notorietate = notorietate;
    }

    public int getCitari() {
        return citari;
    }

    public void setCitari(int citari) {
        if(citari>=0 && citari <=10)
            this.citari = citari;
    }

    @Override
    public String toString() {
        return codEvaluator + " " + codArticol + " " + nivel + " " + notorietate + " " + citari;
    }
}
