public class Cafe {
    
    private String nombre; //Atributos
    private int gCafe;
    private int gCacao;
    private int gLeche;
    public  double precio;

<<<<<<< HEAD
    public Cafe (String nombre, int gCafe, int gCacao, int gLeche, double precio){
=======
    //Creamos el constructor

    public Cafe (String nombre, int gCafe, int gCacao, int gLeche){
>>>>>>> ff96161ba902cd0203d3d5eba9fc240e328e3723
        this.nombre = nombre;
        this.gCafe = gCafe;
        this.gCacao = gCacao;
        this.gLeche = gLeche;
        this.precio = precio;
    }

    //creamos los getters

    public String getNombre() { return nombre; }
    public int getGCafe(){ return gCafe; }
    public int getGCacao(){ return gCacao; }
    public int getGLeche(){ return gLeche; }
    public double getPrecio(){return precio;}
    

    //Creamos la línea de string que irá en el txt

    public String formatToFile(){
        return nombre + ", " + gCafe + ", " + gCacao + ", " + gLeche + ",  " + precio ;
    } 
    
}
