/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ricar
 */
public class MetodosAvl {
    
    private nodo raiz;
    int quejas, quejaMayor, quejaMenor;    
    String msg="";

    public nodo getRaiz() {
        return raiz;
    }

    public MetodosAvl() {
        this.raiz = null;
    }
    
    public nodo rotacionSimpleDerecha(nodo raiz){
        
        nodo tmp=raiz.izq;
        raiz.izq=tmp.der;
        tmp.der=raiz;
        return tmp;
    }
    
    public nodo rotacionSimpleIzquierda(nodo raiz){
        
        nodo tmp=raiz.der;
        raiz.der=tmp.izq;
        tmp.izq=raiz;
        return tmp;
    }
    
    public nodo rotacionDobleDerechaIzquierda(nodo raiz){
        
        raiz.der=rotacionSimpleDerecha(raiz.der);
        return rotacionSimpleIzquierda(raiz);
    }
    
    public nodo rotacionDobleIzquierdaDerecha(nodo raiz){
        
        raiz.izq=rotacionSimpleIzquierda(raiz.izq);
        return rotacionSimpleDerecha(raiz);
    }
    
    int valorAltura;
    public int altura(nodo raiz){
        valorAltura=0;
        altura(raiz, 1);
        return valorAltura;
    }
    private void altura(nodo aux, int nivel){
        if(aux!=null){
            altura(aux.izq, nivel+1);
            if (nivel>valorAltura) {
                valorAltura=nivel;
            }
            altura(aux.der, nivel+1);
        }
    }

//METODO PARA INSERTAR LOS USUARIOS
    public void insertar(usuarios dato){
        
        nodo nuevo=new nodo(dato);
        
        if(this.raiz==null){
            this.raiz=nuevo;
        }else{
            this.raiz=insertar(this.raiz, nuevo);
        }
    }
    private nodo insertar(nodo raiz, nodo nuevo){
        
        nodo nuevaRaiz=raiz;
        
        if(nuevo.info.user.compareTo(raiz.info.user)<0){//se va a la izq
            if(raiz.izq==null){
                raiz.izq=nuevo;
            }else{
                raiz.izq=insertar(raiz.izq, nuevo);
                //a partir de aqui se equilibria
                //calculando las alturas de cada rama, izq, der
                int altDer=altura(raiz.der);
                int altIzq=altura(raiz.izq);
                int fe=altDer-altIzq;
                
                if(fe==-2){
                    if(nuevo.info.user.compareTo(raiz.izq.info.user)<0){//si se cumple es simple
                        nuevaRaiz=rotacionSimpleDerecha(raiz);
                    }else{
                        nuevaRaiz=rotacionDobleIzquierdaDerecha(raiz);
                    }
                }
            }
        }else if(nuevo.info.user.compareTo(raiz.info.user)>0){//se va a la derecha
            if(raiz.der==null){
                raiz.der=nuevo;
            }else{
                raiz.der=insertar(raiz.der, nuevo);
                
                int altDer=altura(raiz.der);
                int altIzq=altura(raiz.izq);
                int fe=altDer-altIzq;
                
                if(fe==2){
                    if(nuevo.info.user.compareTo(raiz.der.info.user)>0){
                        nuevaRaiz=rotacionSimpleIzquierda(raiz);
                    }else{
                        nuevaRaiz=rotacionDobleDerechaIzquierda(raiz);
                    }
                }
            }
        }
        return nuevaRaiz;
    }
    
//METODO QUE ELIMINA USUARIOS
    public boolean eliminarUsuario(String user){
        nodo aux = this.raiz;
        nodo padre = this.raiz;
        boolean hijoizq = true;
        while (aux.info.user.equalsIgnoreCase(user)){
            padre = aux;
            if(Integer.parseInt(user)<Integer.parseInt(aux.info.user)){
                hijoizq = true;
                aux = aux.izq;
            }else{
                hijoizq = false;
                aux = aux.der;
            }
            if(aux == null){
                return false;
            }
        }
        if(aux.izq == null && aux.der == null){
            if(aux == this.raiz){
                this.raiz = null;
            }else{
                if(hijoizq == true){
                    padre.izq = null;
                }else{
                    padre.der = null;
                }
            }
        }else if(aux.der == null){
            if(aux == this.raiz){
                this.raiz = aux.izq;
            }else if(hijoizq == true){
                padre.izq = aux.izq;
            }else{
                padre.der = aux.izq;
            }
        }else if(aux.izq == null){
            if(aux == this.raiz){
                this.raiz = aux.der;
            }else if(hijoizq == true){
                padre.izq = aux.der;
            }else{
                padre.der = aux.der;
            }
        }else{
            nodo cambio = reemplazarAr(aux);
            if(aux == this.raiz){
                raiz = cambio;
            }else if(hijoizq == true){
                padre.izq = cambio;
            }else{
                padre.der = cambio;
            }
        }
        JOptionPane.showMessageDialog(null, " Eliminado \n" + user);
        return true;
    } 
    
