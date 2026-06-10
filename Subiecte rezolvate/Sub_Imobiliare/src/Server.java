import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements Runnable{
    private List<Agent> listAgenti;
    private List<Imobil> listImobile;

    public Server(List<Imobil> listImobile, List<Agent> listAgenti) {
        this.listImobile = listImobile;
        this.listAgenti = listAgenti;
    }

    @Override
    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(1234)){
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int codAgent = -1;
            int codImobil =Integer.parseInt(in.readLine().trim());
            for(var it : listImobile){
                if(codImobil == it.getCod_imobil()){
                    codAgent = it.getCod_agent();
                }
            }

            for(var it : listAgenti) {
                if(it.getCod() == codAgent)
                    out.println(it);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
