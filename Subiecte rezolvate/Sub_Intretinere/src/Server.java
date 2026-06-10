import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements Runnable{
    List<Apartament> listApartamente;

    public Server(List<Apartament> listApartamente) {
        this.listApartamente = listApartamente;
    }


    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(1234)){
            Socket socket = serverSocket.accept();

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            int nr = (int) in.readObject();
            for(var it : listApartamente){
                if(it.getNrApartament() == nr){
                    out.writeObject(it.getSuprafata());
                }
            }
            out.flush();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
