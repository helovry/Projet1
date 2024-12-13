package Entité;

import libraries.StdDraw;
import java.awt.Color;

public class Ennemie extends Entité{//TODO : methodes attaque + sedeplacer
    protected double speed;
    protected int reward;
    protected double time;

    public Ennemie(String nom){
       this.nom=nom;
       if(nom.equals("Minion")){
        this.PV=10;
        this.ATK=3;
        this.ATKSpeed=0;
        this.el=el.None;
        this.range=0;
        this.speed=1;
        this.reward=1;
       }else if(nom.equals("Wind Grognard")){
        this.PV=1;
        this.ATK=7;
        this.ATKSpeed=2;
        this.el=el.Wind;
        this.range=5;
        this.speed=2;
        this.reward=1;
       }else if(nom.equals("Fire Grognard")){
        this.PV=1;
        this.ATK=7;
        this.ATKSpeed=2;
        this.el=el.Fire;
        this.range=3;
        this.speed=2;
        this.reward=1;
       }else if(nom.equals("Water Brute")){
        this.PV=30;
        this.ATK=5;
        this.ATKSpeed=1;
        this.el=el.Water;
        this.range=3;
        this.speed=1;
        this.reward=3;
       }else if(nom.equals("Earth Brute")){
        this.PV=30;
        this.ATK=5;
        this.ATKSpeed=1;
        this.el=el.Water;
        this.range=3;
        this.speed=1;
        this.reward=3;
       }else{//Boss
        this.PV=150;
        this.ATK=100;
        this.ATKSpeed=10.0;
        this.el=el.Fire;
        this.range=2.0;
        this.speed=0.5;
        this.reward=100;
       }
       
    }

    public double getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
    }


    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    //public void attaque(int ATK, double ATKSpeed, double range);//a faire
    
    
    
    public void sedepacer(double speed){
        
    }

    public void apparaitre() {
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledCircle(105.0, 420.0, 10.0); // Dessiner un cercle pour représenter l'ennemi au coordonée de la case de départ
    }

}
