package Trabajo_Final;

import java.util.Scanner;

public class Main {
    public static Universidad universidad = new Universidad("UPC");

    public static void main(String[] args) {
        PantallaVisualizacion.generarPantalla();

        /*try {
            Scanner lector = new Scanner(System.in);
            System.out.print("Ingresar Facultad: ");
            String nombreFacultad = lector.nextLine();
            Facultad facultad = universidad.buscarFacultadPorNombre(nombreFacultad);
            System.out.println(facultad.getNombre());
        }catch (FacultadNoExistenteException f){
            System.out.println(f.getMessage());
        }*/
    }
}
