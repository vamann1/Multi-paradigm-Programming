public class Nota {
    private int id;
    private Discipline disciplina;
    private double nota;

    public Nota(int id, Discipline disciplina, double nota) {
        this.id = id;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discipline getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Discipline disciplina) {
        this.disciplina = disciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
