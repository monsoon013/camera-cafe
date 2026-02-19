import java.io.*; //llamamos a la librería io entera para poder acceder y crear el archivo.txt
import java.util.InputMismatchException; //llamamos al tipo de error
import java.util.Scanner; //crear el scanner para introducir datos por pantalla


public class Main {
    public static void main(String[] args) {
        Maquina cameraCafe = new Maquina("X-133700"); //creamos la máquina
        Scanner sc = new Scanner(System.in);
        int op = 0; 


        while(op < 7){
            try { //Envolver todo en un try catch para recoger todo tipo de errores del usuario.
                System.out.println("### BIENVENIDX ###");
                System.out.println("1. Añadir Café");
                System.out.println("2. Pedir Café");
                System.out.println("3. Agregar Ingredientes");
                System.out.println("4. Ver Historial");
                System.out.println("5. Mostrar Recursos");
                System.out.println("6. Ver Recaudación");
                System.out.println("7. Salir");
                System.out.println("➤ ¿Qué deseas hacer?");

                op = sc.nextInt();
                sc.nextLine(); //limpieza del buffer

                switch(op){
                    case 1: {
                        System.out.println("¿Qué café quieres añadir?");
                        String nomCaf = sc.nextLine(); //pedir por pantalla los datos del café.
                        System.out.println("Gramos de café: ");
                        int gramCaf = sc.nextInt(); //meterlos en variables independientes
                        System.out.println("Gramos de leche: ");
                        int gramLech = sc.nextInt();
                        System.out.println("Gramos de cacao: ");
                        int gramCacao = sc.nextInt();
                        System.out.println("Precio del café:");
                        double precioCafe = sc.nextDouble();

                        Cafe c = new Cafe(nomCaf, gramCaf, gramCacao, gramLech, precioCafe);
                        cameraCafe.agregarReceta(c);
                        break;
                    }
                    case 2: {
                        if(cameraCafe.getCafes().isEmpty()){ //comprobar que haya al menos una receta
                            System.out.println("No hay recetas disponibles. Añade una.");
                            break;
                        }

                        System.out.println("## MENÚ ##");
                        int count = 1;
                        for (Cafe c : cameraCafe.getCafes()){
                            System.out.println(count + ". " + c.getNombre() + " - " + c.getPrecio());
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
                        System.out.println("¿Qué deseas rellenar?");
                        int recurso = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Cantidad a rellenar");
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
                        System.out.println("Has accedido al historial de ventas...");
                        System.out.println("### HISTORIAL DE VENTAS ###");
                        try (BufferedReader br = new BufferedReader(new FileReader("historial_cafe.txt"))){ //leer el historial
                            String linea;
                            while((linea = br.readLine()) != null){ //Comprobar que se pueda leer la línea en el txt
                                System.out.println(linea); //Si se puede, que la imprima por pantalla
                            } 
                        } catch(FileNotFoundException e){
                            System.out.println("Aún no hubo ventas."); //Si no existe el txt, es que no hubo ventas
                        }
                        break;
                    }
                    case 5: {
                        cameraCafe.mostrarStock();
                        break;
                    }
                    case 6: {
                       System.out.println(cameraCafe.getRecaudTotal()); 
                        break;
                    }
                    case 7: {
                        System.out.println("Saliendo...");
                        break;
                    }
                }

            }catch (InputMismatchException e){
                System.out.println("Error: Introduce números: " + e.getMessage()); //Por si introduce un caracter
                sc.nextLine();
            }catch (Exception e){
                System.out.println("Error inesperado: " + e.getMessage()); //mostrar el error por si hay otro tipo de error
            }
            
        }
        sc.close();
    }
}