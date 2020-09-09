package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    public static List<ThreadControler> tl;
    public static int nt;
    public static int min;
    public static int max;
    public static int saltos;

	public static void main(String[] args) {

        int maxPrim=1000;
        int terminado=0;
        nt=4;
        min=0;
        saltos= maxPrim/nt;
        tl = new CopyOnWriteArrayList<>();

        for(int i=0; i<nt; i++){
            min=max;
            max=min+saltos;
            if(i==nt-1){
                max=maxPrim;
            }
            //System.out.println(min+"faljsdfk"+max+"akjdga"+saltos);
            ThreadControler t = new ThreadControler(min,max);
            tl.add(t);
            t.start();
        }

        while(terminado!=nt) {
            for (ThreadControler t : tl) {
                if (t.getTerminado() == 1) {
                    t.pausa(false);
                    terminado += t.getTerminado();
                }
            }
            if(terminado==nt){
                System.out.println("Finalizado");
            }
            else {
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                        Thread.sleep(10);
                        for (ThreadControler t : tl) {
                            t.pausa(false);
                        }
                        System.out.println("Idle CPU ");
                    } else {
                        for (ThreadControler t : tl) {
                            t.reanudar(true);
                        }
                        System.out.println("User working again!    " + terminado);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("Salida");
	}
	
}


