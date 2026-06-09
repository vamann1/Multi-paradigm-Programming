import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    @Override
    public void run() {
        try(Socket socket = new Socket("localhost", 1234)){
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner tastatura = new Scanner(System.in);
            System.out.println("Scrieti pereche id student - materie separata prin ,");
            String studentMaterie = tastatura.nextLine().trim();

            out.println(studentMaterie);

            double nota = Double.parseDouble(in.readLine());
            System.out.println("Nota: " + nota);

        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
