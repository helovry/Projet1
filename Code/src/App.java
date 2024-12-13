import Entité.Ennemie;

public class App{
    public static void main(String[] args) throws Exception {


        
        
        //Carte c1 = new Carte("5-8");
        //c1.afficher();
        Carte c2 = new Carte("10-5");
        c2.afficher();
        //Carte c3 = new Carte("10-10");
        //c3.afficher();



        InterfaceCarte i1 = new InterfaceCarte();
        i1.construitCarte(c2);

        for (Case c : c2.construitChemin())
            System.out.println(c.toString());
        c2.detectionSouris();
        Ennemie e = new Ennemie("Minion");
        e.apparaitre();
        }


}



