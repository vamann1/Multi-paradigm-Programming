import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements Runnable, Closeable {
    private List<Aventura> listAventuri;
    private boolean close = false;

    public Server(List<Aventura> listAventuri) {
        this.listAventuri = listAventuri;
    }


    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(1234)){
            while(!close)
            {
                Socket socket = serverSocket.accept();
                new Thread(()->proceseazaa(socket)).start();
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    private void proceseazaa(Socket socket){
        try(socket){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String denumire = in.readObject().toString();
            if (denumire.equals("close")) {
                close();
            } else {
                for (var it : listAventuri) {
                    if (it.getDenumire().equals(denumire)) {
                        System.out.println("Denumire primita: " + denumire);
                        out.writeObject(it.getLocuriDisponibile());
                    }
                }

            }
            out.flush();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    @Override
    public void close() throws IOException {
        close = true;
    }
}
