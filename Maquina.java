import java.io.*;
import java.util.ArrayList;


public class Maquina {

    private String numeroSerie;
    private ArrayList<Cafe> cafes; 
    private int vasos;
    private int gCafe;
    private int gLeche;
    private int gCacao;
    private int mlAgua;
    private int gCanela;
    private int historial;
    private double recaudacionTotal;
    private String nombreFich = "historial_cafe.txt";

    private final int MAX_CAP = 1000; //constante con la capacidad máxima de cada cosa
    private final int MAX_CAF_DIA = 10;

    public Maquina (String numeroSerie){ //constructor solo con el número de serie
        this.numeroSerie = numeroSerie;
        this.cafes = new ArrayList<>();
        this.vasos = 5;
        this.gCafe = 200;
        this.gCacao = 100;
        this.gLeche = 600;
        this.mlAgua = 800;
        this.gCanela = 500;
        this.historial = 0;
        this.recaudacionTotal = 0;
    }

    public void agregarReceta(Cafe c){
        cafes.add(c);
    }

    //getter
    public ArrayList<Cafe> getCafes(){
        return cafes;
    }

    public int getHistorial(){
        return historial;
    }

    public double getRecaudacionTotal(){
        return recaudacionTotal;
    }

    public void pedirCafe (int indice){
        if(indice < 0 || indice >= cafes.size()){
            System.out.println("No existe el café seleccionado"); 
            return;
        }

        String limiteCafes = comprobarCafes(historial);
        if(limiteCafes != null){
            System.out.println("No puedes pedir más cafés. Vuelve mañana :(");
            return;
        }
        Cafe c = cafes.get(indice);
        int aguaNecesaria = c.getGCafe() + c.getGCacao() + c.getGLeche() + c.getGCanela(); //Fórmula para conseguir el agua necesaria

        String error = comprobarStock(c, aguaNecesaria); //mandar el café pedido y el agua que necesite
        if(error != null){
            System.out.println("No se puede servir :( | " + error); //En caso de que falte algo, mostrar que no se puede servir
            return;
        }      

        System.out.println("Su café está siendo preparado...");
        añadirHistorial(c);
        consumirIngredientes(c, aguaNecesaria);  
        historial++;    
        recaudacionTotal += c.getPrecio();       
    }

    public String comprobarCafes (int cafesPedidos) {
        if(cafesPedidos == MAX_CAF_DIA){ 
            return "No puedes pedir más cafés por hoy.";
        }
        return null;
    }

    public String comprobarStock(Cafe c, int agua){
        if(vasos <= 0){ return "No quedan vasos.";}
        if(c.getGCafe() > gCafe) { return "No queda café.";}
        if(c.getGCacao() > gCacao) { return "No queda cacao.";}
        if(c.getGLeche() > gLeche) { return "No queda leche.";}
        if(c.getGCanela() > gCanela) { return "No queda canela.";}
        if(agua > mlAgua){ return "No queda agua.";}

        return null;
    }

    private void consumirIngredientes(Cafe c, int agua){ //Pasar el café y el agua necesaria obtenida en pedirCafe
        this.vasos--; //resta el vaso
        this.gCafe -= c.getGCafe(); //resta los gramos que necesite el café
        this.gCacao -= c.getGCacao();
        this.gLeche -= c.getGLeche();
        this.gCanela -= c.getGCanela();
        this.mlAgua -= agua; //resta el agua
    }

    public void rellenarIngredientes(int op, int cantidad){
        if (cantidad <= 0 ){
            //Así como el return actúa más para fallos de flujo/programación, IllegalArgumentException funciona más para errores que cometa el usuario al usar el programa.
            throw new IllegalArgumentException("Error al añadir. La cantidad tiene que ser positiva"); 
        }

        switch(op){
            case 1:{
                verificarEspacio(cantidad, gCafe, MAX_CAP, "café"); //llamar al método para poder comprobar que se puede realizar
                gCafe += cantidad; //En caso de que sí se pueda, pues sumar la cantidad que ya hay con la cantidad a añadir
                break;
            }
            case 2:{
                verificarEspacio(cantidad, gCacao, MAX_CAP, "cacao");
                gCacao += cantidad;
                break;
            }
            case 3: {
                verificarEspacio(cantidad, gLeche, MAX_CAP, "leche");
                gLeche += cantidad;
                break;
            }
            case 4: {
                verificarEspacio(cantidad, mlAgua, MAX_CAP, "agua");
                mlAgua += cantidad;
                break;
            }
            case 5: {
                verificarEspacio(cantidad, vasos, 100, "vasos"); //el máximo de vasos que sea 100
                vasos += cantidad;
                break;
            }
            case 6: {
                verificarEspacio(cantidad, gCanela, MAX_CAP, "canela");
                gCanela += cantidad;
                break;
            }
            default: {
                throw new IllegalArgumentException("Opción no válida");
            }
        }
    }
    
    //Evitar ifs reiterativos para comprobar la capacidad
    private void verificarEspacio(int cant_Act, int cant_Add, int max, String nombre){
        if(cant_Act + cant_Add > max){ //Si la suma de la cantidad que ya hay entre la suma de lo que se quiere añadir es mayor que el máximo puesto
            throw new IllegalArgumentException("La cantidad excede la capacidad de " + nombre); //devolver el error
        }
    }

    private void añadirHistorial(Cafe c){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFich, true))){ //Crear el fichero para el historial de cafés
            bw.write(c.formatToFile()); //Escribir la cadena en el fichero
            bw.newLine(); //Espacio
            System.out.println("Venta registrada correctamente.");
        } catch (IOException e){
            System.out.println("Error al registrar la venta: " + e.getMessage()); //Si no se puede, que muestre el error
        }
    }

    public void mostrarStock(){
        System.out.println("# STOCK # \n" 
                          + "[CAFÉ " + gCafe + "]\n" 
                          + "[CACAO " + gCacao + "]\n" 
                          + "[LECHE " + gLeche + "]\n"
                          + "[AGUA " + mlAgua + "]\n"
                          + "[CANELA " + gCanela + "]\n"
                          + "[VASOS " + vasos + "]");
    }


}
