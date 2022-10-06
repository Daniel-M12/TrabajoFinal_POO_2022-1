package Trabajo_Final;

public class BaseDatos {
    public static void generarBaseDatos(Universidad universidad) throws Exception{
        Facultad ingenieria = universidad.crearNuevaFacultad("Ingeniería");
        Facultad cienciasExactas = universidad.crearNuevaFacultad("Ciencias Exactas");
        Facultad medicina = universidad.crearNuevaFacultad("Medicina");

        Curso poo = ingenieria.crearNuevoCurso("Programación Orientada a Objetos", 5);
        Curso estDat = ingenieria.crearNuevoCurso("Estructuras de Datos", 5);
        Curso intProg = ingenieria.crearNuevoCurso("Introducción a la Programación", 4);

        Curso quim1 = cienciasExactas.crearNuevoCurso("Química 1", 4);
        Curso fis1 = cienciasExactas.crearNuevoCurso("Física 1", 5);
        Curso cal1 = cienciasExactas.crearNuevoCurso("Cálculo 1", 5);

        Curso anat = medicina.crearNuevoCurso("Anatomía", 5);
        Curso biol = medicina.crearNuevoCurso("Biología 1", 4);
        Curso fis = medicina.crearNuevoCurso("Fisiología", 5);

        Alumno alumno1 = universidad.ingresarNuevoAlumno("Pepe Salas", "11111111", "Medicina");
        Alumno alumno2 = universidad.ingresarNuevoAlumno("Roberto Quispe", "22222222", "Ciencias Exactas");
        Alumno alumno3 = universidad.ingresarNuevoAlumno("Juana Apac", "33333333", "Ingeniería");

        Alumno alumno4 = universidad.ingresarNuevoAlumno("Luis Nahu", "44444444", "Medicina");
        Alumno alumno5 = universidad.ingresarNuevoAlumno("Sara Crass", "55555555", "Ciencias Exactas");
        Alumno alumno6 = universidad.ingresarNuevoAlumno("Lia Yin", "66666666", "Ingeniería");

        Alumno alumno7 = universidad.ingresarNuevoAlumno("Yesica Silva", "77777777", "Medicina");
        Alumno alumno8 = universidad.ingresarNuevoAlumno("Irvin Zaravia", "88888888", "Ciencias Exactas");
        Alumno alumno9 = universidad.ingresarNuevoAlumno("Jhoan Malpartida", "99999999", "Ingeniería");

        alumno1.asignarCurso(anat);
        alumno1.asignarCurso(biol);
        alumno1.asignarCurso(fis);
        alumno4.asignarCurso(anat);
        alumno4.asignarCurso(biol);
        alumno4.asignarCurso(fis);
        alumno7.asignarCurso(anat);
        alumno7.asignarCurso(biol);
        alumno7.asignarCurso(fis);

        alumno2.asignarCurso(quim1);
        alumno2.asignarCurso(fis1);
        alumno2.asignarCurso(cal1);
        alumno5.asignarCurso(quim1);
        alumno5.asignarCurso(fis1);
        alumno5.asignarCurso(cal1);
        alumno8.asignarCurso(quim1);
        alumno8.asignarCurso(fis1);
        alumno8.asignarCurso(cal1);

        alumno3.asignarCurso(poo);
        alumno3.asignarCurso(estDat);
        alumno3.asignarCurso(intProg);
        alumno6.asignarCurso(poo);
        alumno6.asignarCurso(estDat);
        alumno6.asignarCurso(intProg);
        alumno9.asignarCurso(poo);
        alumno9.asignarCurso(estDat);
        alumno9.asignarCurso(intProg);

    }
}
