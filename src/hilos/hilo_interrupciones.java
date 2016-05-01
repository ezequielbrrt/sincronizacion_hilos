/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import gui.ventana;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ezequielbrrt
 */
public class hilo_interrupciones extends Thread{
    private     static long threadId;
    private int nombre;
    private JTextPane mensaje,mensajeInt;
     private  Logger logger ;  
     private static FileHandler fh;  
     public static String estado;
    
    public hilo_interrupciones(int nombre, JTextPane mensaje,
        JTextPane mensajeInt, Logger logger){
       this.nombre = nombre;
       this.mensaje = mensaje;
       this.mensajeInt =  mensajeInt;
       this.logger = logger;
    }
    public void run() {
        estado = "iniciado hilo";
        StyledDocument doc = mensajeInt.getStyledDocument();
        if(this.nombre == 1){
            (new Thread(new hilo_secuencia1(mensaje))).start();
            try {
                
                Logger.getLogger("MyLog");
                fh = new FileHandler("MyLogFile.log");  
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter);  
                logger.setUseParentHandlers(false);
               //logger.info("hola soy un hilo jijij");
                
                doc.insertString(0,"Iniciando hilo 1 \n",null);
            } catch (BadLocationException ex) {
                Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 1; i<=10; i++){
                try {
                    logger.info("ESTADO: Durmiendo Hilo1");
                    Thread.sleep(2000);
                     
                   logger.info("Secuencia hilo1 "+tareahilo1());
                    doc.insertString(0,"Interrupción del hilo 1 # "+i+"\n",null);
                    hilo_secuencia1.cambio();
                    doc.insertString(0,"Reanudación del hilo 1 # "+i+"\n",null);
                    logger.info("Secuencia hilo1 "+tareahilo1());
                    logger.info("Numero de Interruciones al hilo1  "+i);
                    logger.info("ESTADO: Ejecucion Hilo1");
                } catch (InterruptedException ex) {
                    Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadLocationException ex) {
                    Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
             hilo_secuencia1.detener();
              try {
                doc.insertString(0,"Hilo 1 Terminado \n",null);
                logger.info("ESTADO: Terminado Hilo1");
            } catch (BadLocationException ex) {
                Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
        if(this.nombre == 2){
            (new Thread(new hilo_secuencia2(mensaje))).start();
             try {
                doc.insertString(0,"Iniciando hilo 2 \n",null);
            } catch (BadLocationException ex) {
                Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 1; i<=10; i++){
                try {
                     logger.info("ESTADO: Durmiendo Hilo 2");
                    Thread.sleep(3000);
                     
                    doc.insertString(0,"Interrupción del hilo 2 # "+i+"\n",null);
                    logger.info("ESTADO: Ejecucion Hilo 2");
                    logger.info("Secuencia hilo2 "+tareahilo2());
                    hilo_secuencia2.cambio();   
                     doc.insertString(0,"Reanudación del hilo 2 # "+i+"\n",null);
                     
                     logger.info("Secuencia hilo2 "+tareahilo2());
                     logger.info("Numero de Interruciones al hilo2  "+i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadLocationException ex) {
                    Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            hilo_secuencia2.detener();
            try {
                doc.insertString(0,"Hilo 2 Terminado \n",null);
                 logger.info("ESTADO: Terminado Hilo2");
            } catch (BadLocationException ex) {
                Logger.getLogger(hilo_interrupciones.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
         
     
    }
    public static long id(){
        long threadId = Thread.currentThread().getId();
        return threadId;
    }
    public static long idHilo1(){
        return hilo_secuencia1.id();
    }
    public static long idHilo2(){
        return hilo_secuencia2.id();
    }
    public static String tareahilo1(){
        return hilo_secuencia1.tarea();
    }
     public static String tareahilo2(){
        return hilo_secuencia2.tarea();
    }
    
}
