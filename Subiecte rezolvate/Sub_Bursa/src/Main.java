import org.sqlite.SQLiteConnection;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Persoana> listPersoane = new ArrayList<>();
        List<Tranzactie> listTranzactii = new ArrayList<>();

        try(Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/georgevaman/Desktop/JAVA/Subiecte rezolvate/Sub_Bursa/date/bursa.db"))
        {
            DatabaseMetaData metaData = con.getMetaData();
            System.out.println(metaData.getURL());
            try(Statement s = con.createStatement()){
                ResultSet rs = s.executeQuery("SELECT * FROM Persoane;");
                while(rs.next()){
                    Persoana p = new Persoana();
                    p.setCod(rs.getInt(1));
                    p.setCNP(rs.getString(2));
                    p.setNume(rs.getString(3));
                    listPersoane.add(p);
                }
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        System.out.println();
        System.out.print("Cerinta 1: Numarul clientilor nerezidenti: ");
        int count = 0;
        for(var it : listPersoane){
            if(it.getCNP().charAt(0) == '8' || it.getCNP().charAt(0) == '9'){
                count++;
            }
        }
        System.out.println(count);

        try(Scanner scanner = new Scanner(new File("date/bursa_tranzactii.txt"))){
            while(scanner.hasNextLine())
            {
                String[] parti = scanner.nextLine().trim().split(",");
                int cod = Integer.parseInt(parti[0]);
                String simbol = parti[1];
                String tip = parti[2];
                int cantitate = Integer.parseInt(parti[3]);
                float pret = Float.parseFloat(parti[4]);
                Tranzactie tranz = new Tranzactie(cod, simbol, tip, cantitate, pret);
                listTranzactii.add(tranz);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        Map<String, Integer> map = new HashMap<>();
        for(var it : listTranzactii){
            map.put(it.getSimbol(), map.getOrDefault(it.getSimbol(), 0)+1);
        }

        System.out.println("Cerinta 2: Numarul de tranzactii: ");
        for(var it : map.keySet()){
            System.out.println(it + " -> " + map.get(it) + " tranzactii");
        }


        System.out.println("Cerinta 3: Simboluri scrise in fisier");
        try(OutputStream out = new FileOutputStream(new File("date/simboluri.txt"));
            PrintWriter pw = new PrintWriter(out)) {

            for (var it : Simboluri.values()) {
                if (map.getOrDefault(it.toString(), -1) != -1) {
                    pw.println(it);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("Cerinta 4: Portofolii clienti");

        for(var client : listPersoane)
        {
            System.out.println(client.getNume());
            Map<String, Integer> portfPeSimbol = new HashMap<>();
            for(var tranz : listTranzactii){
                if(client.getCod() == tranz.getCod()){
                    if(tranz.getTip().equals("cumparare")) {
                        portfPeSimbol.put(tranz.getSimbol(), portfPeSimbol.getOrDefault(tranz.getSimbol(), 0) + tranz.getCantitate());
                    }
                    else {
                        portfPeSimbol.put(tranz.getSimbol(), portfPeSimbol.getOrDefault(tranz.getSimbol(), 0) - tranz.getCantitate());
                    }
                }
            }
            for(var it : portfPeSimbol.keySet()){
                System.out.println("    " + it + " - " + portfPeSimbol.get(it));
            }
        }


    }
}
