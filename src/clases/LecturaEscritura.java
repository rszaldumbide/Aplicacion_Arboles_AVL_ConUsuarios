/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Lisao13
 */
public class LecturaEscritura {
    
    private String ruta, texto;
    
    public LecturaEscritura(String ruta, String texto) {
        this.ruta=ruta;
        this.texto=texto;
    }
    
    public LecturaEscritura(String ruta) {
        this.ruta=ruta;
    }
    
    public String leer() throws FileNotFoundException, IOException {
        
        String cadena, lectura = "";
        FileReader f= new FileReader(ruta);
        BufferedReader br= new BufferedReader(f);
        
        while((cadena=br.readLine())!=null){
            lectura+= cadena+"\n";
        }
        br.close();
        return lectura;
        
    }
    
    public void escribir() throws IOException {
        FileWriter fw= new FileWriter(ruta,true);
        PrintWriter pw= new PrintWriter(fw);
        
        if(!"".equals(texto))
           pw.println(texto);
        
        fw.close();
        
    }
    
    public void editar() throws IOException {
        
        FileWriter fw= new FileWriter(ruta);
        PrintWriter pw= new PrintWriter(fw);
        
        if(!"".equals(texto))
           pw.println(texto);
        
        fw.close();
        
    }
    
    public void borrar() throws IOException {
        
        FileWriter fw= new FileWriter(ruta);
        PrintWriter pw= new PrintWriter(fw);
       
        pw.println("");
        
        fw.close();
        
    }
    
    
    
}
    

