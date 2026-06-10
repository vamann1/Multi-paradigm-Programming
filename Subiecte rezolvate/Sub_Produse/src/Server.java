import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable{
    List<Produs> listProduse;
    List<Tranzactie> listTranzactii;

    public Server(List<Produs> listProduse, List<Tranzactie> listTranzactii) {
        this.listProduse = listProduse;
        this.listTranzactii = listTranzactii;
    }


    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(1234)){
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int cod = Integer.parseInt(in.readLine().trim());

            for(var it : listProduse) {
                if(cod == it.getCod()) {
                    out.println(it.getStoc());
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
