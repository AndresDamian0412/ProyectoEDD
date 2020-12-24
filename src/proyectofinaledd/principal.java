/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaledd;

import java.util.Scanner;

/**
 *
 * @author josep
 */
public class principal {
    static Scanner scanner = new Scanner(System.in);
    //Este es el arreglo de objetos sin tamaño
    static AlumnoClass alumnos[];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Se pide el tamaño del arreglo [Esto lo hice de forma temporal por si no habia otra manera
        //de fijar el tamaño del array de objetos]
        System.out.println("Ingrese tamaño del arreglo: ");
        int tam = Integer.parseInt(scanner.nextLine());
        //Este es el tamaño del arreglo con el tamaño pasado por teclado
        alumnos = new AlumnoClass[tam];
        int incremento = 0; //Inicio una variable que servirá como contador de las vueltas en cada registro
        //Aqui se realiza el ciclo de los registros, se detiene cuando la variable anterior
        //es igual al tamaño del arreglo de objetos pasado por teclado
        while (incremento != tam) {
            GuardarDatos(incremento);//Pasamos por parametro el valor de la variable que incrementa en cada vuelta
            incremento++;
        }

    }

    //Aqui esta el metodo que pide los registros de los alumnos y lo almacena en el arreglo de objetos
    public static void GuardarDatos(int incremento) {
        String materias[] = new String[6];
        int Calif[] = new int[6];
        int Status[] = new int[6];
        
        //Pide los datos
        System.out.println("Ingrese numero de control: ");
        int nControl = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese nombre(s)");
        String Nombre = scanner.nextLine();
        System.out.println("Ingrese apellido paterno: ");
        String ApPat = scanner.nextLine();
        System.out.println("Ingrese apellido materno: ");
        String ApMat = scanner.nextLine();
        System.out.println("Ingrese semestre (numero): ");
        int Semestre = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese carrera: ");
        String Carrera = scanner.nextLine();
        //Ingresamos cada materia en un arreglo
        for (int i = 0; i < 6; i++) {
            System.out.println("Ingrese materia [" + (i + 1) + "] :");
            materias[i] = scanner.nextLine();
        }
        //Lo mismo de almacenar la calificacion en un arreglo
        for (int i = 0; i < 6; i++) {
            System.out.println("Ingrese calificacion de materia [" + (i + 1) + "] :");
            Calif[i] = Integer.parseInt(scanner.nextLine());
        }
        //Lo mismo de almacenar el status en un arreglo
        System.out.println("Status: \n1.- Tomando por primera vez \n2.-Recursando \n3.-Especial");
        for (int i = 0; i < 6; i++) {
            System.out.println("Ingrese status de materia [" + (i + 1) + "] :");
            Status[i] = Integer.parseInt(scanner.nextLine());
        }
        //Calculamos promedio en base al arreglo de calificaciones
        int x = 0;
        for (int i = 0; i < 6; i++) {
            x = x + Calif[i];
        }
        int Prom = x / 6;
        //Se guardan los datos en el arreglo de objetos, la posicion depende del valor que se le pasa
        //por parametro
        alumnos[incremento] = new AlumnoClass(nControl, Nombre, ApPat, ApMat, Semestre, Carrera, materias, Calif, Status, Prom);
    }
}
