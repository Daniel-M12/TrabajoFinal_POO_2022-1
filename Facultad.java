package Trabajo_Final;

import java.util.*;

public class Facultad {
    private String nombre;
    private List<Curso> listaCursos;
    private List<Alumno> listaAlumnos;

    public Facultad(String nombre) {
        this.nombre = nombre;
        this.listaCursos = new ArrayList<Curso>();
        this.listaAlumnos = new ArrayList<Alumno>();
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    //Métodos relativos a los alumnos
    private boolean existeAlumnoEnFacultad(String codigoAlumno){ //Sólo usado dentro de Facultad. Indica si existe o no el alumno en la lista de la facultad
        for (Alumno alumno:this.listaAlumnos) {
            if (alumno.getCodigo().equals(codigoAlumno)){
                return true;
            }
        }
        return false;
    }

    public void asignarNuevoAlumno(Alumno alumno){ //Usado en Universidad
        listaAlumnos.add(alumno);
    }

    public Alumno buscarAlumnoEnFacultad(String codigoAlumno) throws AlumnoNoExistenteException{ //Devuelve un objeto de clase Alumno a partir de su código de alumno
        if (existeAlumnoEnFacultad(codigoAlumno)){
            for (Alumno alumno:listaAlumnos) {
                if (alumno.getCodigo().equals(codigoAlumno)){
                    return alumno;
                }
            }
        } else { //Si el alumno no existe lanza una excepción
            throw new AlumnoNoExistenteException();
        }
        return null;
    }

    public void removerAlumno (String codigoAlumno) throws AlumnoNoExistenteException{
        Alumno alumnoARetirar = buscarAlumnoEnFacultad(codigoAlumno); //Este método lanza la excepción relanzada por removerAlumno
        if (alumnoARetirar != null){
            listaAlumnos.remove(alumnoARetirar);
        }
    }

    public void visualizarListaAlumnos(){
        for (Alumno alumno:listaAlumnos) {
            System.out.println(alumno.toString());
        }
    }

    //Métodos relativos a los cursos
    private boolean existeCursoEnFacultad(String nombreCurso){ //Sólo usado dentro de Facultad. Indica si existe o no en la lista de la facultad
        for (Curso curso:listaCursos) {
            if (curso.getMateria().equals(nombreCurso)){
                return true;
            }
        }
        return false;
    }

    public Curso crearNuevoCurso(String nombreCurso, int creditos){
        if (!existeCursoEnFacultad(nombreCurso)){ //Crea un nuevo curso si no existe
            Curso curso = new Curso(nombreCurso, creditos, this);
            listaCursos.add(curso);
            return curso;

        } else { //Si ya existe el curso muestra el siguiente mensaje y...
            System.out.println("Ya existe un curso con el nombre ingresado.");
        }
        return null; //...devuelve "null".
    }

    public Curso buscarCursoEnFacultad(String nombreCurso) throws CursoNoExistenteException{ //Devuelve un objeto de clase Curso a partir de su nombre de materia
        if (existeCursoEnFacultad(nombreCurso)) {
            for (Curso curso:listaCursos) {
                if (curso.getMateria().equals(nombreCurso)){
                    return curso;
                }
            }
        } else { //Si el curso no existe lanza una excepción
            throw new CursoNoExistenteException();
        }
        return null;
    }

    public void removerCurso(String nombreCurso) throws CursoNoExistenteException{
        Curso cursoEliminar = buscarCursoEnFacultad(nombreCurso); //Este método lanza la excepción relanzada por removerAlumno
        if (cursoEliminar != null){
            listaCursos.remove(cursoEliminar);
        }
    }

    public void visualizarListaCursos(){
        if (!listaCursos.isEmpty()){
            for (Curso curso:listaCursos) {
                System.out.println(curso.toString());
            }
        } else {
            System.out.println("Aún no se han registrado cursos en esta facultad.");
        }
    }

    //Relativos a Curso y Alumno a la vez
    public void asignarCursoAAlumno(String codigoAlumno, String nombreCurso) throws AlumnoNoExistenteException, CursoNoExistenteException{ //Usado en Universidad. Agrega un Curso a la lista de cursos del Alumno
        Alumno alumnoAAsignarCurso = buscarAlumnoEnFacultad(codigoAlumno); //Este método lanza una de las excepciones relanzadas por asignarCursoAAlumno
        Curso cursoAAsignar = buscarCursoEnFacultad(nombreCurso); //Este método lanza la otra excepción

        if (alumnoAAsignarCurso!=null && cursoAAsignar!=null){
            alumnoAAsignarCurso.asignarCurso(cursoAAsignar);
        } else {
            System.out.println("No se pudo asignar el curso al alumno.");
        }
    }

    /*
    @Override
    public String toString() {
        return "Facultad de " + nombre;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facultad)) return false;
        Facultad facultad = (Facultad) o;
        return nombre.equals(facultad.nombre) && Objects.equals(listaCursos, facultad.listaCursos) && Objects.equals(listaAlumnos, facultad.listaAlumnos);
    }

}