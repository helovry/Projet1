import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Entité.Ennemie;

public class Vague {
    protected List<String> inter;
    protected String nomv;
    protected Path cheminDuFichier;
    protected Map<Double, Ennemie> v;
    protected LocalTime time;

    public Vague(String nomv){
        this.nomv = nomv;
        this.cheminDuFichier=Paths.get("ressources/waves/"+ nomv +".wwe");
        this.inter= new ArrayList<>();
        this.v=new HashMap<>();
        this.time = LocalTime.now();
    }

    public void remplieinter(){
        try (BufferedReader reader = Files.newBufferedReader(cheminDuFichier)) { 
            String line = null; while (((line = reader.readLine()) != null) ) { 
                inter.add(line); 
            } 
        }catch (IOException e) { System.out.println(e); }
    }

    public void creeMap(){
        for(String s : inter){
            String[] parts = s.split("\\|");
            String nom = parts[1];
            Double nombre = Double.parseDouble(parts[0]);//Double.parseDouble -> transforme un String en Double
            Ennemie ennemie = new Ennemie(nom);
            v.put(nombre, ennemie);
        }
    }

    public void faireApparaitre(){
        while(true){
            Duration d = Duration.between(time, LocalTime.now());
            double sec = d.toMillis()/1000.0;// la division sert à transformer les milisecondes en seconde
            if(v.containsKey(sec)){
                Ennemie e = v.get(sec);
                e.apparaitre();
            }

        }
    }
}