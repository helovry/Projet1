public class Case {
    private double x; // Coordonnée X
    private double y; // Coordonnée Y
    private char lettre; // Lettre associée

    public Case(double x, double y, char lettre) {
        this.x = x;
        this.y = y;
        this.lettre = lettre;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public char getLettre() {
        return lettre;
    }

    public boolean contains(double mouseX, double mouseY, double tailleCase) {
        return mouseX >= x - tailleCase / 2 && mouseX <= x + tailleCase / 2 && mouseY >= y - tailleCase / 2 && mouseY <= y + tailleCase / 2;
    }

    public String toString(){
        return "Case [X = " + x + "Y = " + y + "lettre = " + lettre + "]";
    }
}