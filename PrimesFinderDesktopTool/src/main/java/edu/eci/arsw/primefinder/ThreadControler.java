package edu.eci.arsw.primefinder;

import java.math.BigInteger;

/**
 *
 * @author JUAN NIETO
 */
public class ThreadControler extends Thread {

    int min,max;
    public boolean pausa;
    public int terminado;

    public ThreadControler(int min, int max){
        this.min=min;
        this.max=max;
        pausa=true;

    }

    public void pausa(boolean pausa) {
        this.pausa = pausa;
    }

    public synchronized void reanudar(boolean pausa){
        this.pausa=pausa;
        notifyAll();
    }

    public int getTerminado() {
        return terminado;
    }

    @Override
    public void run(){

        if(pausa){

            PrimesResultSet prs=new PrimesResultSet("john");

            String mi = Integer.toString(min);
            String ma = Integer.toString(max);
            PrimeFinder.findPrimes(new BigInteger(String.valueOf(min)), new BigInteger(String.valueOf(max)), prs);

            System.out.println("Prime numbers found:");

            System.out.println(prs.getPrimes());

            terminado+=1;

        }else{
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}