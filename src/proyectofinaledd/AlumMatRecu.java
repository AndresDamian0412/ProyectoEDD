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

    public AlumMatRecu(int Status, String Materia) {
        this.Status = Status;
        this.Materia = Materia;
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
    
    
}
