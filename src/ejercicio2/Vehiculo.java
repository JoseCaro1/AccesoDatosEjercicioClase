package ejercicio2;

import java.io.Serializable;

public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String matricula;
    private String marca;
    private transient double deposito;
    private String modelo;

    public Vehiculo(String matricula, String marca, double deposito, String modelo) {

        this.matricula = matricula;
        this.marca = marca;
        this.deposito = deposito;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return String.format("Matricula: %s\nMarca: %s\nModelo: %s\n", matricula, marca, modelo);
    }
}
