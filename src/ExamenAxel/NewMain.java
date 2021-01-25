/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamenAxel;

import java.util.Scanner;

/**
 *
 * @author axel
 */
public class NewMain {

    static Scanner scanner = new Scanner(System.in);
    static int Contador = 0;
    static int tamañoArreglo = 1;
    static Contactos[] contactos = new Contactos[tamañoArreglo];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        boolean salir = false;
        System.out.println("Ingrese opcion: ");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1:
                System.out.println("Registrar contacto: ");
                System.out.println("Nombre: ");
                String Nombre = scanner.nextLine();
                System.out.println("Apellido Paterno: ");
                String ApellidoPaterno = scanner.nextLine();
                System.out.println("Apellido Materno: ");
                String ApellidoMaterno = scanner.nextLine();
                System.out.println("Telefono: ");
                String Telefono = scanner.nextLine();
                System.out.println("Correo: ");
                String Correo = scanner.nextLine();
                System.out.println("Facebook: ");
                String Facebook = scanner.nextLine();

                if (Contador <= tamañoArreglo) {
                    contactos[Contador] = new Contactos(Nombre, ApellidoPaterno, ApellidoMaterno, Telefono, Correo, Facebook);
                    Contador++;
                } else {
                    System.out.println("Arreglo lleno");
                }
                Menu();
                break;

            case 2:
                System.out.println("Consultar contactos por apellido paterno");
                String Buscar = scanner.nextLine();
                
                int encontrados = 0;
                
                
                for(int i = 0; i < contactos.length; i++){
                    if(contactos[i].getApellidoPaterno().compareToIgnoreCase(Buscar) == 0){
                        System.out.println("Apellido Paterno: " + contactos[i].getApellidoPaterno()
                        + "Apellido Materno: " + contactos[i].getApellidoMaterno()
                        + "\tNombre: " + contactos[i].getNombre() + "\tTelefono: " + contactos[i].getTelefono()
                        + "\tCorreo: " + contactos[i].getCorreo() + "\tFacebook: " + contactos[i].getFacebook());
                        encontrados++;
                    }
                    
                    if(encontrados == 0){
                        System.out.println("No se encontraron resultados");
                    }
                }
                
                System.out.println("Ap. Paterno \t Ap. Materno \t Nombre \t Correo \t Facebook");
                for(Contactos registro : contactos){
                    if(registro.getApellidoPaterno().compareToIgnoreCase(Buscar) == 0){
                        System.out.println(registro.getApellidoPaterno()
                        + " " + registro.getApellidoMaterno()
                        + "\t" + registro.getNombre() + "\t" + registro.getTelefono()
                        + "\t" + registro.getCorreo() + "\t" + registro.getFacebook());
                    }
                }
                Menu();
                break;
                
            case 3: 
                System.out.println("Consultar contacto por numero de telefono");
                
                String b = scanner.nextLine();
                
                int x = 0;
                
                
                for(int i = 0; i < contactos.length; i++){
                    if(contactos[i].getTelefono().compareToIgnoreCase(b) == 0){
                        System.out.println("Apellido Paterno: " + contactos[i].getApellidoPaterno()
                        + "Apellido Materno: " + contactos[i].getApellidoMaterno()
                        + "\tNombre: " + contactos[i].getNombre() + "\tTelefono: " + contactos[i].getTelefono()
                        + "\tCorreo: " + contactos[i].getCorreo() + "\tFacebook: " + contactos[i].getFacebook());
                        x++;
                    }
                    
                    if(x == 0){
                        System.out.println("No se encontraron resultados");
                    }
                }
                
                System.out.println("Ap. Paterno \t Ap. Materno \t Nombre \t Correo \t Facebook");
                for(Contactos registro : contactos){
                    if(registro.getTelefono().compareToIgnoreCase(b) == 0){
                        System.out.println(registro.getApellidoPaterno()
                        + " " + registro.getApellidoMaterno()
                        + "\t" + registro.getNombre() + "\t" + registro.getTelefono()
                        + "\t" + registro.getCorreo() + "\t" + registro.getFacebook());
                    }
                }
                Menu();
                break;
                
            case 4:
                System.out.println("Consultar contacto por numero de correo: ");
                
                String bus = scanner.nextLine();
                
                int y = 0;
                
                
                for(int i = 0; i < contactos.length; i++){
                    if(contactos[i].getCorreo().compareToIgnoreCase(bus) == 0){
                        System.out.println("Apellido Paterno: " + contactos[i].getApellidoPaterno()
                        + "Apellido Materno: " + contactos[i].getApellidoMaterno()
                        + "\tNombre: " + contactos[i].getNombre() + "\tTelefono: " + contactos[i].getTelefono()
                        + "\tCorreo: " + contactos[i].getCorreo() + "\tFacebook: " + contactos[i].getFacebook());
                        y++;
                    }
                    
                    if(y == 0){
                        System.out.println("No se encontraron resultados");
                    }
                }
                
                System.out.println("Ap. Paterno \t Ap. Materno \t Nombre \t Correo \t Facebook");
                for(Contactos registro : contactos){
                    if(registro.getCorreo().compareToIgnoreCase(bus) == 0){
                        System.out.println(registro.getApellidoPaterno()
                        + " " + registro.getApellidoMaterno()
                        + "\t" + registro.getNombre() + "\t" + registro.getTelefono()
                        + "\t" + registro.getCorreo() + "\t" + registro.getFacebook());
                    }
                }
                Menu();
                break;
                
            case 5:
                break;
        }
    }
}
