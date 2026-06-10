import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Aventura> listAventuri = new ArrayList<>();
        List<Rezervare> listRezervari = new ArrayList<>();

        try(FileReader fr = new FileReader("aventuri.json")){
            JSONTokener tokener = new JSONTokener(fr);
            JSONArray jsa = new JSONArray(tokener);
            for(int i =0; i< jsa.length(); i++){
                JSONObject jso = jsa.getJSONObject(i);
                Aventura aventura = new Aventura(
                        jso.getInt("cod_aventura"),
                        jso.getString("denumire"),
                        jso.getDouble("tarif"),
                        jso.getInt("locuri_disponibile")
                );
                listAventuri.add(aventura);
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        List<Aventura> cerinta1 = listAventuri.stream().filter(new Predicate<Aventura>() {
            @Override
            public boolean test(Aventura aventura) {
                return aventura.getLocuriDisponibile()>=20;
            }
        }).toList();
        System.out.println("Lista aventurilor filtrata:");
        cerinta1.forEach(System.out::println);

        try(Scanner scanner = new Scanner(new File("rezervari.txt"))){
            while(scanner.hasNextLine()){
                String[] parti = scanner.nextLine().trim().split(",");
                Rezervare rezervare = new Rezervare(
                        Integer.parseInt(parti[0]),
                        Integer.parseInt(parti[1]),
                        Integer.parseInt(parti[2])
                );
                listRezervari.add(rezervare);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

//       List<Aventura> cerinta2 = new ArrayList<>();
//        for(var it : listAventuri){
//            int rezTemp = it.getLocuriDisponibile();
//            for (var it2 : listRezervari){
//                if(it.getCod() == it2.getCodAventura()){
//                    rezTemp -= it2.getNrLocuriRezervate();
//                }
//            }
//            if(rezTemp >= 5 ){
//                cerinta2.add(it);
//            }
//        }

        Map<Integer, Integer> map = new HashMap<>();
        for(var it : listRezervari){
            map.put(it.getCodAventura(), map.getOrDefault(it.getCodAventura(), 0)+it.getNrLocuriRezervate());
        }

        List<Aventura> cerinta2 = new ArrayList<>();
        for(var it : listAventuri){
            cerinta2.add(new Aventura(it.getCod(), it.getDenumire(), it.getTarif(), it.getLocuriDisponibile()));
        }

         cerinta2 = cerinta2.stream().filter(new Predicate<Aventura>() {
            @Override
            public boolean test(Aventura aventura) {
                return aventura.getLocuriDisponibile()-map.getOrDefault(aventura.getCod(), 0) >= 5;
            }
        }).toList();

        for(var it : cerinta2){
            it.setLocuriDisponibile(it.getLocuriDisponibile() - map.get(it.getCod()));
        }

        System.out.println("Lista aventurilor cu cel putin 5 locuri ramase: ");
        cerinta2.forEach(System.out::println);

        System.out.println("!SCRIERE IN FISIER!");
        try(PrintWriter fisier = new PrintWriter("venituri.txt")){
            fisier.println("Denumire aventura, Numar rezervari, Valoare totala");
            List<Aventura> cerinta3 = listAventuri.stream().sorted().toList();
            for(var it : cerinta3){
                fisier.println(it.getDenumire() + ", " +  map.get(it.getCod()) + ", " + it.getTarif()*map.get(it.getCod()));
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        try {
            Server server = new Server(listAventuri);
            Client client = new Client();
            new Thread(server).start();
            Thread.sleep(500);
            new Thread(client).start();
        }
        catch (Exception e){
            System.err.println(e);
        }

    }
}
