/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author fmgarcia
 */
public class Hilo extends Thread {
    private int nombre;
    private int duración;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        // Lanzamos dos hilos de forma concurrente que duren un tiempo aleatorio:
        Random aleatorio = new Random(1337);
        //for (int i=0; i<2; i++) {
            // Un hilo tendrá un tiempo de ejecución comprendido entre los 0 y 10 segundos.
          
            //new Thread(new Hilo(i, aleatorio.nextInt(10000))).start();
            new Thread(new Hilo(1,2000)).start();
            new Thread(new Hilo(2,3000)).start();

    }


    public Hilo(int nombre, int duración) {        
        this.nombre = nombre;
        this.duración = duración;
    }

    /**
     * Método que contiene las acciones que hará el hilo cuando se ejecute.
     */
    @Override
    public void run() {
        for (int i = 0; i < 3;i++){
        if (this.nombre == 1){
            System.out.println("hilo1");
            try {
                Thread.sleep(2000);
                            System.out.println("FLAG DE INTERRUPCIÓN VALE "+Thread.interrupted());
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("excepcion");
            }
             System.out.println("hilo1 de nuevo");
        }
        if(this.nombre == 1){
            System.out.println("hilo2");
             try {
                Thread.sleep(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("excepcion hilo2");
            }
              System.out.println("hilo2 de nuevo");
              
        }
        }
       // System.out.println("Soy el hilo "+this.nombre+" y he iniciado mi ejecución. Mi flag de interrupción vale ");
        //System.out.println("Soy el hilo "+this.nombre+" y voy a parar mi ejecución "+this.duración+" ms.");
       /*try {            // Para simular la finalización inesperada del hilo 1 lo hacemos lanzando una nueva excepción:
            if (this.nombre==1) throw new InterruptedException();
            Thread.sleep(this.duración);
        } catch (InterruptedException ex) { // Sleep puede lanzar una excepción que aborte la ejecución del hilo.
            // AQUÍ VA EL CÓDIGO ESPECÍFICO DEL TRATAMIENTO INESPERADO DEL HILO:
            System.out.println("INTERRUPCIÓN INESPERADA DEL HILO "+this.nombre);
            // El flag interrupted de la clase Thread se activa:
            System.out.println("FLAG DE INTERRUPCIÓN VALE "+Thread.interrupted());
            return;
        }*/
        //System.out.println("Soy el hilo "+this.nombre+" y continúo mi ejecución.");
        //System.out.println("Soy el hilo "+this.nombre+" y he finalizado mi ejecución.");
    }
}