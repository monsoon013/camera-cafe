import java.util.ArrayList;

public class Maquina {

    private String numeroSerie;
    private ArrayList<Cafe> cafes;
    private int vasos;
    private int gCafe;
    private int gLeche;
    private int gCacao;
    private int mlAgua;

    public Maquina (String numeroSerie, ArrayList<Cafe> cafes, int vasos, 
                    int gCafe, int gCacao, int gLeche, int mlAgua ){
        this.numeroSerie = numeroSerie;
        this.cafes = cafes;
        this.vasos = vasos;
        this.gCafe = gCafe;
        this.gCacao = gCacao;
        this.gLeche = gLeche;
        this.mlAgua = mlAgua;
    }

    
    
}
