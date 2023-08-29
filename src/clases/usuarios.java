/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author ricar
 */
public class usuarios{
    
    public String user;
    public String nombreYApellido;
    public int edad;
    public String genero;
    public int numeroQuejas;

    public usuarios(){  
    }
    
    public usuarios(String user, String nombreYApellido, int edad, String genero, int numeroQuejas) {
        this.user = user;
        this.nombreYApellido = nombreYApellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroQuejas = numeroQuejas;
    }

    @Override
    public String toString() {
        return user;
        //return "Usuarios{" + "Usuario=" + user + ", Nombre y Apellido=" + nombreYApellido + ", Edad=" + edad + ", Genero=" + genero + ", Numero de Quejas=" + numeroQuejas + '}';
    }   

}
