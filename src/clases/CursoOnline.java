package clases;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class CursoOnline extends Curso {
    
    private final int nivel;
    private HashMap <Alumno, Integer> seguimiento;
    private final HashSet <Curso> cursosPrevios;

    public CursoOnline(String titulo, double precioMatricula, 
            LocalDate FechaI, LocalDate FechaF, int nivel, Curso... cursos) {
        super(titulo, precioMatricula, FechaI, FechaF);
        this.cursosPrevios = new HashSet<>();
        this.seguimiento = new HashMap<>();
        this.nivel = nivel;
        Collections.addAll(cursosPrevios, cursos);
    }
    
  
    public int getNivel(Alumno al) {
        if(!this.seguimiento.containsKey(al)) {
            return -1;
        }else{
            return this.seguimiento.get(al);
        }
    }
    
    public boolean superarNivel(Alumno al) {
        if(this.alumnosMatriculados.contains(al)) {
            if(this.seguimiento.get(al) != null) {
                int nivelActual = this.seguimiento.get(al);
                if(nivelActual < this.nivel) {
                    nivelActual++;
                    this.seguimiento.put(al, nivelActual);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean matricularAlumno(Alumno alumno) {
        for(Curso crs : this.cursosPrevios) {
            if(!crs.isAlumnoApto(alumno)) {
                return false;
            }
        }
        if(super.matricularAlumno(alumno)) {
            this.seguimiento.put(alumno, 0);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    protected boolean cursoSuperado(Alumno al) {
        if(this.seguimiento.get(al) != null) {
            int nivelActual = this.seguimiento.get(al);
            return (nivelActual >= (int)(this.nivel / 2));
        }
        return false;
    }

    @Override
    public CursoOnline copia() {
        CursoOnline copia = (CursoOnline) super.copia();
        copia.seguimiento = new HashMap<>();
        return copia;
    }
}
