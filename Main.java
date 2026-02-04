import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Maquina cameraCafe = new Maquina("X-133700", null, 0, 0, 0, 0, 0);
        Scanner sc = new Scanner(System.in);
        int op = 0; 


        while(op < 4){
            System.out.println("### BIENVENIDX ###");
            System.out.println("1. Añadir Café");
            System.out.println("2. Pedir Café");
            System.out.println("3. Agregar Ingredientes");
            System.out.println("4. Salir");
            System.out.println("¿Qué deseas hacer?");

            op = sc.nextInt();
            sc.nextLine(); //limpieza del buffer

            switch(op){
                case 1: {
                    System.out.println("¿Qué café quieres añadir?");
                    String nomCaf = sc.nextLine();
                    System.out.println("Gramos de café: ");
                    int gramCaf = sc.nextInt();
                    System.out.println("Gramos de leche: ");
                    int gramLech = sc.nextInt();
                    System.out.println("Gramos de cacao: ");
                    int gramCacao = sc.nextInt();

                    Cafe c = new Cafe(nomCaf, gramCaf, gramCacao, gramLech);
                    cameraCafe.agregarReceta(c);
                    break;
                }
                case 2: {
                    cameraCafe.getCafes();
                    System.out.println("¿Qué café deseas?");
                    int desCafe = sc.nextInt();
                    cameraCafe.pedirCafe(desCafe);
                    break;
                }
                case 4: {
                    System.out.println("Saliendo...");
                    sc.close()
                }
            }
        }


        
    }
}