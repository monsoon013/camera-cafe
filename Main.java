import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Maquina cameraCafe = new Maquina("X-133700");
        Scanner sc = new Scanner(System.in);
        int op = 0; 


        while(op < 6){
            try { //Envolver todo en un try catch para recoger todo tipo de errores del usuario.
                System.out.println("### BIENVENIDX ###");
                System.out.println("1. Añadir Café");
                System.out.println("2. Pedir Café");
                System.out.println("3. Agregar Ingredientes");
                System.out.println("4. Ver Historial");
                System.out.println("5. Mostrar Recursos");
                System.out.println("6. Salir");
                System.out.println("➤ ¿Qué deseas hacer?");

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
                        if(cameraCafe.getCafes().isEmpty()){
                            System.out.println("No hay recetas disponibles. Añade una.");
                            break;
                        }

                        System.out.println("## MENÚ ##");
                        int count = 1;
                        for (Cafe c : cameraCafe.getCafes()){
                            System.out.println(count + ". " + c.getNombre());
                            count++;
                        }

                        System.out.println("¿Qué café deseas?");
                        int desCafe = sc.nextInt();
                        cameraCafe.pedirCafe(desCafe - 1); //para acceder a la posición correcta. Es decir, al teclear 1 te llevaría a la posición 1, no a la PRIMERA.
                        break;
                    }
                    case 3:{
                        System.out.println("### RELLENAR ###");
                        System.out.println("1. Café\n" 
                                         + "2. Cacao\n"
                                         + "3. Leche\n"
                                         + "4. Agua\n"
                                         + "5. Vasos");
                        System.out.println("➤ ¿Qué deseas rellenar?");
                        int recurso = sc.nextInt();
                        sc.nextLine();
                        System.out.println("➤ Cantidad a rellenar");
                        int cantidad = sc.nextInt();

                        //Comprobar que no se pase de cantidad
                        try{
                            cameraCafe.rellenarIngredientes(recurso, cantidad);
                            System.out.println("Se ha rellenado correctamente");
                        }catch(IllegalArgumentException e){
                            System.out.println("Error al rellenar: " + e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("➤ Has accedido al historial de ventas...");
                        System.out.println("### HISTORIAL DE VENTAS ###");
                        try (BufferedReader br = new BufferedReader(new FileReader("historial_cafe.txt"))){
                            String linea;
                            while((linea = br.readLine()) != null) System.out.println(linea);
                        } catch(FileNotFoundException e){
                            System.out.println("Aún no hubo ventas.");
                        }
                        break;
                    }
                    case 5: {
                        cameraCafe.mostrarStock();
                        break;
                    }
                    case 6: {
                        System.out.println("Saliendo...");
                        break;
                    }
                }

            }catch (InputMismatchException e){
                System.out.println("Error: Introduce números: " + e.getMessage());
                sc.nextLine();
            }catch (Exception e){
                System.out.println("Error inesperado: " + e.getMessage());
            }
            
        }
        sc.close();
    }
}