package clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class CursoPresencial extends Curso {
    
    private final int cupo;
    private final int numMinAsistencias;
    private HashMap <Integer, HashSet<Alumno>> asistencias;

    public CursoPresencial(String titulo, double precioMatricula, 
            LocalDate FechaI, LocalDate FechaF, int cupo, int numAsistencias) {
        super(titulo, precioMatricula, FechaI, FechaF);
        this.asistencias = new HashMap<>();
        this.cupo = cupo;
        this.numMinAsistencias = numAsistencias;
    }
    
    public int getPlazasLibres() { return (this.cupo - this.numAlumnos); }
    public boolean registroAsistencia(int dia, Alumno al) {
        int diaInicio = this.fechaIni.getDayOfYear();
        int diaFinalizacion = this.fechaFinalizacion.getDayOfYear();
        int diferencia = diaFinalizacion - diaInicio;
        if(this.alumnosMatriculados.contains(al) && dia <= diferencia) {
            HashSet <Alumno> listaAlumnos = this.asistencias.get(dia);
            if(listaAlumnos != null) {
                listaAlumnos.add(al);
                this.asistencias.put(dia, listaAlumnos);
            }else{
                listaAlumnos = new HashSet<>();
                listaAlumnos.add(al);
                this.asistencias.put(dia, listaAlumnos);
            }
            return true;
        }
        return false;
    }
    
    public int getAsistenciasAlumno(Alumno al) {
        int conteoDias = 0;
        for(int dia : this.asistencias.keySet()) {
            HashSet <Alumno> listaAlumnos = this.asistencias.get(dia);
            if(listaAlumnos != null) {
                if(listaAlumnos.contains(al)) {
                    conteoDias++;
                }
            }
        }
        return conteoDias;
    }

    @Override
    public boolean matricularAlumno(Alumno alumno) {
        if(getPlazasLibres() > 0) {
            return super.matricularAlumno(alumno);
        }
        return false;
    }
    
    @Override
    protected boolean cursoSuperado(Alumno al) {
        return (getAsistenciasAlumno(al) >= this.numMinAsistencias);
    }

    @Override
    public CursoPresencial copia() {
        CursoPresencial copia = (CursoPresencial) super.copia();
        copia.asistencias = new HashMap<>();
        return copia;
    }
}
