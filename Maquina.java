import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Maquina {

    private String numeroSerie;
    private ArrayList<Cafe> cafes;
    private int vasos;
    private int gCafe;
    private int gLeche;
    private int gCacao;
    private int mlAgua;
    private String nombreFich = "historial_cafe.txt";

    private final int MAX_CAP = 1000;

    public Maquina (String numeroSerie, ArrayList<Cafe> cafes, int vasos, 
                    int gCafe, int gCacao, int gLeche, int mlAgua ){
        this.numeroSerie = numeroSerie;
        this.cafes = new ArrayList<>();
        this.vasos = 5;
        this.gCafe = 200;
        this.gCacao = 100;
        this.gLeche = 600;
        this.mlAgua = 800;
    }

    public void agregarReceta(Cafe c){
        cafes.add(c);
    }

    public ArrayList<Cafe> getCafes(){
        return cafes;
    }

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

        System.out.println("Su café está siendo preparado...");
        añadirHistorial(c);
    }

    public String comprobarStock(Cafe c, int agua){
        if(vasos <= 0){ return "No quedan vasos.";}
        if(c.getGCafe() > gCafe) { return "No queda café.";}
        if(c.getGCacao() > gCacao) { return "No queda cacao.";}
        if(c.getGLeche() > gLeche) { return "No queda leche.";}
        if(agua > mlAgua){ return "No queda agua.";}

        return null;
    }

    private void consumirIngredientes(Cafe c, int agua){
        this.vasos--;
        this.gCafe -= c.getGCafe();
        this.gCacao -= c.getGCacao();
        this.gLeche -= c.getGLeche();
        this.mlAgua -= agua;
    }

    public void añadirHistorial(Cafe c){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFich, true))){
            bw.write(c.formatToFile());
            bw.newLine();
            System.out.println("Venta registrada correctamente.");
        } catch (IOException e){
            System.out.println("Error al registrar la venta: " + e.getMessage());
        }
    }

    public int rellenarIngredientes(int ingrediente){
        int op = 0;
        Scanner sc = new Scanner(System.in);
        
        
    }
    
}
