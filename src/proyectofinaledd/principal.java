/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaledd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author josep
 * @author andre
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
                        System.out.println("NOMBRE: " + alumno.getApPat() + " " + alumno.getApMat() + " " + alumno.getNombres()
                                + "\t" + " NO. CONTROL: " + alumno.getNoControl() + "\t" + " CARRERA: " + alumno.getCarrera() + "\t" + " SEMESTRE: "
                                + alumno.getSemestre() + "\t" + " PROMEDIO: " + alumno.getPromGral());
                    }
                    break;
                }

                case 2: {
                    //Se crea una copia del arreglo de alumnos para no afectar su contenido accidentalmente
                    AlumnoClass[] aux = new AlumnoClass[10];
                    aux = alumnos;
                    //Se crea un arraylist para guardar los resultados de la busqueda
                    ArrayList<AlumnoClass> encontrados = new ArrayList<AlumnoClass>();
                    //Se ordenan los alumnos del array en base a su carrera para que la busqueda binaria funcione
                    OrdenaAlumnosCarr(aux, 0, aux.length - 1);

                    System.out.println("Ingrese carrera: ");
                    String busqueda = scanner.nextLine();

                    int posicion; //guarda la posicion en la que encontro un alumno de esa carrera
                    posicion = binarySearchCarrera(aux, 0, aux.length - 1, busqueda);//hace la busqueda y asigna posicion
                    while (posicion != -1) {         //Mientras encuentre alumnos
                        encontrados.add(aux[posicion]); //agrega el alumno al arraylist
                        aux = eliminaElemento(aux, posicion);  //elimina el alumno que ya encontró
                        posicion = binarySearchCarrera(aux, 0, aux.length - 1, busqueda); //vuelve a buscar
                    }
                    //Ahora hay que vaciar los objetos en un arreglo normal para poder utilizar el metodo de ordenamiento
                    AlumnoClass[] aux2 = new AlumnoClass[encontrados.size()];
                    for (int i = 0; i < encontrados.size(); i++) {
                        aux2[i] = encontrados.get(i);
                    }
                    OrdenaAlumnosApe(aux2, 0, aux2.length - 1);
                    for (AlumnoClass aux21 : aux2) {
                        System.out.println("NOMBRE(S):" + aux21.getApPat() + " " + aux21.getApMat() + " " + aux21.getNombres()
                                + "\t" + " NO. CONTROL: " + aux21.getNoControl() + "\t" + " CARRERA: " + aux21.getCarrera() + "\t" + " SEMESTRE: " + aux21.getSemestre()
                                + "\t" + " PROMEDIO: " + aux21.getPromGral());
                    }
                    break;
                }
                case 3: {
                    
                    int busqueda = 3;
                    boolean tiene_recursadas = false;
                    ArrayList<AlumnoClass> alumMatRecur = new ArrayList<>();

                    //Hacemos un ciclo for para que en cada alumno se haga lo siguiente:
                    for (AlumnoClass arrayOriginal : alumnos) {
                        ArrayList<AlumMatRecu> encontrados = new ArrayList<>();
                        //Tomamos los arreglos de materias y status del alumno para almacenarlos en unos auxiliares que seran usados despues
                        int[] Status = arrayOriginal.getStatus();
                        String[] Materias = arrayOriginal.getMaterias();
                        int[] Calificaciones = arrayOriginal.getCalif();
                        String Carrera = arrayOriginal.getCarrera();

                        //Creamos un arreglo de objetos de las materias recursadas que sera usada despues
                        AlumMatRecu[] materiasRecursadasarray = new AlumMatRecu[arrayOriginal.getMaterias().length];
                        //En este ciclo for se almacena en el arreglo anterior creado en la posicion dicha los elementos que estan almacenados en cada array (Status y Materias)
                        for (int i = 0; i < Status.length; i++) {
                            materiasRecursadasarray[i] = new AlumMatRecu(Status[i], Materias[i], Calificaciones[i], Carrera);
                        }

                        //Ordenamos por el metodo quicksort, usando como parametro el numero de Status, el array al que le almacenamos los elementos anteriores
                        OrdenaMateriasRecursadas(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1);
                        //El siguiente proceso busca entre el arreglo de las materias ordenadas si existe alguna
                        //en status de recursada, si es asi pues elimina del arreglo de materias y la agrega a una lista.
                        int posicion;
                        posicion = binarySearchStatus(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1, busqueda);

                        String[] materiasEncontradas = null;//Se puso aqui pq no dejaba usarlo fuera del if
                        //Este if es solo para filtrar los alumnos que tienen materias recursadas, cuando encuentra al menos una se activa el boleano
                        if (posicion >= 0 && materiasRecursadasarray[posicion].getCalificacion() < 70) {
                            tiene_recursadas = true;
                            //Es el mismo procedimiento que el que se usa en el case 2 para agregar y eliminar elementos.
                            while (posicion != -1 && materiasRecursadasarray[posicion].getCalificacion() < 70) {
                                encontrados.add(materiasRecursadasarray[posicion]);
                                materiasRecursadasarray = eliminaMateria(materiasRecursadasarray, posicion);
                                posicion = binarySearchStatus(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1, busqueda);
                            }
                            //Ahora hay que vaciar los objetos en un arreglo de objetos
                            AlumMatRecu[] aux = new AlumMatRecu[encontrados.size()];
                            for (int i = 0; i < encontrados.size(); i++) {
                                aux[i] = encontrados.get(i);
                            }
                            //Filtramos el arreglo anterior a uno de cadenas porque solo necesitamos las materias
                            materiasEncontradas = new String[aux.length];
                            for (int i = 0; i < aux.length; i++) {
                                materiasEncontradas[i] = aux[i].getMateria();
                            }

                        } else {
                            //Si no encuentra materias recursadas el valor del boleano no cambia
                            tiene_recursadas = false;
                        }

                        //Si encuentra que tiene recursadas lo agrega al arrayList creado para almacenar a todos los alumnos
                        if (tiene_recursadas == true) {
                            AlumnoClass conRecursadas = new AlumnoClass(arrayOriginal.getNoControl(), arrayOriginal.getNombres(), arrayOriginal.getApPat(), arrayOriginal.getApMat(), arrayOriginal.getCarrera() ,materiasEncontradas);
                            alumMatRecur.add(conRecursadas);
                        }

                    }
                    //Vaciamos lo que contenia el arrayList en un nuevo arreglo para poder mostrar los datos obtenidos
                    AlumnoClass[] alumnosConRecursadas = new AlumnoClass[alumMatRecur.size()];
                    for (int i = 0; i < alumMatRecur.size(); i++) {
                        alumnosConRecursadas[i] = alumMatRecur.get(i);
                    }
                    //Ordenamos nuevamente el arreglo con los alumnos con materias recursadas tomando como parametro su numero de control
                    OrdenaAlumnosNumC(alumnosConRecursadas, 0, alumnosConRecursadas.length - 1);
                    System.out.println("========ALUMNOS QUE SE DARÁN DE BAJA POR REPROBACIÓN========");
                    for (AlumnoClass aux2 : alumnosConRecursadas) {
                        String[] materias = aux2.getMateriasRecursadas();
                        System.out.println("NO. CONTROL: " + aux2.getNoControl() + "\t" + "NOMBRE: " + aux2.getApPat() + " "
                                + aux2.getApMat() + " " + aux2.getNombres() + "\t" + "CARRERA: " + aux2.getCarrera());

                        
                    }
                    
                    break;
                }
                case 4: {

                    int busqueda = 1;
                    boolean tiene_recursadas = false;
                    ArrayList<AlumnoClass> alumMatRecur = new ArrayList<>();

                    //Hacemos un ciclo for para que en cada alumno se haga lo siguiente:
                    for (AlumnoClass arrayOriginal : alumnos) {
                        ArrayList<AlumMatRecu> encontrados = new ArrayList<>();
                        //Tomamos los arreglos de materias y status del alumno para almacenarlos en unos auxiliares que seran usados despues
                        int[] Status = arrayOriginal.getStatus();
                        String[] Materias = arrayOriginal.getMaterias();
                        int[] Calificaciones = arrayOriginal.getCalif();

                        //Creamos un arreglo de objetos de las materias recursadas que sera usada despues
                        AlumMatRecu[] materiasRecursadasarray = new AlumMatRecu[arrayOriginal.getMaterias().length];
                        //En este ciclo for se almacena en el arreglo anterior creado en la posicion dicha los elementos que estan almacenados en cada array (Status y Materias)
                        for (int i = 0; i < Status.length; i++) {
                            materiasRecursadasarray[i] = new AlumMatRecu(Status[i], Materias[i], Calificaciones[i]);
                        }

                        //Ordenamos por el metodo quicksort, usando como parametro el numero de Status, el array al que le almacenamos los elementos anteriores
                        OrdenaMateriasRecursadas(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1);
                        //El siguiente proceso busca entre el arreglo de las materias ordenadas si existe alguna
                        //en status de recursada, si es asi pues elimina del arreglo de materias y la agrega a una lista.
                        int posicion;
                        posicion = binarySearchStatus(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1, busqueda);

                        String[] materiasEncontradas = null;//Se puso aqui pq no dejaba usarlo fuera del if
                        //Este if es solo para filtrar los alumnos que tienen materias recursadas, cuando encuentra al menos una se activa el boleano
                        if (posicion >= 0 && materiasRecursadasarray[posicion].getCalificacion() < 70) {
                            tiene_recursadas = true;
                            //Es el mismo procedimiento que el que se usa en el case 2 para agregar y eliminar elementos.
                            while (posicion != -1 && materiasRecursadasarray[posicion].getCalificacion() < 70) {
                                encontrados.add(materiasRecursadasarray[posicion]);
                                materiasRecursadasarray = eliminaMateria(materiasRecursadasarray, posicion);
                                posicion = binarySearchStatus(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1, busqueda);
                            }
                            //Ahora hay que vaciar los objetos en un arreglo de objetos
                            AlumMatRecu[] aux = new AlumMatRecu[encontrados.size()];
                            for (int i = 0; i < encontrados.size(); i++) {
                                aux[i] = encontrados.get(i);
                            }
                            //Filtramos el arreglo anterior a uno de cadenas porque solo necesitamos las materias
                            materiasEncontradas = new String[aux.length];
                            for (int i = 0; i < aux.length; i++) {
                                materiasEncontradas[i] = aux[i].getMateria();
                            }

                        } else {
                            //Si no encuentra materias recursadas el valor del boleano no cambia
                            tiene_recursadas = false;
                        }

                        //Si encuentra que tiene recursadas lo agrega al arrayList creado para almacenar a todos los alumnos
                        if (tiene_recursadas == true) {
                            AlumnoClass conRecursadas = new AlumnoClass(arrayOriginal.getNoControl(), arrayOriginal.getNombres(), arrayOriginal.getApPat(), arrayOriginal.getApMat(), materiasEncontradas);
                            alumMatRecur.add(conRecursadas);
                        }

                    }
                    //Vaciamos lo que contenia el arrayList en un nuevo arreglo para poder mostrar los datos obtenidos
                    AlumnoClass[] alumnosConRecursadas = new AlumnoClass[alumMatRecur.size()];
                    for (int i = 0; i < alumMatRecur.size(); i++) {
                        alumnosConRecursadas[i] = alumMatRecur.get(i);
                    }
                    //Ordenamos nuevamente el arreglo con los alumnos con materias recursadas tomando como parametro su numero de control
                    OrdenaAlumnosNumC(alumnosConRecursadas, 0, alumnosConRecursadas.length - 1);
                    System.out.println("========ALUMNOS CON MATERIAS A RECURSAR========");
                    for (AlumnoClass aux2 : alumnosConRecursadas) {
                        String[] materias = aux2.getMateriasRecursadas();
                        System.out.print("NO. CONTROL: " + aux2.getNoControl() + "\t" + "NOMBRE: " + aux2.getApPat() + " "
                                + aux2.getApMat() + " " + aux2.getNombres() + "\t" + "MATERIAS A RECURSAR: ");

                        for (String materia : materias) {
                            System.out.print(materia + " ");
                        }
                        System.out.println("");
                    }

                    break;
                }
                case 5: {
                    int busqueda = 2;
                    boolean tiene_recursadas = false;
                    ArrayList<AlumnoClass> alumMatRecur = new ArrayList<>();

                    //Hacemos un ciclo for para que en cada alumno se haga lo siguiente:
                    for (AlumnoClass arrayOriginal : alumnos) {
                        ArrayList<AlumMatRecu> encontrados = new ArrayList<>();
                        //Tomamos los arreglos de materias y status del alumno para almacenarlos en unos auxiliares que seran usados despues
                        int[] Status = arrayOriginal.getStatus();
                        String[] Materias = arrayOriginal.getMaterias();
                        int[] Calificaciones = arrayOriginal.getCalif();

                        //Creamos un arreglo de objetos de las materias recursadas que sera usada despues
                        AlumMatRecu[] materiasRecursadasarray = new AlumMatRecu[arrayOriginal.getMaterias().length];
                        //En este ciclo for se almacena en el arreglo anterior creado en la posicion dicha los elementos que estan almacenados en cada array (Status y Materias)
                        for (int i = 0; i < Status.length; i++) {
                            materiasRecursadasarray[i] = new AlumMatRecu(Status[i], Materias[i], Calificaciones[i]);
                        }

                        //Ordenamos por el metodo quicksort, usando como parametro el numero de Status, el array al que le almacenamos los elementos anteriores
                        OrdenaMateriasRecursadas(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1);
                        //El siguiente proceso busca entre el arreglo de las materias ordenadas si existe alguna
                        //en status de recursada, si es asi pues elimina del arreglo de materias y la agrega a una lista.
                        int posicion;
                        posicion = binarySearchStatus(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1, busqueda);

                        String[] materiasEncontradas = null;//Se puso aqui pq no dejaba usarlo fuera del if
                        //Este if es solo para filtrar los alumnos que tienen materias recursadas, cuando encuentra al menos una se activa el boleano
                        if (posicion >= 0 && materiasRecursadasarray[posicion].getCalificacion() < 70) {
                            tiene_recursadas = true;
                            //Es el mismo procedimiento que el que se usa en el case 2 para agregar y eliminar elementos.
                            while (posicion != -1 && materiasRecursadasarray[posicion].getCalificacion() < 70) {
                                encontrados.add(materiasRecursadasarray[posicion]);
                                materiasRecursadasarray = eliminaMateria(materiasRecursadasarray, posicion);
                                posicion = binarySearchStatus(materiasRecursadasarray, 0, materiasRecursadasarray.length - 1, busqueda);
                            }
                            //Ahora hay que vaciar los objetos en un arreglo de objetos
                            AlumMatRecu[] aux = new AlumMatRecu[encontrados.size()];
                            for (int i = 0; i < encontrados.size(); i++) {
                                aux[i] = encontrados.get(i);
                            }
                            //Filtramos el arreglo anterior a uno de cadenas porque solo necesitamos las materias
                            materiasEncontradas = new String[aux.length];
                            for (int i = 0; i < aux.length; i++) {
                                materiasEncontradas[i] = aux[i].getMateria();
                            }

                        } else {
                            //Si no encuentra materias recursadas el valor del boleano no cambia
                            tiene_recursadas = false;
                        }

                        //Si encuentra que tiene recursadas lo agrega al arrayList creado para almacenar a todos los alumnos
                        if (tiene_recursadas == true) {
                            AlumnoClass conRecursadas = new AlumnoClass(arrayOriginal.getNoControl(), arrayOriginal.getNombres(), arrayOriginal.getApPat(), arrayOriginal.getApMat(), materiasEncontradas);
                            alumMatRecur.add(conRecursadas);
                        }

                    }
                    //Vaciamos lo que contenia el arrayList en un nuevo arreglo para poder mostrar los datos obtenidos
                    AlumnoClass[] alumnosConRecursadas = new AlumnoClass[alumMatRecur.size()];
                    for (int i = 0; i < alumMatRecur.size(); i++) {
                        alumnosConRecursadas[i] = alumMatRecur.get(i);
                    }
                    //Ordenamos nuevamente el arreglo con los alumnos con materias recursadas tomando como parametro su numero de control
                    OrdenaAlumnosNumC(alumnosConRecursadas, 0, alumnosConRecursadas.length - 1);
                    System.out.println("========ALUMNOS CON MATERIAS A ESPECIAL========");
                    for (AlumnoClass aux2 : alumnosConRecursadas) {
                        String[] materias = aux2.getMateriasRecursadas();
                        System.out.print("NO. CONTROL: " + aux2.getNoControl() + "\t" + "NOMBRE: " + aux2.getApPat() + " "
                                + aux2.getApMat() + " " + aux2.getNombres() + "\t" + "MATERIAS A ESPECIAL: ");

                        for (String materia : materias) {
                            System.out.print(materia + " ");
                        }
                        System.out.println("");
                    }
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

    //QuickSort con cadenas
    public static void OrdenaCadenas(String[] arreglo, int izq, int der) {

        String pivote = arreglo[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        String aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (arreglo[i].compareTo(pivote) <= 0 && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (arreglo[j].compareTo(pivote) > 0) {
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
            OrdenaCadenas(arreglo, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            OrdenaCadenas(arreglo, j + 1, der);      // ordenamos subarray derecho
        }
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

    //Ordena materias recursadas usando el status de menor a mayor como referencia usando QuickSort
    public static void OrdenaMateriasRecursadas(AlumMatRecu[] arreglo, int izq, int der) {
        AlumMatRecu pivote = arreglo[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        AlumMatRecu aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (arreglo[i].getStatus() <= pivote.getStatus() && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (arreglo[j].getStatus() > pivote.getStatus()) {
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
            OrdenaMateriasRecursadas(arreglo, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            OrdenaMateriasRecursadas(arreglo, j + 1, der);      // ordenamos subarray derecho
        }
    }

    //Ordena los alumnos por la carrera que cursen
    public static void OrdenaAlumnosCarr(AlumnoClass[] arreglo, int izq, int der) {

        AlumnoClass pivote = arreglo[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        AlumnoClass aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (arreglo[i].getCarrera().compareTo(pivote.getCarrera()) <= 0 && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (arreglo[j].getCarrera().compareTo(pivote.getCarrera()) > 0) {
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
            OrdenaAlumnosCarr(arreglo, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            OrdenaAlumnosCarr(arreglo, j + 1, der);      // ordenamos subarray derecho
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
    /*public static void GuardarDatos(int incremento) {
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
    }*/
    //Este método sirve únicamente para guardar objetos de tipo alumno, así facilitanto las pruebas
    public static void CreacionDeObjetos() {
        String[] Materias1 = {"Fisica", "Calculo", "Español", "Geografia", "Musica", "Historia"};
        String[] Materias2 = {"Ingles", "Español", "Arte", "Atronomia", "Fisica", "EDD"};
        String[] Materias3 = {"Calculo", "Historia", "Fisica", "Español", "Geografia", "Artes"};
        String[] Materias4 = {"Español", "Fisica", "Biología", "Arte", "Musica", "Calculo"};
        String[] Materias5 = {"Quimica", "Artes", "Biotecnología", "Aeronautica", "Musica", "Filosofía"};
        String[] Materias6 = {"Español", "Fisica", "Biología", "Arte", "Musica", "Calculo"};
        String[] Materias7 = {"Ingles", "Español", "Arte", "Atronomia", "Fisica", "EDD"};
        String[] Materias8 = {"Quimica", "Artes", "Biotecnología", "Aeronautica", "Musica", "Filosofía"};
        String[] Materias9 = {"Calculo", "Historia", "Fisica", "Español", "Geografia", "Artes"};
        String[] Materias10 = {"Fisica", "Calculo", "Español", "Geografia", "Musica", "Historia"};

        int[] Calificaciones1 = {70, 85, 90, 60, 80, 70};
        int[] Calificaciones2 = {100, 100, 100, 100, 100, 90};
        int[] Calificaciones3 = {90, 80, 90, 95, 100, 70,};
        int[] Calificaciones4 = {70, 70, 70, 60, 50, 70};
        int[] Calificaciones5 = {80, 70, 80, 60, 70, 80};
        int[] Calificaciones6 = {90, 85, 85, 90, 80, 70};
        int[] Calificaciones7 = {80, 80, 80, 75, 90, 90};
        int[] Calificaciones8 = {70, 70, 65, 80, 65, 70};
        int[] Calificaciones9 = {100, 100, 100, 90, 90, 80};
        int[] Calificaciones10 = {100, 90, 90, 70, 80, 100};

        int[] status1 = {1, 1, 1, 1, 1, 3};
        int[] status2 = {1, 2, 2, 1, 1, 1,};
        int[] status3 = {1, 1, 1, 1, 1, 1};
        int[] status4 = {2, 2, 2, 3, 3, 1};
        int[] status5 = {1, 1, 2, 2, 1, 3};
        int[] status6 = {1, 1, 1, 1, 1, 1};
        int[] status7 = {1, 2, 1, 1, 2, 1};
        int[] status8 = {2, 2, 3, 3, 2, 1};
        int[] status9 = {1, 1, 1, 1, 1, 2};
        int[] status10 = {2, 2, 1, 2, 3, 1};

        alumnos[0] = new AlumnoClass(123, "Mario", "Fernandez", "Aguilar", 3, "Sistemas", Materias1, Calificaciones1, status1, 75, 1);
        alumnos[1] = new AlumnoClass(456, "Pedro", "Aguilar", "Andrade", 5, "Industrial", Materias2, Calificaciones2, status2, 98, 0);
        alumnos[2] = new AlumnoClass(789, "Iosef", "Tarasov", "Sanchez", 7, "Alimentarias", Materias3, Calificaciones3, status3, 87, 0);
        alumnos[3] = new AlumnoClass(147, "Axelin", "Lara", "Rodriguez", 7, "Sistemas", Materias4, Calificaciones4, status4, 65, 2);
        alumnos[4] = new AlumnoClass(258, "Andrew", "Chavez", "Damian", 1, "Industrial", Materias5, Calificaciones5, status5, 73, 1);
        alumnos[5] = new AlumnoClass(369, "Lupe", "Hernandez", "Lopez", 3, "Alimentarias", Materias6, Calificaciones6, status6, 83, 0);
        alumnos[6] = new AlumnoClass(321, "Juan", "Ponce", "Maldonado", 9, "Industrial", Materias7, Calificaciones7, status7, 82, 0);
        alumnos[7] = new AlumnoClass(654, "Fercho", "Gimenez", "Torres", 9, "Alimentarias", Materias8, Calificaciones8, status8, 70, 2);
        alumnos[8] = new AlumnoClass(987, "Armando", "Manzanero", "Juarez", 3, "Sistemas", Materias9, Calificaciones9, status9, 93, 0);
        alumnos[9] = new AlumnoClass(159, "Panfilo", "Marquez", "Salinas", 5, "Sistemas", Materias10, Calificaciones10, status10, 88, 1);
    }

    //metodo de búsqueda binaria, **********falta acoplar a nuestro proyecto*********
    public static int binarySearch(int[] array, int minLimit, int maxLimit, int value) {
        if (maxLimit >= 0 && array[minLimit] <= value && array[maxLimit] >= value) {
            int mid = (minLimit + maxLimit) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                return binarySearch(array, mid + 1, maxLimit, value);
            }
            return binarySearch(array, minLimit, mid - 1, value);
        }
        return -1;
    }

    //metodo de búsqueda binaria, **********falta acoplar a nuestro proyecto*********
    public static int binarySearchStatus(AlumMatRecu[] array, int minLimit, int maxLimit, int value) {
        if (maxLimit >= 0 && array[minLimit].getStatus() <= value && array[maxLimit].getStatus() >= value) {
            int mid = (minLimit + maxLimit) / 2;
            if (array[mid].getStatus() == value) {
                return mid;
            } else if (array[mid].getStatus() < value) {
                return binarySearchStatus(array, mid + 1, maxLimit, value);
            }
            return binarySearchStatus(array, minLimit, mid - 1, value);
        }
        return -1;
    }

    //metodo de búsqueda binaria
    public static int binarySearchCarrera(AlumnoClass[] array, int minLimit, int maxLimit, String value) {
        if (maxLimit >= 0 && array[minLimit].getCarrera().compareTo(value) <= 0 && array[maxLimit].getCarrera().compareTo(value) >= 0) {
            int mid = (minLimit + maxLimit) / 2;
            if (array[mid].getCarrera().compareTo(value) == 0) {
                return mid;
            } else if (array[mid].getCarrera().compareTo(value) < 0) {
                return binarySearchCarrera(array, mid + 1, maxLimit, value);
            }
            return binarySearchCarrera(array, minLimit, mid - 1, value);
        }
        return -1;
    }

    public static AlumnoClass[] eliminaElemento(AlumnoClass[] arreglo, int posicion) {
        AlumnoClass[] aux = new AlumnoClass[arreglo.length - 1];
        int j = 0;
        for (int i = 0; i < arreglo.length; i++) {
            if (i != posicion) {                //éste método únicamente nos sirve para eliminar
                aux[j] = arreglo[i];         // los alumnos reprobados que vayamos pasando al arreglo
                j++;
            }
        }
        return aux;
    }

    public static AlumMatRecu[] eliminaMateria(AlumMatRecu[] arreglo, int posicion) {
        AlumMatRecu[] aux = new AlumMatRecu[arreglo.length - 1];
        int j = 0;
        for (int i = 0; i < arreglo.length; i++) {
            if (i != posicion) {                //éste método únicamente nos sirve para eliminar
                aux[j] = arreglo[i];         // los alumnos reprobados que vayamos pasando al arreglo
                j++;
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
        /*
        //===========================================================================
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
         */
    }

    public static void main(String[] args) {

        //MetodoParaPruebas();
        //Se pide el tamaño del arreglo [Esto lo hice de forma temporal por si no habia otra manera
        //de fijar el tamaño del array de objetos]
        //System.out.println("Ingrese tamaño del arreglo: ");
        //int tam = Integer.parseInt(scanner.nextLine());
        //Este es el tamaño del arreglo con el tamaño pasado por teclado
        alumnos = new AlumnoClass[10];
        //int incremento = 0; //Inicio una variable que servirá como contador de las vueltas en cada registro
        //Aqui se realiza el ciclo de los registros, se detiene cuando la variable anterior
        //es igual al tamaño del arreglo de objetos pasado por teclado
        /*while (incremento != tam) {
            GuardarDatos(incremento);//Pasamos por parametro el valor de la variable que incrementa en cada vuelta
            incremento++;
        }*/
        CreacionDeObjetos();
        Menu();
    }
}
