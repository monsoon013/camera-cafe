public class Cafe {
    
    private String nombre;
    private int gCafe;
    private int gCacao;
    private int gLeche;
    public  double precio;

    public Cafe (String nombre, int gCafe, int gCacao, int gLeche, double precio){
        this.nombre = nombre;
        this.gCafe = gCafe;
        this.gCacao = gCacao;
        this.gLeche = gLeche;
        this.precio = precio;
    }

    //getters

    public String getNombre() { return nombre; }
    public int getGCafe(){ return gCafe; }
    public int getGCacao(){ return gCacao; }
    public int getGLeche(){ return gLeche; }
    public double getPrecio(){return precio;}
    

    

    public String formatToFile(){
        return nombre + ", " + gCafe + ", " + gCacao + ", " + gLeche + ",  " + precio ;
    } 
    
}
