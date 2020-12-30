/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaledd;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author josep
 */
public class principal {

    static Scanner scanner = new Scanner(System.in);
    //Este es el arreglo de objetos sin tamaño
    static AlumnoClass alumnos[];
    static AlumnoClass alumnosBaja[]; //arreglo que contiene los alumnos por darse de baja

    public static void Menu() {

        boolean repetir = true;
        while (repetir) {
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

            System.out.println("Ingrese una opcion: ");
            int op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1: {
                    System.out.println("La lista original de alumnos ordenados por apellido es: ");
                    OrdenaAlumnosApe(alumnos, 0, (alumnos.length - 1));//ordena los objetos alumno por apellido paterno
                    //sigue mostrarlos // Ya los muestra ;) Pero falta darle forma, es temporal la muestra de datos
                    for (AlumnoClass alumno : alumnos) {
                        System.out.println(alumno.getNombres() + " " + alumno.getApPat() + " " + alumno.getApMat()
                                + " " + alumno.getNoControl() + " " + alumno.getCarrera() + " " + alumno.getSemestre()
                                + " " + alumno.getPromGral());
                    }

                    break;
                }

                case 2: {
                    //Este espacio esta modificado debido a que no se aplicaba la busqueda binaria **AUN ESTA EN PROCESO**
                    System.out.println("Ingrese carrera: ");
                    String carrera = scanner.nextLine();
                    System.out.println("========Alumnos de " + carrera + "========");

                    String[] carreras = new String[alumnos.length];
                    int in = 0;
                    for (AlumnoClass alumno : alumnos) {
                        carreras[in] = alumno.getCarrera();
                        in++;
                    }
                    for (String Carrera : carreras) {
                        System.out.println(Carrera);
                    }

                    //AVISO: POR ALGUNA RAZÓN LA POSICION [0] DEL ARRAY NO ES TOMADA EN CUENTA Y ES COMO SI NO HUBIERA VALOR
                    //PERO LAS DEMAS POSICIONES SI SON CONOCIDAS. PARECE QUE SOLO ES ESE EL UNICO FALLO QUE HE ENCOTRADO
                    //EL METODO FUNCIONA PERFECTAMENTE Y SOLO FALTA APLICARLO DE MANERA CORRECTA AL PROBLEMA
                    //EL METODO FUE CAMBIADO DE RECURSIVO A ITERATIVO (USANDO WHILE) PARA CHECAR SI ESE ERA EL FALLO.
                    //EL CODIGO ORIGINAL AQUI: https://parzibyte.me/blog/2018/10/31/busqueda-binaria-java-arreglos-cadenas/
                    System.out.println("Posicion: " + Arrays.binarySearch(alumnos, carrera));
                    System.out.println("Posicion con metodo: " + busquedaBinariaConWhile(carreras, carrera));
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
                    repetir = false;
                    break;
                }
                default: {
                    System.out.println("Ingrese una opción válida");
                    Menu();
                    break;
                }
            }
        }
    }

    //Metodo de busqueda binaria con Strings
    public static int busquedaBinariaConWhile(String[] arreglo, String busqueda) {

        int izquierda = 0, derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            // Calculamos las mitades...
            int indiceDelElementoDelMedio = (int) Math.floor((izquierda + derecha) / 2);
            String elementoDelMedio = arreglo[indiceDelElementoDelMedio];

            // Primero vamos a comparar y ver si el resultado es negativo, positivo o 0
            int resultadoDeLaComparacion = busqueda.compareToIgnoreCase(elementoDelMedio);

            // Si el resultado de la comparación es 0, significa que ambos elementos son iguales
            // y por lo tanto quiere decir que hemos encontrado la búsqueda
            if (resultadoDeLaComparacion == 0) {
                return indiceDelElementoDelMedio;
            }

            // Si no, entonces vemos si está a la izquierda o derecha
            if (resultadoDeLaComparacion < 0) {
                derecha = indiceDelElementoDelMedio - 1;
            } else {
                izquierda = indiceDelElementoDelMedio + 1;
            }
        }
        // Si no se rompió el ciclo ni se regresó el índice, entonces el elemento no
        // existe
        return -1;
    }

    //Ordena los alumnos por apellido paterno mediante quicksort
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

    //Ordena los alumnos por numero de control mediante quicksort
    public static void OrdenaAlumnosNumC(AlumnoClass[] arreglo, int izq, int der) {
        AlumnoClass pivote = arreglo[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        AlumnoClass aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (arreglo[i].getNoControl() <= pivote.getNoControl() && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (arreglo[j].getNoControl() > pivote.getNoControl()) {
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
            OrdenaAlumnosNumC(arreglo, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            OrdenaAlumnosNumC(arreglo, j + 1, der);      // ordenamos subarray derecho
        }
    }

    //Ordena los alumnos por cantidad de materias en especial mediante quicksort
    //Se usa este metodo para el inciso C
    public static void OrdenaAlumnosMatEsp(AlumnoClass[] arreglo, int izq, int der) {
        AlumnoClass pivote = arreglo[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        AlumnoClass aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (arreglo[i].getContEsp() <= pivote.getContEsp() && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (arreglo[j].getContEsp() > pivote.getContEsp()) {
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
            OrdenaAlumnosNumC(arreglo, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            OrdenaAlumnosNumC(arreglo, j + 1, der);      // ordenamos subarray derecho
        }
    }

    //Aqui esta el metodo que pide los registros de los alumnos y lo almacena en el arreglo de objetos
    public static void GuardarDatos(int incremento) {
        String materias[] = new String[6];
        int Calif[] = new int[6];
        int Status[] = new int[6];
        int ContEsp = 0; //Contador que nos dice cuantas materias en especial tiene "x" alumno

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
            if (Status[i] == 3) {
                ContEsp++;   //Si la materia esta en especial, el contador se incrementa
            }
        }
        //Calculamos promedio en base al arreglo de calificaciones
        int x = 0;
        for (int i = 0; i < 6; i++) {
            x = x + Calif[i];
        }
        int Prom = x / 6;
        //Se guardan los datos en el arreglo de objetos, la posicion depende del valor que se le pasa
        //por parametro
        alumnos[incremento] = new AlumnoClass(nControl, Nombre, ApPat, ApMat, Semestre, Carrera, materias, Calif, Status, Prom, ContEsp);
    }

    //metodo de búsqueda binaria, **********falta acoplar a nuestro proyecto*********
    public static int binarySearch(int[] array, int minLimit, int maxLimit, int value) {
        if (maxLimit >= 0 && array[minLimit] <= value && array[maxLimit] >= value) {
            int mid = (minLimit + maxLimit) / 2;
            System.out.println(String.format("Límite inferior %d límite superior %d valor en el arreglo %d valor a buscar %d", minLimit, maxLimit, array[mid], value));
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                return binarySearch(array, mid + 1, maxLimit, value);
            }
            return binarySearch(array, minLimit, mid - 1, value);
        }
        return -1;
    }

    public static AlumnoClass[] eliminaElemento(AlumnoClass[] arreglo, int posicion) {
        AlumnoClass[] aux = new AlumnoClass[arreglo.length - 1];
        for (int i = 0; i < arreglo.length; i++) {
            if (i != posicion) {                //éste método únicamente nos sirve para eliminar
                aux[i] = arreglo[i];         // los alumnos reprobados que vayamos pasando al arreglo
            }
        }
        return aux;
    }

    public static void MetodoParaPruebas() {
        //clase auxiliar para probar código

        String[] arreglo = {"Chris", "Claire", "Django", "John", "Leon", "Morty", "Rick", "Saul", "Tuco", "Walter"};

        String busqueda = "Chris";
        // Ahora con la que usa el ciclo while
        int indiceDelElementoBuscado = busquedaBinariaConWhile(arreglo, busqueda);
        System.out.println("[Con ciclo While] -- El elemento buscado ("
                + busqueda
                + ") se encuentra en el index "
                + indiceDelElementoBuscado);
    }

    public static void main(String[] args) {

        MetodoParaPruebas();
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
}
