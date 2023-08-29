/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Lisao13
 */
public class cargar {
    private MetodosAvl arbol = new MetodosAvl();
    
    private String texto;
    private String[] info;
    private String[] cadena;

    public cargar(String texto) {
        this.texto = texto;    
    }
    
    public MetodosAvl separar(){
        this.cadena = this.texto.split("\n");
        for (String cadena1 : this.cadena) {
            this.info = cadena1.split(",");
            usuarios u = new usuarios(this.info[0], this.info[1],Integer.parseInt(this.info[2]), this.info[3], Integer.parseInt(this.info[4]));
            arbol.insertar(u);
        }
       
       return arbol;
    }
    
    
    
}
