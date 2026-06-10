import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable, Closeable {
    private boolean close = false;

    public Client() {
    }

    @Override
    public void run() {
        while(!close) {
            try (Socket socket = new Socket("localhost", 1234)) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                Scanner tastatura = new Scanner(System.in);
                System.out.println("Introduce denumirea unei aventuri/Introduce close pentru a inchide: ");
                String denumire = tastatura.nextLine().trim();


                if(denumire.trim().equals("close")){
                    out.writeObject(denumire);
                    close();
                }
                else {
                    out.writeObject(denumire);
                    System.out.println("Locuri disponibile pentru aventura " + denumire + ": ");
                    int locuri = (int) in.readObject();
                    System.out.println(locuri);
                }
                out.flush();
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }

    @Override
    public void close() throws IOException {
        close = true;
    }
}
