import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Factura> listFacturi = new ArrayList<>();
        List<Apartament> listApartamente = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File("date/S27_intretinere_facturi.txt"))){
            while(scanner.hasNextLine())
            {
                String[] parti = scanner.nextLine().trim().split(",");
                double valoare = Double.parseDouble(parti[2]);
                Factura factura = new Factura(parti[0], parti[1], valoare);
                listFacturi.add(factura);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("Cerinta 1. Factura cu valoare maxima: ");
        listFacturi.sort(Comparator.comparingDouble(Factura::getValoare));
        System.out.println(listFacturi.getLast().getDenumire() + " " + listFacturi.getLast().getValoare());

        try {
            Class.forName("org.sqlite.JDBC");
            String path = new java.io.File("date/S27_intretinere.db").getAbsolutePath();
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + path); Statement s = con.createStatement())
            {
                ResultSet rs = s.executeQuery("select * from Apartamente;");
                    while(rs.next()){
                        int nrAp = rs.getInt(1);
                        int supr = rs.getInt(2);
                        int nrPers = rs.getInt(3);
                      Apartament apt = new Apartament(nrAp, supr, nrPers);
                      listApartamente.add(apt);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        System.out.println("Cerinta 2. Lista apartamentelor cu cel putin 2 persoane, sortate descrescator dupa nr. apt");
        List<Apartament> cerinta2 = listApartamente.stream().filter(apartament -> apartament.getNrPersoane()>=2)
                .sorted(Comparator.comparingInt(Apartament::getNrApartament).reversed()).toList();
        for(var it : cerinta2){
            System.out.println(it);
        }


        Map<String, Double> map = new HashMap<>();
        for(var it : listFacturi){
            map.put(it.getRepartizae(), map.getOrDefault(it.getRepartizae(), 0.0)+it.getValoare());
        }

        System.out.println("Cerinta 3. Summary in fisier text.");
        try(PrintWriter out = new PrintWriter(new File("date/sumar.txt"))){
            for(var it : map.keySet()){
                int count = 0;
                for(var it2 : listFacturi){
                    if(it.equals(it2.getRepartizae())) {
                        count++;
                    }
                }
                out.println(it + ", " + map.get(it) + ", " + count);
            }

        } catch (Exception e) {
            System.err.println(e);
        }


        System.out.println("Cerinta 4. Server TCP/IP");
        try {
            Server server = new Server(listApartamente);
            new Thread(server).start();
            Thread.sleep(500);
            Client client =new Client();
            client.run();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
