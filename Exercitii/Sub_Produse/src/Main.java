import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Produs> listProduse = new ArrayList<>();
        List<Tranzactie> listTranzactii = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("produse.txt"));
            while(scanner.hasNextLine()){
                String [] parti = scanner.nextLine().trim().split(",");
                int cod = Integer.parseInt(parti[0].trim());
                String denumire = parti[1];
                double pret = Double.parseDouble(parti[2].trim());

                Produs aux = new Produs(cod, denumire, pret);
                listProduse.add(aux);
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        System.out.println("Exista " + listProduse.size() + " produse.");
        listProduse.sort(Produs::compareTo);
        for(var prod : listProduse){
            System.out.println(prod);
        }

        try(FileReader in = new FileReader("tranzactii.json")){
            JSONTokener tokener = new JSONTokener(in);
            JSONArray jsa = new JSONArray(tokener);

            for(int i = 0; i< jsa.length(); i++){
                JSONObject jso = jsa.getJSONObject(i);
                int cod = jso.getInt("codProdus");
                int cantitate = jso.getInt("cantitate");
                String tip = jso.getString("tip");
                listTranzactii.add(new Tranzactie(cod, cantitate, tip));
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        for(var it : listTranzactii){
            System.out.println(it.getCod_produs()  + " " + it.getCantitate() + " " + it.getTip());
        }

        try(PrintWriter printWriter = new PrintWriter("lista.txt")){
             for(var prod : listProduse){
                 int count  = 0;
                 for(var tranz : listTranzactii)
                 {
                     if(prod.getCod() == tranz.getCod_produs()) {
                         count++;
                         if(tranz.getTip().equals("iesire")){
                             prod.setStoc(-tranz.getCantitate());
                         }
                         else {
                             prod.setStoc(tranz.getCantitate());
                         }
                     }
                 }
                 prod.setNrTranzactii(count);
             }

             listProduse.sort((o1, o2) -> Integer.compare(o2.getNrTranzactii(), o1.getNrTranzactii()));

             printWriter.println("Denumire Produs, Numar tranzactii");
             for(var prod : listProduse){
                 printWriter.println(prod.getDenumire() + " " + prod.getNrTranzactii());
             }
        }
        catch (Exception e){
            System.err.println(e);
        }

        double valoare= 0;
        for(var it : listProduse){
            valoare += (it.getStoc()* it.getPret());
        }
        System.out.println("Valoarea totala: " + valoare);

        try {
            Server server = new Server(listProduse, listTranzactii);
            new Thread(server).start();
            Thread.sleep(500);
            new Thread(new Client()).start();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
