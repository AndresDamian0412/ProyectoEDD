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
public class AlumnoClass {

    //Esta es la clase del Objeto Alumno.
    //Se ha puesto como arreglo de strings Materias pues son 6 materias que se tienen que almacenar. Si tienen otra idea hagan cambios y expliquen en el was xd
    int NoControl;
    String Nombres, ApPat, ApMat;
    int Semestre;
    String Carrera, Materias[];
    int Calif[], Status[], PromGral;
    int ContEsp; //contador de materias en especial, sirve para ordenar alumnos que tengan materias en especial

    String MateriasRecursadas[];

    public AlumnoClass(int NoControl, String Nombres, String ApPat, String ApMat, int Semestre, String Carrera, String[] Materias, int[] Calif, int[] Status, int PromGral, int ContEsp) {
        this.NoControl = NoControl;
        this.Nombres = Nombres;
        this.ApPat = ApPat;
        this.ApMat = ApMat;
        this.Semestre = Semestre;
        this.Carrera = Carrera;
        this.Materias = Materias;
        this.Calif = Calif;
        this.Status = Status;
        this.PromGral = PromGral;
        this.ContEsp = ContEsp;
    }

    //Este constructor fue creado para ser usado en la opcion 4
    public AlumnoClass(int NoControl, String Nombres, String ApPat, String ApMat, String[] MateriasRecursadas) {
        this.NoControl = NoControl;
        this.Nombres = Nombres;
        this.ApPat = ApPat;
        this.ApMat = ApMat;
        this.MateriasRecursadas = MateriasRecursadas;
    }

    public String[] getMateriasRecursadas() {
        return MateriasRecursadas;
    }

    public void setMateriasRecursadas(String[] MateriasRecursadas) {
        this.MateriasRecursadas = MateriasRecursadas;
    }

    public AlumnoClass() {
    }

    public int getNoControl() {
        return NoControl;
    }

    public void setNoControl(int NoControl) {
        this.NoControl = NoControl;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApPat() {
        return ApPat;
    }

    public void setApPat(String ApPat) {
        this.ApPat = ApPat;
    }

    public String getApMat() {
        return ApMat;
    }

    public void setApMat(String ApMat) {
        this.ApMat = ApMat;
    }

    public int getSemestre() {
        return Semestre;
    }

    public void setSemestre(int Semestre) {
        this.Semestre = Semestre;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public String[] getMaterias() {
        return Materias;
    }

    public void setMaterias(String[] Materias) {
        this.Materias = Materias;
    }

    public int[] getCalif() {
        return Calif;
    }

    public void setCalif(int[] Calif) {
        this.Calif = Calif;
    }

    public int[] getStatus() {
        return Status;
    }

    public void setStatus(int[] Status) {
        this.Status = Status;
    }

    public int getPromGral() {
        return PromGral;
    }

    public void setPromGral(int PromGral) {
        this.PromGral = PromGral;
    }

    public int getContEsp() {
        return ContEsp;
    }

    public void setContEsp(int ContEsp) {
        this.ContEsp = ContEsp;
    }
}
