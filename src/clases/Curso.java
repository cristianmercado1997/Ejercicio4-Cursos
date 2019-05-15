package clases;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Curso implements Cloneable {
    private String titulo;
    protected LocalDate fechaIni;
    protected LocalDate fechaFinalizacion;
    private double precioMatri;
    protected HashSet <Alumno> alumnosMatriculados;
    protected HashSet <Alumno> almunosAptos;
    protected int numAlumnos;
    
    protected Curso(String titulo, double precioMatricula, LocalDate FechaI, LocalDate FechaF) {
        this.alumnosMatriculados = new HashSet<>();
        this.almunosAptos = new HashSet<>();
        this.titulo = titulo;
        this.precioMatri = precioMatricula;
        this.fechaIni = FechaI;
        this.fechaFinalizacion = FechaF;
    }
    
  
    public String getTitulo() { return titulo; }
    public LocalDate getFechaIni() { return fechaIni; }
    public LocalDate getFechaFinalizacion() { return fechaFinalizacion; }
    public double getPrecioMatri() { return precioMatri; }
    public int getNumAlumnos() { return numAlumnos; }
    public HashSet<Alumno> getAlumnosMatriculados() {
        return new HashSet<>(this.alumnosMatriculados);
    }

    public HashSet<Alumno> getAlmunosAptos() {
        return new HashSet<>(this.almunosAptos);
    }
    
  
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setNumAlumnos(int numAlumnos) { this.numAlumnos = numAlumnos; }
    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public void setPrecioMatri(double precioMatri) {
        this.precioMatri = precioMatri;
    }

    public void setAlumnosMatriculados(HashSet<Alumno> alumnosMatriculados) {
        this.alumnosMatriculados = alumnosMatriculados;
    }

    public void setAlmunosAptos(HashSet<Alumno> almunosAptos) {
        this.almunosAptos = almunosAptos;
    } 
    
  
    public boolean isTerminado() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual.equals(this.fechaFinalizacion);
    }
    
    public boolean isAlumnoApto(Alumno alumno) {
        return this.almunosAptos.contains(alumno);
    }
    
    public boolean matricularAlumno(Alumno alumno) {
        if(alumno.getCredito() >= this.precioMatri) {
            if(alumno.agregarCurso(this)) {
                this.alumnosMatriculados.add(alumno);
                alumno.decrementarCredito(precioMatri);
                this.numAlumnos++;
                return true;
            }
        }
        return false;
    }
    
    protected abstract boolean cursoSuperado(Alumno al);
    public boolean calificar() {
        for(Alumno al : this.alumnosMatriculados) {
            if(cursoSuperado(al)) {
                this.almunosAptos.add(al);
            }
        }
        return true;
    }
    
    public LinkedList<Alumno> getListaAlumnos() {
        LinkedList<Alumno> lista = new LinkedList<>();
        for(Alumno al : this.alumnosMatriculados) {
            lista.add(al);
        }
        Collections.sort(lista, new CompararAlumnos());
        return lista;
    }

    protected Curso copia() {
        try {
            Curso copiaCurso = (Curso) super.clone();
            copiaCurso.almunosAptos = new HashSet<>();
            copiaCurso.alumnosMatriculados = new HashSet<>();
            return copiaCurso;
        } catch(CloneNotSupportedException e) {
            System.err.print("Error, No Se Puede Clonar el Curso");
        }
        return null;
    }
}