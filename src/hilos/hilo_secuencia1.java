/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import gui.ventana;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ezequielbrrt
 */
public class hilo_secuencia1 extends Thread {
    private static volatile boolean  activo = true;
    private int estado;
    private static int flag = 0;
    private static int contador = 0;
    private long n1 = 0, n2 = 1, aux,limite = 1000;
    private int contadorF = 1,cont = 1; 
   JTextPane mensaje;
  
   
    static long threadId;
    public hilo_secuencia1(JTextPane mensaje){
        this.mensaje =  mensaje;
    }

    
   
    public void run() {
        StyledDocument doc = mensaje.getStyledDocument();
        try {
              threadId = Thread.currentThread().getId();
                String strLong = Long.toString(threadId);
               // main.imprimir("Id Hilo Secuencia 1 "+strLong);
 
            while(activo){                
                if(flag == 0){
                    Thread.sleep(300);  
                    aux = n1;
                    n1 = n2;
                    n2 = aux + n1;
                   doc.insertString(0,"Fibonacci "+n2+"\n",null);

                }
                else{
                    if(esPrimo(contadorF)){
                        Thread.sleep(300);
                        doc.insertString(0,"Primo " + contadorF+"\n",null);
                    }
                    contadorF++;
                }
            
            
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }catch(Exception e){
            System.out.println("error");
        }
        
        
        
    }
    
    public static void cambio(){
        contador ++;
        if(contador %2 == 0) flag = 0;
        else flag = 1;
    }
    public static void detener(){
        activo = false;
    }
    
    public static String tarea(){
        if(flag == 1) return "Hilo1 Secuencia: Fibonacci\n";
        return "Hilo1 Secuencia: Primos\n";
    }
    
    public boolean esPrimo(int numero){
        int aux;
        for(int cont=2;cont<numero;cont++){
            aux=numero%cont;
            if(aux==0) return false;
        }
    return true;
    }
    
     public static long id(){
        return threadId;
    }
    
}


