/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaledd;

/**
 *
 * @author josep
 */
public class AlumMatRecu {
    int Status;
    String Materia;
    int Calificacion;
    String Carrera;

    public AlumMatRecu(int Status, String Materia, int Calificacion) {
        this.Status = Status;
        this.Materia = Materia;
        this.Calificacion = Calificacion;
    }

    public AlumMatRecu(int Status, String Materia, int Calificacion, String Carrera) {
        this.Status = Status;
        this.Materia = Materia;
        this.Calificacion = Calificacion;
        this.Carrera = Carrera;
    }

    public AlumMatRecu(int Status, int Calificacion) {
        this.Status = Status;
        this.Calificacion = Calificacion;
    }
       
    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }
    
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String Materia) {
        this.Materia = Materia;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }

    
    
    
}
