package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

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
        terminado=0;

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
            terminado=1;
            pausa=false;

        }else{
            synchronized (this) {
                try {
                    System.out.println(min+"  alskf "+max);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}