    private nodo reemplazarAr(nodo aux) {
        nodo padre = aux;
        nodo rpz = aux;
        nodo temp = aux.der;
        while (temp != null) {
            padre = rpz;
            rpz = temp;
            temp = temp.izq;
        }
        if (rpz != aux.der) {
            padre.izq = rpz.der;
            rpz.der = aux.der;
        }
        return rpz;
    }  
    
    public void borrarMenor() {
        nodo aux = raiz.izq;
        if (raiz != null) {
            if (raiz.izq == null) {
                raiz = raiz.der;
            } else {
                nodo anterior = raiz;
                aux = raiz.izq;
                while (aux.izq != null) {
                    anterior = aux;
                    aux = aux.izq;
                }
                anterior.izq = aux.der;
            }
        }
        JOptionPane.showMessageDialog(null, "Valor eliminado");
    }
    public void borrarMayor() {
        nodo aux = raiz.izq;
        if (raiz != null) {
            if (raiz.der == null) {
                raiz = raiz.izq;
            } else {
                nodo anterior = raiz;
                aux = raiz.der;
                while (aux.der != null) {
                    anterior = aux;
                    aux = aux.der;
                }

                anterior.der = aux.izq;
            }
        }
        JOptionPane.showMessageDialog(null, "Valor eliminado");
    }  
    
    private nodo nodoMayorValor(nodo user){
        nodo actual= user;
        while(actual.der!=null){
            actual=actual.der;
        }
        return actual;
    }
    
    public void eliminar(String user) {
        raiz = eliminarNodo(raiz, user);
    }

    public nodo eliminarNodo(nodo raiz, String user) {
        int fe;
        if (raiz == null) {
            return raiz;
        }
        if (user.compareTo(raiz.info.user) < 0) {
            raiz.izq = eliminarNodo(raiz.izq, user);
        } else if (user.compareTo(raiz.info.user) > 0) {
            raiz.der = eliminarNodo(raiz.der, user);
        } else {
            //encontro el nodo
            //caso 1: hoja o unico hijo
            if (raiz.izq == null || raiz.der == null) {
                nodo aux = null;
                if (raiz.izq == null) {
                    aux = raiz.der;
                } else {
                    aux = raiz.izq;
                }

                //caso de ser hoja
                //elimina el actual y lo deja en null
                if (aux == null) {
                    raiz = null;
                } else {
                    //caso con un hijo
                    //reemplaza el actual por el hijo
                    raiz = aux;
                }
            } else {
                //caso con dos hijos
                nodo aux = nodoMayorValor(raiz.der);

                //copio el dato del que le reemplaza
                raiz.info = aux.info;

                //se elimina el actual
                raiz.der = eliminarNodo(raiz.der, aux.info.user);
            }
            if (raiz == null) {
                return raiz;
            }
            //actualizar altura
            int hDer = altura(raiz.der);
            int hIzq = altura(raiz.izq);
            fe = hDer - hIzq;
            //equilibrando
            if (fe == 2) {
                //simple izq
                if (raiz.info.user.compareTo(raiz.der.info.user) > 0) {
                    return rotacionSimpleIzquierda(raiz);
                } else {
                    return rotacionDobleDerechaIzquierda(raiz);
                }
            }
            if (fe == -2) {
                //simple der
                if (raiz.info.user.compareTo(raiz.izq.info.user) < 0) {
                    return rotacionSimpleDerecha(raiz);
                } else {
                    return rotacionDobleIzquierdaDerecha(raiz);
                }
            }
        }
        return raiz;
    }
    
