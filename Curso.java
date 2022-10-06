package Trabajo_Final;

import java.util.*;

public class Curso {
    private String materia;
    private int creditos;
    private Facultad facultad;

    public Curso(String materia, int creditos, Facultad facultad) {
        this.materia = materia;
        this.creditos = creditos;
        this.facultad = facultad;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getCredito() {
        return creditos;
    }

    public void setCredito(int credito) {
        this.creditos = credito;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }


    @Override
    public String toString() {
        return "Curso{" +
                "materia='" + materia + '\'' +
                ", creditos: " + creditos +
                ", facultad: " + facultad.getNombre() +
                '}';
    }
}