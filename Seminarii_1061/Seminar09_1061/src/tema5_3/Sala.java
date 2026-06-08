package tema5_3;

public class Sala {
    private String[][] sala;
    private int locuriLibere;

    public Sala(int[] l){
        sala = new String[l.length][];
        for(int i=0; i< sala.length; i++){
            sala[i] = new String[l[i]];
            locuriLibere+=l[i];
        }
    }

    public synchronized void rezervare(String alias, int nrLocuri){
        if(locuriLibere < nrLocuri)
            return;
        for(int i=0; i< sala.length; i++){
            if(sala[i]==null) {
                sala[i] = new String[]{alias};
                locuriLibere--;
            }
        }
    }
}