    //no vale
//    public nodo eliminarNodo(nodo raiz, String user){
//        nodo padre= raiz.Padre;
//        if(user.compareTo(raiz.info.user)<0){
//            eliminarNodo(raiz.izq, user);
//        }else if(user.compareTo(raiz.info.user)>0){
//            eliminarNodo(raiz.der, user);     
//        }else{
//            //los 3 casos que existe
//            //CASO 1: SI ES HOJA
//            if(raiz.izq==null && raiz.der==null){
//                if(Integer.parseInt(user)<Integer.parseInt(padre.info.user)){
//                    padre.izq=null;
//                }else if(Integer.parseInt(user)>Integer.parseInt(padre.info.user)){
//                    padre.der=null;
//                }
//            }
//            //CASO2: TIENE UN HIJO IZQUIERDO O DERECHO
//            if(raiz.izq!=null && raiz.der==null){
//                padre.izq=raiz.izq;
//                raiz.izq.Padre=padre;
//            }else{
//                if(raiz.izq==null && raiz.der!=null){
//                    padre.der=raiz.der;
//                    raiz.der.Padre=padre;
//                }
//            }
//            //CASO3: TIENE 2 HIJOS
//            if(raiz.izq!=null && raiz.der!=null){
//                nodo menorRama= raiz.der;
//                nodo padreX= raiz.Padre;
//                nodo aux=raiz.der;
//                padreX.der=menorRama;
//                menorRama.der=aux;
//                menorRama.izq=raiz.izq;
//                menorRama.Padre=padreX;
//                aux.izq=null;
//                }
//            }
//        return raiz;
//    }
    
//METODO BUSCAR USUARIO
    //de acuerdo al user
    public String BuscarString(String user){
        nodo raizAux=this.raiz;
        while(raizAux!=null){
            if(user.equalsIgnoreCase(raizAux.info.user)){
                return "Usuario: "+raizAux.info.user+"; Nombres: "+raizAux.info.nombreYApellido
                        +"; Edad: "+raizAux.info.edad+"; Genero: "+raizAux.info.genero
                        +"; Quejas: "+raizAux.info.numeroQuejas;                 //rompe ciclo
            }else{
                if(user.compareTo(raizAux.info.user)<0){
                    raizAux=raizAux.izq;
                }else{
                    raizAux=raizAux.der;
                }
            }
        }
        return null;
    } 
    //booleanamente
    public boolean BuscarBooleano(String user){
        nodo raizAux=this.raiz;
        while(raizAux!=null){
            if(user.equalsIgnoreCase(raizAux.info.user)){
                return true;             //rompe ciclo
            }else{
                if(user.compareTo(raizAux.info.user)<0){
                    raizAux=raizAux.izq;
                }else{
                    raizAux=raizAux.der;
                }
            }
        }
        return false;
    } 
    
//METODO PARA LAS QUEJAS
    public int Quejas(String user){
        quejas=0;
        nodo raizAux=this.raiz;
        while(raizAux!=null){
            if(user.equalsIgnoreCase(raizAux.info.user)){
                quejas=raizAux.info.numeroQuejas;
                quejas++;
                raizAux.info.numeroQuejas=quejas;
                //if(quejas==5){
                    break;
                //}
            }else{
                if(user.compareTo(raizAux.info.user)<0){
                    raizAux=raizAux.izq;
                }else{
                    raizAux=raizAux.der;
                }
            }
        }
        return quejas;
    }
    
//METODO PARA MOSTRAR LOS USUARIOS ALFABETICAMENTE
    public String mostrarDatos() {
        msg=""; 
        return mostrarDatos(this.raiz);
    }
    private String mostrarDatos(nodo raiz){ 
        if (raiz != null) {
            mostrarDatos(raiz.izq);
            msg += "=> " + raiz.info.user+"<p>";
            mostrarDatos(raiz.der);
        }
        return msg;
    }
    
//METODO PARA RESUMIR LOS USUARIOS DE ACUERDO A CUAL TIENE MAYOR QUEJAS Y CUAL MENOR
    public int mayorQuejas() {
        quejaMayor=0;
        return mayorQuejas(this.raiz);
    }
    private int mayorQuejas(nodo raiz){ 
        if (raiz != null) {
            mayorQuejas(raiz.izq);
            if(raiz.info.numeroQuejas>quejaMayor){
                quejaMayor=raiz.info.numeroQuejas;
            }
            mayorQuejas(raiz.der);
        }
        return quejaMayor;
    }
    public String buscarQuejaMayor(int queja){
        queja=mayorQuejas();
        nodo raizAux=this.raiz;
        while(raizAux!=null){
            if(queja==raizAux.info.numeroQuejas){
                return "Usuario: "+raizAux.info.user
                        +"; Quejas: "+raizAux.info.numeroQuejas;
            }else{
                if(queja<raizAux.info.numeroQuejas){
                    raizAux=raizAux.izq;
                }else{
                    raizAux=raizAux.der;
                }
            }
        }
        return null;
    }
    
    public int menorQuejas() {
        quejaMenor=5;
        return menorQuejas(this.raiz);
    }
    private int menorQuejas(nodo raiz){ 
        if (raiz != null) {
            menorQuejas(raiz.izq);
            if(raiz.info.numeroQuejas<quejaMenor){
                quejaMenor=raiz.info.numeroQuejas;
            }
            menorQuejas(raiz.der);
        }
        return quejaMenor;
    }
    public String buscarQuejaMenor(int queja){
        queja=menorQuejas();
        nodo raizAux=this.raiz;
        while(raizAux!=null){
            if(queja==raizAux.info.numeroQuejas){
                return "Usuario: "+raizAux.info.user
                        +"; Quejas: "+raizAux.info.numeroQuejas;
            }else{
                if(queja<raizAux.info.numeroQuejas){
                    raizAux=raizAux.izq;
                }else{
                    raizAux=raizAux.der;
                }
            }
        }
        return null;
    }
   
}
