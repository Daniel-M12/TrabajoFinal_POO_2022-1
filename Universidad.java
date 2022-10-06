package Trabajo_Final;

import java.util.*;

public class Universidad {
    //Constante
    private final int ANIOACTUAL = 2022;
    //Atributos
    private String nombre;
    private static int numeroTotalAlumnos; //Sólo para determinar el código de los nuevos alumnos. Aumenta cada vez que ingresa un alumno y nunca disminuye para hacer que el código de alumno sea único.
    private List<Facultad> listaFacultades;
    private Map<Alumno,Facultad> alumnosEnCadaFacultad; //Relaciona el Alumno con la Facultad a la que pertenece

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.listaFacultades = new ArrayList<Facultad>();
        this.alumnosEnCadaFacultad = new HashMap<Alumno,Facultad>();

        //Simulando una base de datos con datos ya ingresados:
        try {
            BaseDatos.generarBaseDatos(this);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Facultad> getListaFacultades() {
        return listaFacultades;
    }

    public void setListaFacultades(List<Facultad> listaFacultades) {
        this.listaFacultades = listaFacultades;
    }

    public static int getNumeroTotalAlumnos() {
        return numeroTotalAlumnos;
    }

    //Métodos asociados a la Facultad
    public boolean existeFacultad (String nombreFacultad){
        for (Facultad facultad:this.listaFacultades) {
            if (facultad.getNombre().equals(nombreFacultad)){
                return true;
            }
        }
        return false;
    }

    public Facultad buscarFacultadPorNombre(String nombreFacultad) throws FacultadNoExistenteException{
        if (existeFacultad(nombreFacultad)){
            for (Facultad facultad:listaFacultades) {
                if (facultad.getNombre().equals(nombreFacultad)){
                    return facultad;
                }
            }
        } else {
            throw new FacultadNoExistenteException();
        }

        return null;
    }

    public Facultad crearNuevaFacultad(String nombreDeNuevaFacultad) { //Usado por el usuario para crear una nueva Facultad
        if (!existeFacultad(nombreDeNuevaFacultad)){
            Facultad facultadNueva = new Facultad(nombreDeNuevaFacultad);
            listaFacultades.add(facultadNueva);
            return facultadNueva;
        } else {
            System.out.println("La facultad que intenta crear ya existe.");
        }
        return null;
    }

    public void visualizarListaFacultades(){
        for (Facultad facultad:listaFacultades) {
            System.out.println("Facultad de " + facultad.getNombre());
        }
    }

    //Métodos asociados al Alumno
    public boolean existeAlumnoUniversidad(String dniAlumno) { //Para ser usado por Universidad
        for (Alumno alumno: alumnosEnCadaFacultad.keySet()) {
            if (alumno.getDni().equals(dniAlumno)){
                return true;
            }
        }
        return false;
    }

    public Alumno buscarAlumnoPorDNI(String dniAlumno) throws AlumnoNoExistenteException{
        Alumno alumnoBuscado = null;
        if (existeAlumnoUniversidad(dniAlumno)) {
            for (Alumno alumno : alumnosEnCadaFacultad.keySet()) {
                if (alumno.getDni().equals(dniAlumno)) {
                    Facultad facultadAlumno = alumnosEnCadaFacultad.get(alumno);
                    alumnoBuscado = facultadAlumno.buscarAlumnoEnFacultad(alumno.getCodigo());
                }
            }
        } else {
            throw new AlumnoNoExistenteException();
        }
        return alumnoBuscado;
    }

    public Alumno ingresarNuevoAlumno(String nombre, String dni, String nombreFacultad) throws FacultadNoExistenteException { //Usado por el usuario si desea ingresar un nuevo alumno
        numeroTotalAlumnos += 1; //Incrementa el número total de alumnos de la Universidad

        if (!existeAlumnoUniversidad(dni)){
            Facultad facultadDelNuevoAlumno = buscarFacultadPorNombre(nombreFacultad); //Este método lanza la excepción relanzada por ingresarNuevoAlumno
            String codigoNuevoAlumno = generarCodigoDeAlumno();
            Alumno alumnoNuevo = new Alumno(codigoNuevoAlumno, dni, nombre, facultadDelNuevoAlumno);

            facultadDelNuevoAlumno.asignarNuevoAlumno(alumnoNuevo); //Agregar nuevo alumno a la Facultad deseada

            alumnosEnCadaFacultad.put(alumnoNuevo,facultadDelNuevoAlumno); //Registrar al Alumno asociado a la Facultad, en el registro (Map) de la Universidad

            return alumnoNuevo;
        } else {
            System.out.println("El alumno ingresado ya forma parte de " + this.nombre);
        }
        return null;
    }

    public void retirarAlumno(String dniAlumno) throws AlumnoNoExistenteException{ //Usado por el usuario, para remover a un alumno de la unviversidad
        Alumno alumnoARetirar = buscarAlumnoPorDNI(dniAlumno); //Este método lanza la excepción relanzada por el método

        if (alumnosEnCadaFacultad.containsKey(alumnoARetirar)){
            Facultad facultadDelAlumno = alumnosEnCadaFacultad.get(alumnoARetirar); //Obtiene la facultad a la que pertenece el alumno, según el registro de la Universidad.
            facultadDelAlumno.removerAlumno(alumnoARetirar.getCodigo());
        } else {
            System.out.println("El DNI ingresado no corresponde a ningún alumno registrado.");
        }
    }

    public String generarCodigoDeAlumno(){
        String codigoNuevo = "";

        codigoNuevo += Integer.toString(ANIOACTUAL); //2022
        String numeroDelCodigo = Integer.toString(numeroTotalAlumnos); //1
        String cero = "0";

        while (numeroDelCodigo.length()<5){ //01
            numeroDelCodigo = cero.concat(numeroDelCodigo); //00001
        }

        codigoNuevo += numeroDelCodigo; //202200001

        return codigoNuevo;
    }

    //Asociados a la Facultad y Alumno
    /*public void cambiarFacultadDelAlumno(String dni, String nombreDeFacultadAMigrar) throws FacultadNoExistenteException, AlumnoNoExistenteException{
        Facultad facultarHaciaDondeMigra = buscarFacultadPorNombre(nombreDeFacultadAMigrar); //Este método lanza una excepción relanzada por cambiarFacultadDelAlumno
        Alumno alumnoAMigrar = buscarAlumnoPorDNI(dni); //Este método lanza la otra excepción

        Facultad facultadActual = null;

        if (existeAlumnoUniversidad(dni)){
            facultadActual = alumnoAMigrar.getFacultad();
        } else { //Entra aquí si el alumno no estaba en ninguna facultad
            System.out.println("El DNI ingresado no corresponde a ningún alumno registrado. No se pudo realizar la migración.");
            return; //Termina la ejecución del método
        }

        //Asignar nueva facultad al alumno
        facultadActual.getListaAlumnos().remove(alumnoAMigrar); //Se remueve al alumno de la facultad actual
        facultarHaciaDondeMigra.getListaAlumnos().add(alumnoAMigrar); //Se coloca al alumno en la nueva facultad

        //Actualizando el Map en Universidad
        alumnosEnCadaFacultad.put(alumnoAMigrar,facultarHaciaDondeMigra);
    }*/

}