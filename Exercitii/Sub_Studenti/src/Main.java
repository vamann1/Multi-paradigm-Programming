import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> listStudenti = new ArrayList<>();
        List<Nota> listNote = new ArrayList<>();

        try(FileReader stream = new FileReader("S11_studenti.json"))
        {
            JSONTokener tokener = new JSONTokener(stream);
            JSONArray jsa = new JSONArray(tokener);
            for(int i = 0; i<jsa.length(); i++){
                JSONObject jso = jsa.getJSONObject(i);
                int id = jso.getInt("IdStudent");
                String nume = jso.getString("Nume");
                String  prenume = jso.getString("Prenume");
                listStudenti.add(new Student(id, nume, prenume));
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

        System.out.println("Numar total de studenti: " + listStudenti.size());

        try(Scanner scanner = new Scanner(new File("S11_note.txt"))){
            while(scanner.hasNextLine()){
                String[] parti = scanner.nextLine().trim().split(",");
                int id = Integer.parseInt(parti[0]);
                Discipline disciplina = Discipline.valueOf(parti[1]);
                double nota = Double.parseDouble(parti[2]);
                listNote.add(new Nota(id, disciplina, nota));
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("Lista discipline: ");
        for(var it : Discipline.values()){
            System.out.println();
            System.out.print(it.toString() + " ");
            int count = 0;
            for(var nota : listNote){
                if(it == nota.getDisciplina()){
                    count++;
                }
            }
            System.out.print(count + " note");
        }
        System.out.println();

        Scanner tastatura = new Scanner(System.in);
        System.out.println("Scrieti disciplina: ");
        String linie = tastatura.nextLine().trim();
        Discipline disc = Discipline.valueOf(linie);
        System.out.println("Nume Student, Prenume Student, Nota");
        for(var it : listNote){
            if(it.getDisciplina() == disc){
                for(var stud : listStudenti){
                    if(it.getId() == stud.getId()){
                        System.out.println(stud + " " + it.getNota());
                    }
                }
            }
        }

        try {
            Server server = new Server(listStudenti, listNote);
            new Thread(server).start();
            Thread.sleep(500);
            new Thread(new Client()).start();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
