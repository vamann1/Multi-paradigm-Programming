package seminar.seminar9.g1061;

import java.util.ArrayList;
import java.util.List;

public class Intrare<T> extends Thread{
    private Muzeu<T> muzeu;
    private int n;
    private T vizitator;
    private Generator<T> generator;

    public Intrare(Muzeu<T> muzeu, int n, Generator<T> generator) {
        this.muzeu = muzeu;
        this.n = n;
        this.generator=generator;
        vizitator= generator.init();
    }


    @Override
    public void run(){
        while(!Main.stop){
            int nrVizitatori = (int)(Math.random()*n)+1;
            List<T> vizitatori = new ArrayList<>();
            for(int i = 0; i< nrVizitatori; i++){
                vizitatori.add(vizitator);
                vizitator = generator.next(vizitator);
            }
            muzeu.intrare(vizitatori);
        }
    }
}
