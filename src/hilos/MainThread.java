/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JTextPane;

/**
 *
 * @author ezequielbrrt
 */
public class MainThread implements Runnable{
    private static Logger logger = Logger.getLogger("MyLog");  
    private static FileHandler fh;  
     private JTextPane mensajeHilo1, mensajeInt, mensajeHilo2;

    //hilo_secuencia1 hilo1 =  new hilo_secuencia1();
    //hilo_secuencia2 hilo2 = new hilo_secuencia2();
    
    public MainThread(JTextPane mensajeHilo1,JTextPane mensajeInt, JTextPane mensajeHilo2){
        this.mensajeHilo1 =  mensajeHilo1;
        this.mensajeInt =  mensajeInt;
        this.mensajeHilo2 = mensajeHilo2;
    }


    @Override
    public void run() {
        //Tiene que instanciar al hilo de interrupciones
        
          try {  

        //Configura el loggger con el manejador y el formato
        fh = new FileHandler("MyLogFile.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
        logger.setUseParentHandlers(false);
        (new Thread(new hilo_interrupciones(1,mensajeHilo1,mensajeInt,logger))).start();
        (new Thread(new hilo_interrupciones(2,mensajeHilo2,mensajeInt,logger))).start();
        // the following statement is used to log any messages  
        logger.info("Nombre hilo3: hilo_interrupciones\n");
        logger.info("Nombre hilo1: hilo_secuencia1\n");
        logger.info("Nombre hilo2: hilo_secuencia2\n");
        logger.info("Id del hilo_interrupciones "+ hilo_interrupciones.id()+"\n");
        logger.info("Id del hilo_secuencia1 "+ hilo_interrupciones.idHilo1()+"\n");

    } catch (SecurityException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  

    }
    void imprimir(String interrupcion_) {
        logger.info(interrupcion_); 
    }
}
