package Entité;

public class Tour  extends Entité{
    protected int cost;
    //position??

    public Tour(String nom){
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void attaque(int ATK, double ATKSpeed, double range);
    
}
