package polinomiosap3;

public class Nodo {
    //Atributos
    private int C, E;
    private Nodo Liga;

    public Nodo() 
    {
    }
 
    public Nodo(int C, int E) 
    {
        this.C = C;
        this.E = E;
        this.Liga = null;
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getE() {
        return E;
    }

    public void setE(int E) {
        this.E = E;
    }

    public Nodo getLiga() {
        return Liga;
    }

    public void setLiga(Nodo Liga) {
        this.Liga = Liga;
    }
}
