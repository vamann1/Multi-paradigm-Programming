import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Articol> articole = new ArrayList<>();
        List<Evaluare> evaluari = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File("articole.txt"))){
            while (scanner.hasNextLine()){
                String[] parti = scanner.nextLine().trim().split(",");
                int cod = Integer.parseInt(parti[0]);
                articole.add(new Articol(cod, parti[1], parti[2]));
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        System.out.println("Cerinta 1. Numarul de articole primite: ");
        System.out.println(articole.size());

        try(FileReader fr = new FileReader("evaluari.json")){
            JSONTokener tokener = new JSONTokener(fr);
            JSONArray jsa = new JSONArray(tokener);
            for(int i =0; i<jsa.length(); i++){
                JSONObject jso = jsa.getJSONObject(i);
                int codEv = jso.getInt("Cod evaluator");
                int codArt = jso.getInt("Cod articol");
                int nivel = jso.getInt("Nivel stiintific");
                int notorietate = jso.getInt("Notorietate");
                int citari = jso.getInt("Citari");
                evaluari.add(new Evaluare(codEv, codArt, nivel, notorietate, citari));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        Map<Integer, Long> cerinta2 = evaluari.stream().collect(Collectors.groupingBy(Evaluare::getCodArticol, Collectors.counting()));
        System.out.println("Cerinta 2. Numarul de evaluari pe fiecare articol: ");
        for(var it : cerinta2.keySet()){
            System.out.println(it + " -> " + cerinta2.get(it));
        }


        System.out.println("Cerinta 3. Salvare in fisier.");
        try(PrintWriter pw = new PrintWriter("jurnal.txt")){
            Map<Integer, Double> mapMedie = evaluari.stream().collect(Collectors.
                    groupingBy(Evaluare::getCodArticol, Collectors.averagingDouble(((o1) -> o1.getNivel()+o1.getNotorietate()+o1.getCitari()))));

            articole.stream()
                    .filter(a -> mapMedie.containsKey(a.getCod()))
                    .sorted(Comparator.comparingDouble((Articol a) -> mapMedie.get(a.getCod())).reversed())
                    .forEach(a -> pw.println(a.getCod() + " " + a.getNumeAutor() + " " + mapMedie.get(a.getCod())));

        }catch (Exception e){
            System.err.println(e);
        }

        try {
            System.out.println("Cerinta 4. Server TCP/IP");
            Server server = new Server(evaluari);
            new Thread(server).start();
            Thread.sleep(500);
            new Thread(new Client()).start();
        }
        catch (Exception e){
            System.err.println(e);
        }

    }
}
