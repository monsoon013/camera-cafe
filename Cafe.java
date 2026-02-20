public class Cafe {
    
    private String nombre; //Atributos
    private int gCafe;
    private int gCacao;
    private int gLeche;
    private int gCanela;
    private double precio;

    //Creamos el constructor

    public Cafe (String nombre, int gCafe, int gCacao, int gLeche, int gCanela, double precio){
        this.nombre = nombre;
        this.gCafe = gCafe;
        this.gCacao = gCacao;
        this.gLeche = gLeche;
        this.gCanela = gCanela;
        this.precio = precio;
    }

    //creamos los getters

    public String getNombre() { return nombre; }
    public int getGCafe(){ return gCafe; }
    public int getGCacao(){ return gCacao; }
    public int getGLeche(){ return gLeche; }
    public int getGCanela(){ return gCanela;}
    public double getPrecio(){ return precio;}

    //Creamos la línea de string que irá en el txt

    public String formatToFile(){
        return nombre + ", " + gCafe + ", " + gCacao + ", " + gLeche + ", " + gCanela + ", " + precio;
    } 
    
}
