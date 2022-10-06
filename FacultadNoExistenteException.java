package Trabajo_Final;

public class FacultadNoExistenteException extends Exception{
    public FacultadNoExistenteException() {
        super("Facultad No Existente: La facultad ingresada no existe en esta universidad o el nombre ingresado no es correcto.");
    }
}
