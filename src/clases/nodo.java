/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author ricardo
 */
public class nodo {
    
    public nodo izq;
    public nodo der;
    public nodo Padre;
    public usuarios info;

    public nodo(usuarios info) {
        this.info = info;
        this.izq=null;
        this.der=null;
        this.Padre=null;
    }
    
}
