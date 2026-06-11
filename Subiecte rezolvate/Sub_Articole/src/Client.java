import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client implements Runnable{
    @Override
    public void run() {
        try(Socket socket = new Socket("localhost", 1234)){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Scanner tastatura = new Scanner(System.in);
            System.out.println("Introduceti numarul articolului: ");
            int nrArt = Integer.parseInt(tastatura.nextLine().trim());

            out.writeObject(nrArt);
            out.flush();

            List<Evaluare> lista = new ArrayList<>((List<Evaluare>) in.readObject());
            System.out.println("Lista de evaluari primita: ");
            for(var it : lista){
                System.out.println(it);
            }
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
