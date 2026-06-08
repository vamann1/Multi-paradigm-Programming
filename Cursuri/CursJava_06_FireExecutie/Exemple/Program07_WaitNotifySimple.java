class AplicatieNumarSleep {
    private volatile boolean avemNumar;
    private volatile int numar;

    public void citire() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }
        numar = (int) (Math.random() * 3000);
        avemNumar = true;
    }

    public void afisare() {
        while (!avemNumar) {
            System.out.println("Asteptam un numar...");
        }
        System.out.println("Numărul este " + numar);
    }
}

class AplicatieNumarWait {
    private volatile boolean avemNumar;
    private volatile int numar;

    public synchronized void citire() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }
        numar = (int) (Math.random() * 3000);
        avemNumar = true;
        notifyAll();
    }

    public synchronized void afisare() {
        while (!avemNumar) {
            System.out.println("Asteptam un numar...");
            try { wait(); } catch (InterruptedException e) { }
        }
        System.out.println("Numărul este " + numar);
    }
}

public class Program07_WaitNotifySimple {
    public static void main(String[] args) {

        // Varianta 1: Folosind o buclă simplă
        //var app = new AplicatieNumarSleep();

        // Varianta 2: Folosind o buclă cu wait() și notifyAll()
        var app = new AplicatieNumarWait();

        // Fir de execuție care așteaptă numărul pentru a-l afișa
        new Thread(() -> {
            app.afisare();
        }).start();

        // Fir de execuție care generează numărul de afișat
        new Thread(() -> {
            app.citire();
        }).start();
    }
}
