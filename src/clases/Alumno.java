package clases;

import java.util.HashSet;

public class Alumno implements Comparable<Alumno> {
    private final String nombre;
    private final String DNI;
    private double credito = 100.0;
    private HashSet <Curso> cursos;
    
    public Alumno(String nombre, String DNI) {
        cursos = new HashSet<>();
        this.nombre = nombre;
        this.DNI = DNI;
    }
    
    public Alumno(String nombre, String DNI, double credito) {
        this(nombre, DNI); 
        this.credito = credito;
    }
    

    public String getDNI() { return this.DNI; }
    public double getCredito() { return this.credito; }
    
    public void incrementarCredito(double cant) { this.credito += cant; }
    public void decrementarCredito(double cant) {
        if(this.credito >= cant) {
            this.credito -= cant;
        }
    }
    public boolean agregarCurso(Curso curso) { return this.cursos.add(curso); }

    @Override
    public String toString() {
        return "Alumno {" + "nombre = " + nombre + ", DNI = " 
               + DNI + ", credito = " + credito + ", cursos = " + cursos.size() + '}';
    }

    @Override
    public int compareTo(Alumno t) {
        return this.nombre.compareTo(t.nombre);
    }
}
