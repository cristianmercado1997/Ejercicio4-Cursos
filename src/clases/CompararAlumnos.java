package clases;

import java.util.Comparator;

public class CompararAlumnos implements Comparator<Alumno> {

    @Override
    public int compare(Alumno t, Alumno t1) {
        int c1 = t.compareTo(t1);
        int c2 = t.getDNI().compareTo(t1.getDNI());
        
        if(c1 == 0) {
            return c2;
        }else{
            return c1;
        }
    }
}
