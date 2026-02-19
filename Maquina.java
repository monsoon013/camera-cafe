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
    private double recaudacionTotal;
    private String nombreFich = "historial_cafe.txt";

    private final int MAX_CAP = 1000;

    public Maquina (String numeroSerie){
        this.numeroSerie = numeroSerie;
        this.cafes = new ArrayList<>();
        this.vasos = 5;
        this.gCafe = 200;
        this.gCacao = 100;
        this.gLeche = 600;
        this.mlAgua = 800;
        this.recaudacionTotal = 0.0;
    }

    public void agregarReceta(Cafe c){
        cafes.add(c);
    }

    public ArrayList<Cafe> getCafes(){
        return cafes;
    }

    public String  getRecaudTotal(){return "La máquina ha recaudado " + recaudacionTotal ;}

    public void pedirCafe (int indice){
        if(indice < 0 || indice >= cafes.size()){
            System.out.println("No existe el café seleccionado");
            return;
        }


        Cafe c = cafes.get(indice);
        int aguaNecesaria = c.getGCafe() + c.getGCacao() + c.getGLeche();

        String error = comprobarStock(c, aguaNecesaria);
        if(error != null){
            System.out.println("No se puede servir :( | " + error);
            return;
        }

        consumirIngredientes(c, aguaNecesaria);
        recaudacionTotal  +=   c.getPrecio();

        System.out.println("Su café está siendo preparado...");
        añadirHistorial(c);
    }

    public String comprobarStock(Cafe c, int agua){
        if(vasos <= 0){ return "No quedan vasos.";}
        if(c.getGCafe() > gCafe) { return "No queda café.";}
        if(c.getGCacao() > gCacao) { return "No queda cacao.";}
        if(c.getGLeche() > gLeche) { return "No queda leche.";}
        if(agua > mlAgua){ return "No queda agua.";}

        return null; //retornar un error vacío como indicando que está todo correcto
    }

    private void consumirIngredientes(Cafe c, int agua){
        this.vasos--;
        this.gCafe -= c.getGCafe();
        this.gCacao -= c.getGCacao();
        this.gLeche -= c.getGLeche();
        this.mlAgua -= agua;
    }

    public void rellenarIngredientes(int op, int cantidad){
        if (cantidad <= 0 ){
            //Así como el return actúa más para fallos de flujo/programación, IllegalArgumentException funciona más para errores que cometa el usuario al usar el programa.
            throw new IllegalArgumentException("Error al añadir. La cantidad tiene que ser positiva"); 
        }

        switch(op){
            case 1:{
                verificarEspacio(cantidad, gCafe, MAX_CAP, "café");
                gCafe += cantidad;
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
                verificarEspacio(cantidad, vasos, 100, "vasos");
                vasos += cantidad;
                break;
            }
            default: {
                throw new IllegalArgumentException("Opción no válida");
            }
        }
    }
    
    //Evitar ifs reiterativos para comprobar la capacidad
    private void verificarEspacio(int cant_Act, int cant_Add, int max, String nombre){
        if(cant_Act + cant_Add > max){
            throw new IllegalArgumentException("La cantidad excede la capacidad de " + nombre);
        }
    }

    private void añadirHistorial(Cafe c){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFich, true))){
            bw.write(c.formatToFile());
            bw.newLine();
            System.out.println("Venta registrada correctamente.");
        } catch (IOException e){
            System.out.println("Error al registrar la venta: " + e.getMessage());
        }
    }

    public void mostrarStock(){
        System.out.println("# STOCK # \n" 
                          + "[CAFÉ " + gCafe + "]\n" 
                          + "[CACAO " + gCacao + "]\n" 
                          + "[LECHE " + gLeche + "]\n"
                          + "[AGUA " + mlAgua + "]\n"
                          + "[VASOS " + vasos + "]");
    }


}
