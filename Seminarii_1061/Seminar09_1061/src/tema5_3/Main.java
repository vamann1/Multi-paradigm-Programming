package tema5_3;

public class Main {
    public static void main(String[] args) {
        try{
            Sala sala = new Sala(new int[] {10, 11, 12, 9, 8, 7, 3});

        }
        catch(Exception ex){
            System.err.println(ex);
        }
    }
}
