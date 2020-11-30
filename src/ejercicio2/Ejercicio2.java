package ejercicio2;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {

    /*2) Lectura y escritura de objetos con serialización. Crea una aplicación que almacene los datos
básicos de un vehículo como la matrícula (String), marca (String), tamaño de depósito (doublé)
y modelo (String). Los datos anteriores se pedirán por teclado y se irán añadiendo al fichero
(no se sobrescriben los datos) cada vez que ejecutemos la aplicación. El fichero siempre será
el mismo, en todos los casos. El atributo tamaño de depósito no se incluirá en el fichero (aún
así debe pedirse por teclado). La aplicación deberá también poder visualizar los datos del
fichero.*/

    final String DIRPATH = "src\\ejercicio2\\fichero";
    final String FILEPATH = "src\\ejercicio2\\fichero\\vehicle.dat";

    private Vehiculo createAnVehicle() {
        String matricula, marca, modelo;
        double tamaño;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Introduce la matricula");
        matricula = keyboard.nextLine();

        System.out.println("Introduce la marca");
        marca = keyboard.nextLine();

        System.out.println("Introduce tamaño del deposito");
        tamaño = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.println("Introduce el modelo");
        modelo = keyboard.nextLine();


        return new Vehiculo(matricula, marca, tamaño, modelo);
    }

    private void writeVehicle(String path, Vehiculo vehiculo) throws IOException {
        File vehicle = new File(path);


        if (vehicle.length() > 0) {
            MyObjectOutputStream writer2 = new MyObjectOutputStream(new FileOutputStream(vehicle, true));
            writer2.writeObject(vehiculo);
        } else {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(vehicle, true));
            writer.writeObject(vehiculo);
        }


    }

    private void readObjectFile(String path) throws IOException {
        File vehicle = new File(path);
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(vehicle));
        Vehiculo vehiculo;
        try {
            while (true) {
                vehiculo = (Vehiculo) reader.readObject();
                System.out.println(vehiculo);
            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Ejercicio2() throws IOException {
        createFileIfNotExist(new File(DIRPATH), "dir");
        createFileIfNotExist(new File(FILEPATH), "file");
        writeVehicle(FILEPATH, createAnVehicle());
        readObjectFile(FILEPATH);
    }

    public static void main(String[] args) throws IOException {
        new Ejercicio2();
    }

    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

}
