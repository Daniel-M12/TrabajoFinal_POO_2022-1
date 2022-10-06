package Trabajo_Final;


import java.util.Scanner;
import java.util.SortedMap;

public class PantallaVisualizacion {
    private static Scanner lector = new Scanner(System.in);
    private static Universidad universidad = Main.universidad;

    public static void generarPantalla(){
        boolean continuar = true;
        String opcion;

        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("--------CONTROL DE LA UNIVERSIDAD--------");
            System.out.println("-----------------------------------------");
            System.out.println("- Seleccione la sección a ingresar:    - ");
            System.out.println("    1. Control de Facultad.              ");
            System.out.println("    2. Control de Cursos.                ");
            System.out.println("    3. Control de Alumnos.               ");
            System.out.println("    4. Salir.                            ");
            System.out.println("-----------------------------------------");
            System.out.print("Opción elegida (1, 2, 3, 4): ");
            opcion = lector.next();

            switch (opcion){
                case "1":
                    controlFacultad();
                    break;
                case "2":
                    controlCursos();
                    break;
                case "3":
                    controlAlumnos();
                    break;
                case "4":
                    continuar = false;
                    break;
                default:
                    System.out.println("Ingrese una opción correcta.");
                    break;
            }

        }while (continuar);

    }

    private static void controlAlumnos() {
        boolean continuar = true;
        String opcion;

        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("------------CONTROL DE ALUMNOS-----------");
            System.out.println("-----------------------------------------");
            System.out.println("-- Seleccione una opción:              --");
            System.out.println("    1. Ingresar nuevo Alumno.            ");
            System.out.println("    2. Visualizar Alumno(s).             ");
            System.out.println("    3. Asignar cursos al Alumno.         ");
            System.out.println("    4. Salir.                            ");
            System.out.println("-----------------------------------------");
            System.out.print("Opción elegida (1, 2, 3, 4): ");
            opcion = lector.next();

            switch (opcion){
                case "1":
                    lector.nextLine();
                    System.out.print("¿A qué facultad ingresará el alumno?: ");
                    String nombreFacultadIngreso = lector.nextLine();
                    System.out.println("");

                    System.out.println("Ingrese los datos del Alumno: ");
                    System.out.print("- Nombre: ");
                    String nombreAlumnoNuevo = lector.nextLine();
                    System.out.print("- DNI: ");
                    String dniAlumnoNuevo = lector.nextLine();
                    //lector.nextLine();

                    try {
                        Facultad facultadIngreso = universidad.buscarFacultadPorNombre(nombreFacultadIngreso);
                        //Creando nuevo Alumno
                        Alumno alumnoNuevo = universidad.ingresarNuevoAlumno(nombreAlumnoNuevo,dniAlumnoNuevo,nombreFacultadIngreso);

                        System.out.print("¿Desea asignar cursos al alumno? (S/N): ");
                        String respuesta = lector.nextLine();

                        if (respuesta.equalsIgnoreCase("S")){
                            asignarCursosAlumno(alumnoNuevo, facultadIngreso);
                        } else if (respuesta.equalsIgnoreCase("N")){

                        }
                    } catch (FacultadNoExistenteException f){
                        System.out.println(f.getMessage());
                    }
                    break;
                case "2":
                    boolean otroAlumno = true;
                    lector.nextLine();
                    System.out.println("¿Desea visualizar una lista de alumnos en una facultad (1) o la lista de cursos de un alumno (2)?");
                    String rpta = lector.nextLine();
                    switch (rpta){
                        case "1": //Lista de alumnos en una facultad
                            System.out.print("Ingrese el nombre de una facultad para la visualización: ");
                            String nombreFacultadVisualizarAlumnos = lector.nextLine();
                            try {
                                System.out.println("");
                                System.out.println("-----------------------------------------");
                                Facultad facultadVisualizarAlumnos = universidad.buscarFacultadPorNombre(nombreFacultadVisualizarAlumnos);
                                facultadVisualizarAlumnos.visualizarListaAlumnos();
                                System.out.println("-----------------------------------------");
                                System.out.println("");
                            }catch (FacultadNoExistenteException f){
                                System.out.println(f.getMessage());
                            }
                            break;
                        case "2": //Lista de cursos del alumno
                            System.out.print("¿A qué facultad pertenece el alumno?: ");
                            String nombreFacultadAlumno = lector.nextLine();
                            lector.nextLine();
                            System.out.print("Ingrese el código del alumno: ");
                            String codigoAlumno = lector.nextLine();
                            try {
                                Facultad facultadAlumno = universidad.buscarFacultadPorNombre(nombreFacultadAlumno);
                                Alumno alumnoAMostrarCursos = facultadAlumno.buscarAlumnoEnFacultad(codigoAlumno);
                                System.out.println("");
                                System.out.println("-----------------------------------------");
                                alumnoAMostrarCursos.visualizarListaCursos();
                                System.out.println("-----------------------------------------");
                                System.out.println("");
                            }catch (FacultadNoExistenteException f){
                                System.out.println(f.getMessage());
                            }catch (AlumnoNoExistenteException a){
                                System.out.println(a.getMessage());
                            }
                            break;
                    }
                    break;
                case "3":
                    lector.nextLine();
                    System.out.print("Ingrese la facultad a la que pertenece el alumno: ");
                    String nombreFacultadAsignarCursos = lector.nextLine();
                    try{
                        Facultad facultadAsignarCursos = universidad.buscarFacultadPorNombre(nombreFacultadAsignarCursos);
                        System.out.print("Ingrese el código del alumno: ");
                        String codigoAlumnoAsignar = lector.nextLine();

                        asignarCursosAlumno(facultadAsignarCursos.buscarAlumnoEnFacultad(codigoAlumnoAsignar),facultadAsignarCursos);

                    }catch (FacultadNoExistenteException f){
                        System.out.println(f.getMessage());
                    }catch (AlumnoNoExistenteException a){
                        System.out.println(a.getMessage());
                    }
                    break;
                case "4":
                    continuar = false;
                    break;
                default:
                    System.out.println("Ingrese una opción correcta.");
                    break;
            }

        }while (continuar);
    }

    private static void asignarCursosAlumno(Alumno alumno, Facultad facultad) {
        System.out.println("Lista de cursos disponibles: ");
        System.out.println("-----------------------------------------");
        facultad.visualizarListaCursos();
        System.out.println("-----------------------------------------");

        boolean masCursos = true;
        do{
            System.out.println("");
            System.out.println("¿Qué curso le desea asignar?");
            String nombreCursoAAsignar = lector.nextLine();
            try {
                Curso cursoAAsignar = facultad.buscarCursoEnFacultad(nombreCursoAAsignar);
                facultad.asignarCursoAAlumno(alumno.getCodigo(),nombreCursoAAsignar);
            }catch (CursoNoExistenteException c){
                System.out.println(c.getMessage());
            } catch (AlumnoNoExistenteException a){
                System.out.println(a.getMessage());
            }
            //lector.nextLine();
            do {
                System.out.println("");
                System.out.println("¿Desea asignar más cursos al alumno? (S/N)");
                String respuesta = lector.nextLine();
                if (respuesta.equals("N")) {
                    masCursos = false;
                    break;
                }
                else if (respuesta.equals("S")){
                    break;
                }
                else {
                    System.out.println("Ingrese una opción correcta.");
                    continue;
                }
            }while (true);
        }while (masCursos);
    }

    private static void controlCursos() {
        boolean continuar = true;
        String opcion;

        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("------------CONTROL DE CURSOS------------");
            System.out.println("-----------------------------------------");
            System.out.println("-- Seleccione una opción:              --");
            System.out.println("    1. Crear nuevo Curso.                ");
            System.out.println("    2. Visualizar lista de cursos.       ");
            System.out.println("    3. Modificar Curso.                  ");
            System.out.println("    4. Eliminar Curso.                   ");
            System.out.println("    5. Salir.                            ");
            System.out.println("-----------------------------------------");
            System.out.print("Opción elegida (1, 2, 3, 4, 5): ");
            opcion = lector.next();
            //lector.nextLine();

            switch (opcion){
                case "1":
                    lector.nextLine();
                    System.out.print("Ingrese el nombre de la facultad en la que creará el nuevo curso: ");
                    String nombreFacultadNuevoCurso = lector.nextLine();
                    try{
                        Facultad facultadNuevoCurso = universidad.buscarFacultadPorNombre(nombreFacultadNuevoCurso);
                        System.out.println("");
                        System.out.print("Ingrese el nombre del curso a crear: ");
                        String nombreNuevoCurso = lector.nextLine();

                        System.out.print("Ingrese el número de créditos que tendrá el curso " + nombreNuevoCurso + ": ");
                        int creditosNuevoCurso = lector.nextInt();

                        Curso cursoYaCreado = facultadNuevoCurso.crearNuevoCurso(nombreNuevoCurso,creditosNuevoCurso);
                        System.out.println("");
                        if (cursoYaCreado!=null){
                            System.out.println("Se ha agregado el curso " + nombreNuevoCurso + " a la Facultad de " + facultadNuevoCurso.getNombre() + " exitosamente.");
                        } else {
                            System.out.println("El curso no ha podido ser creado.");
                        }

                        lector.nextLine();
                    }catch (FacultadNoExistenteException f){
                        System.out.println(f.getMessage());
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "2":
                    lector.nextLine();
                    System.out.print("¿De qué facultad desea visualizar la lista de cursos?: ");
                    String nombreFacultadVisualizarCursos = lector.nextLine();
                    try {
                        Facultad facultadVisualizarCursos = universidad.buscarFacultadPorNombre(nombreFacultadVisualizarCursos);
                        System.out.println("-----------------------------------------");
                        facultadVisualizarCursos.visualizarListaCursos();
                        System.out.println("-----------------------------------------");
                    }catch (FacultadNoExistenteException f){
                        System.out.println(f.getMessage());
                    }
                    lector.nextLine();
                    break;
                case "3":
                    lector.nextLine();
                    System.out.print("Ingrese el nombre de la facultad de la que desea modificar el/los curso(s): ");
                    String nombreFacultadModificarCursos = lector.nextLine();

                    try{
                        Facultad facultadModificarCursos = universidad.buscarFacultadPorNombre(nombreFacultadModificarCursos);
                        //lector.nextLine();
                        System.out.println("-----------------------------------------");
                        facultadModificarCursos.visualizarListaCursos();
                        System.out.println("-----------------------------------------");
                        System.out.println("");
                        System.out.print("Ingrese el nombre del curso a modificar: ");
                        String nombreCursoModificar = lector.nextLine();

                        Curso cursoAModificar = facultadModificarCursos.buscarCursoEnFacultad(nombreCursoModificar);
                        System.out.println("Sólo puede modificar el número de créditos del curso.");
                        System.out.print("Ingrese el nuevo número de créditos que tendrá el curso: ");
                        int nuevoNumeroCreditos = lector.nextInt();

                        cursoAModificar.setCredito(nuevoNumeroCreditos);
                        lector.nextLine();

                        System.out.println("El curso " + nombreCursoModificar + " ha sido actualizado correctamente.");
                        System.out.println("");
                    }catch (FacultadNoExistenteException f){
                        System.out.println(f.getMessage());
                    }catch (CursoNoExistenteException c){
                        System.out.println(c.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "4":
                    lector.nextLine();
                    System.out.print("Ingrese el nombre de la facultad a la que pertenece el curso a eliminar: ");
                    String nombreFacultadEliminarCurso = lector.nextLine();

                    try {
                        Facultad facultadEliminarCurso = universidad.buscarFacultadPorNombre(nombreFacultadEliminarCurso);
                        //lector.nextLine();
                        System.out.println("-----------------------------------------");
                        facultadEliminarCurso.visualizarListaCursos();
                        System.out.println("-----------------------------------------");
                        System.out.println("");
                        System.out.print("Indique el nombre del curso a eliminar: ");
                        String nombreCursoEliminar = lector.nextLine();

                        Curso cursoEliminar = facultadEliminarCurso.buscarCursoEnFacultad(nombreCursoEliminar);

                        facultadEliminarCurso.removerCurso(nombreCursoEliminar);
                        //lector.nextLine();
                        System.out.println("El curso " + nombreCursoEliminar + " ha sido eliminado exitosamente de la Facultad de " + nombreFacultadEliminarCurso);
                        lector.nextLine();
                    }catch (FacultadNoExistenteException f){
                        System.out.println(f.getMessage());
                    }catch (CursoNoExistenteException c){
                        System.out.println(c.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "5":
                    continuar = false;
                    break;
                default:
                    System.out.println("Ingrese una opción correcta.");
                    break;
            }

        }while (continuar);
    }

    private static void controlFacultad() {
        boolean continuar = true;
        String opcion;

        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("-----------CONTROL DE FACULTAD-----------");
            System.out.println("-----------------------------------------");
            System.out.println("-- Seleccione una opción:              --");
            System.out.println("    1. Crear Nueva Facultad.             ");
            System.out.println("    2. Visualizar lista de facultades.   ");
            System.out.println("    3. Salir.                            ");
            System.out.println("-----------------------------------------");
            System.out.print("Opción elegida (1, 2, 3): ");
            opcion = lector.next();

            switch (opcion){
                case "1": //Crear Facultad
                    lector.nextLine();
                    System.out.print("Ingrese el nombre de la nueva facultad: ");
                    String nombreFacultadCrear = lector.nextLine();

                    Facultad facultad = universidad.crearNuevaFacultad(nombreFacultadCrear);
                    if (facultad!=null){
                        System.out.println("Se ha creado exitosamente la Facultad de " + facultad.getNombre());
                    } else {
                        lector.nextLine();
                    }
                    break;
                case "2": //Visualizar Facultades
                    System.out.println("");
                    System.out.println("-----------------------------------------");
                    universidad.visualizarListaFacultades();
                    System.out.println("-----------------------------------------");
                    lector.nextLine();
                    lector.nextLine();
                    break;
                case "3":
                    continuar = false;
                    break;
                default:
                    System.out.println("Ingrese una opción correcta.");
                    break;
            }

        }while (continuar);
    }



}
