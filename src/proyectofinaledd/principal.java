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
        Menu();

    }

    public static void Menu() {
        int op = 0;
        System.out.println("=====SISTEMA DE INFORMACIÓN DE ALUMNOS NIVEL MEDIO SUPERIOR=====");
        System.out.println("Seleccione una de las siguienes opciones");
        System.out.println("1) Listado de alumnos");
        System.out.println("2) Consultar alumnos por carrera");
        System.out.println("3) Alumnos candidatos a baja por reprobación");
        System.out.println("4) Alumnos que recursarán alguna materia");
        System.out.println("5) Alumnos que llevarán alguna materia en especial");
        System.out.println("6) Alumnos con el promedio más bajo");
        System.out.println("7) Alumnos con el primedio más alto");
        System.out.println("8) Alumnos candidatos a beca");
        System.out.println("9) Salir");

        switch (op) {
            case 1: {
                System.out.println("La lista original de alumnos ordenados por apellido es: ");
                OrdenaAlumnosApe(alumnos,0,(alumnos.length-1));//ordena los objetos alumno por apellido paterno
                //sigue mostrarlos
                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {

                break;
            }
            case 5: {

                break;
            }
            case 6: {

                break;
            }
            case 7: {

                break;
            }
            case 8: {

                break;
            }
            case 9: {
                System.out.println("GRACIAS POR UTILIZAR EL SISTEMA");
                break;
            }
            default: {
                System.out.println("Ingrese una opción válida");
                Menu();
                break;
            }
        }
    }

    public static void OrdenaAlumnosApe(AlumnoClass[] arreglo, int izq, int der) {

        AlumnoClass pivote = arreglo[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        AlumnoClass aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (arreglo[i].getApPat().compareTo(pivote.getApPat()) <= 0 && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (arreglo[j].getApPat().compareTo(pivote.getApPat()) > 0) {
                j--;           // busca elemento menor que pivote
            }
            if (i < j) {                        // si no se han cruzado                      
                aux = arreglo[i];                      // los intercambia
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
            }
        }

        arreglo[izq] = arreglo[j];      // se coloca el pivote en su lugar de forma que tendremos                                    
        arreglo[j] = pivote;      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1) {
            OrdenaAlumnosApe(arreglo, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            OrdenaAlumnosApe(arreglo, j + 1, der);      // ordenamos subarray derecho
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