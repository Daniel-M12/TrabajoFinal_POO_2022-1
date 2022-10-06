package Trabajo_Final;

public class CursoNoExistenteException extends Exception{
    public CursoNoExistenteException() {
        super("El curso ingresado no existe.");
    }
}
