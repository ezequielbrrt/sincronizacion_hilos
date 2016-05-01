/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import gui.ventana;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ezequielbrrt
 */
public class hilo_secuencia2 extends Thread {
    private static volatile boolean activo = true;
    private static int flag = 0 ;
    int estado=1;
    int estado2 =1;
    private static int contador = 0;
     private int contadorF = 2, cont=1; 
    static long threadId ;
   JTextPane mensaje ;
   
   public hilo_secuencia2(JTextPane mensaje){
       this.mensaje = mensaje;
   }
     
    
     
     
    @Override
    public void run() {
        StyledDocument doc = mensaje.getStyledDocument();
        Random rnd = new Random();
         threadId = Thread.currentThread().getId();
                String strLong = Long.toString(threadId);
               // main.imprimir("Id Hilo Secuencia2 "+strLong);
         try {
            while(activo){
                if (flag == 0){
                    doc.insertString(0,"Random " + rnd.nextInt(200)+"\n",null);
                    Thread.sleep(300);
                   
                }
                else{
                     if(contadorF % 2 == 0){
                        Thread.sleep(300);
                        doc.insertString(0,"Pares " + contadorF+"\n",null);
                    }
                    contadorF++;
                }
               
            }
          
            
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } catch (BadLocationException ex) {
            Logger.getLogger(hilo_secuencia2.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

     public static void cambio(){
         contador ++;
         if(contador %2 == 0) flag =0;
         else flag = 1;  
     }
     public static void detener(){
         activo = false;
     }
      public static long id(){
        return threadId;
    }
      public static String tarea(){
          if (flag == 0) return "Hilo2 Secuencia: Random";
          return "Hilo2 Secuencia: Pares";
      }
}
