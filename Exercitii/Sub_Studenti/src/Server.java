import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements Runnable {
    private List<Student> listStudenti;
    private  List<Nota> listaNote;

    public Server(List<Student> listStudenti, List<Nota> listaNote) {
        this.listStudenti = listStudenti;
        this.listaNote = listaNote;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(1234)){
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String[] studentMaterie = in.readLine().trim().split(",");
            int id = Integer.parseInt(studentMaterie[0].trim());
            Discipline disciplina = Discipline.valueOf(studentMaterie[1].trim());

            double nota = -1;
            for(var it : listaNote){
                if(it.getDisciplina() == disciplina && it.getId() == id)
                {
                        nota = it.getNota();
                }
            }
            out.println(nota);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

}
