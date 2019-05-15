import clases.Alumno;
import clases.CursoOnline;
import clases.CursoPresencial;
import java.time.LocalDate;

public class Programa {

    public static void main(String[] args) {
     
        
        
        
        
        
        
        Alumno Pepe = new Alumno("Pepe", "34678904");
        Alumno Andrea = new Alumno("Andrea", "17679456", 125);
        
        CursoPresencial cursoP;
        cursoP = new CursoPresencial("Diseño de Bases de Datos", 50,
                 LocalDate.of(2014,5,5), LocalDate.of(2014,5,6), 20, 1);
        
        CursoOnline cursoO;
        cursoO = new CursoOnline("Administracion de Bases de Datos", 25,
                 LocalDate.of(2014,5,12), LocalDate.of(2014,5,17), 4, cursoP);
        
        cursoP.matricularAlumno(Andrea);
        cursoP.matricularAlumno(Pepe);
        cursoP.registroAsistencia(1, Pepe);
        
        cursoP.calificar();
        System.out.println(".:Alumnos Aptos del Curso Presencial:.");
        System.out.println(cursoP.getAlmunosAptos().toString());
        
        cursoO.matricularAlumno(Andrea);
        cursoO.matricularAlumno(Pepe);
        
        System.out.println("\n\n.:Alumnos Matriculados en el Curso Online:.");
        System.out.println(cursoO.getAlumnosMatriculados().toString());
        
        cursoO.superarNivel(Pepe);
        
        cursoO.calificar();
        System.out.println("\n.:Alumnos Aptos en el Curso Online:.");
        System.out.println(cursoO.getAlmunosAptos().toString());
        
        System.out.println("\n.:Lista de Alumnos Ordenados del Curso Presencial:.");
        System.out.println(cursoP.getListaAlumnos().toString());
    }
}