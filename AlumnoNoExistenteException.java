package Trabajo_Final;

public class AlumnoNoExistenteException extends Exception{
    public AlumnoNoExistenteException() {
        super("Alumno No Existente: El alumno ingresado no se encuentra en esta universidad.");
    }
}
