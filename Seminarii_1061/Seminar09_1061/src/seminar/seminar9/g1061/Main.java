package seminar.seminar9.g1061;

public class Main {
    public static boolean stop;

    public static void main(String[] args) {
        try{
            Muzeu<Integer> muzeu = new Muzeu<>(30);
            Generator<Integer> generator =  new Generator<Integer>() {
                @Override
                public Integer init() {
                    return 1;
                }

                @Override
                public Integer next(Integer a) {
                    return a+1;
                }
            };
            Intrare<Integer> intrare= new Intrare<>(muzeu, 5, generator);
            Iesire<Integer> iesire = new Iesire<>(muzeu, 3);
            iesire.start();
            intrare.start();
            Thread.sleep(10);
            stop=true;

        }
        catch(Exception ex) {
            System.err.println(ex);
        }
    }
}
