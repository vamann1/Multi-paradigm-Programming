import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements  Runnable {
    List<Evaluare> evaluari;

    public Server(List<Evaluare> evaluari) {
        this.evaluari = evaluari;
    }


    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(1234)){
            Socket socket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            int codArt = (int) in.readObject();
            List<Evaluare> evaluariFiltrate = evaluari.stream().filter((o1)-> o1.getCodArticol() == codArt).toList();
            out.writeObject(evaluariFiltrate);
            out.flush();

        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
