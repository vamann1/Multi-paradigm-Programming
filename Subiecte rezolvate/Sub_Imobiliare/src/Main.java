import org.sqlite.SQLiteConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

// jdbc:sqlite:/Users/georgevaman/Desktop/agentia.db
public class Main {
    public static void main(String[] args) {
        List<Agent> listAgenti = new ArrayList<>();
        List<Imobil> listImobile = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("agenti.txt"))){
            while(scanner.hasNextLine()){
                String[] parti = scanner.nextLine().trim().split(",");
                int cod = Integer.parseInt(parti[0]);
                String nume = parti[1];
                String telefon = parti[2];
                Agent aux = new Agent(cod, nume, telefon);
                listAgenti.add(aux);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }

        for(var it : listAgenti){
            System.out.println(it);
        }

        String url = "jdbc:sqlite:/Users/georgevaman/Desktop/agentia.db";
        try (Connection conexiune = DriverManager.getConnection(url)) {
                PreparedStatement ps = conexiune.prepareStatement("SELECT * FROM IMOBILE;");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int cod_imob = rs.getInt(1);
                    int cod_ag = rs.getInt(2);
                    String tip = rs.getString(3);
                    double pret = rs.getDouble(4);
                    Imobil aux = new Imobil(cod_imob, pret, cod_ag, tip);
                    listImobile.add(aux);
                }
                rs.close();
                ps.close();
        }
        catch (Exception ex){
            System.err.println(ex);
        }

        for(var it : listAgenti){
            int nr = 0;
            for(var it2 : listImobile){
                if(it2.getCod_agent() == it.getCod()){
                    nr++;
                }
            }
            System.out.println(it.getNume() + " " + nr);
        }

        try (PrintWriter fisier = new PrintWriter("jurnal.txt")){
            for(var it : listAgenti)
            {
                fisier.println(it.getNume());
                fisier.print("apartament: ");
                for(var imobil : listImobile) {
                    if (imobil.getTip_imobil().equals("apartament") && it.getCod() == imobil.getCod_agent()) {
                        fisier.print(imobil.getPret() + ", ");
                    }
                }
                fisier.println();
                fisier.print("casa: ");
                for(var imobil : listImobile) {
                    if (imobil.getTip_imobil().equals("casa") && it.getCod() == imobil.getCod_agent()) {
                        fisier.print(imobil.getPret() + ", ");
                    }
                }
                fisier.println();
                fisier.print("teren: ");
                for(var imobil : listImobile) {
                    if (imobil.getTip_imobil().equals("teren") && it.getCod() == imobil.getCod_agent()) {
                        fisier.print(imobil.getPret() + ", ");
                    }
                }
                fisier.println();
                }
            }
         catch (Exception e) {
            System.err.println(e);
        }

        try {
            Server server = new Server(listImobile, listAgenti);
            new Thread(server).start();
            Thread.sleep(500);
            new Thread(new Client()).start();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}