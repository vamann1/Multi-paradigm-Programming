import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements  Runnable{


    @Override
    public void run() {
        try(Socket socket = new Socket("localhost", 1234)){
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Introduceti numarul apartamentului: ");
            Scanner tastatura = new Scanner(System.in);
            int numar = Integer.parseInt(tastatura.nextLine().trim());

            out.writeObject(numar);
            out.flush();

            int suprafata = (int) in.readObject();
            System.out.println("Suprafata: " + suprafata + " m^2");

        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
