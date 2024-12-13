package Entité;
enum Elements{None, Wind, Water, Earth, Fire}

public abstract class Entité {//Pas sur de cette class :(
    protected String nom;
    protected int PV;
    protected int ATK;
    protected double ATKSpeed;
    protected double range;
    protected Elements el;

    /*public Entité(String nom, int PV, int ATK, double ATKSpeed, double range, Elements el){
        this.nom=nom;
        this.PV=PV;
        this.ATK=ATK;
        this.ATKSpeed=ATKSpeed;
        this.range=range;
        this.el=el;
    }*/

    
    public int getPV() {
        return PV;
    }

    public int getATK() {
        return ATK;
    }

    public double getATKSpeed() {
        return ATKSpeed;
    }

    public double getRange() {
        return range;
    }

    public Elements getEl() {
        return el;
    }

    public void setPV(int pV) {
        PV = pV;
    }

    public void setATK(int aTK) {
        ATK = aTK;
    }

    public void setATKSpeed(double aTKSpeed) {
        ATKSpeed = aTKSpeed;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setEl(Elements el) {
        this.el = el;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }  
    
    //public abstract void attaque();
}
