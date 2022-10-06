package Trabajo_Final;

import java.util.*;

public class Alumno {
    private String codigo;
    private String dni;
    private String nombre;
    private Facultad facultad;
    private List<Curso> cursosActuales;

    public Alumno(String codigo, String dni, String nombre, Facultad facultad) {
        this.codigo = codigo;
        this.dni = dni;
        this.nombre = nombre;
        this.facultad = facultad;
        this.cursosActuales = new ArrayList<Curso>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Curso> getCursosActuales() {
        return cursosActuales;
    }

    public void setCursosActuales(List<Curso> cursosActuales) {
        this.cursosActuales = cursosActuales;
    }

    //Métodos relativos a los cursos del Alumno
    public void asignarCurso(Curso curso) throws CursoNoExistenteException{ //Llamado por Facultad
        if (curso.getFacultad().equals(this.facultad)){
            cursosActuales.add(curso);
        } else {
            System.out.println("El curso no pertenece a la facultad del alumno.");
            throw new CursoNoExistenteException();
        }
    }

    public void visualizarListaCursos(){
        for (Curso curso:cursosActuales) {
            System.out.println(curso.toString());
        }
    }

    public int calcularCreditosTotales(){
        int creditosTotales = 0;
        for (Curso curso:cursosActuales) {
            creditosTotales += curso.getCredito();
        }
        return creditosTotales;
    }


    @Override
    public String toString() {
        return "Alumno{" +
                "Código: " + codigo +
                ", DNI: " + dni +
                ", nombre: " + nombre +
                ". Facultad: " + facultad.getNombre() +
                '}';
    }
}