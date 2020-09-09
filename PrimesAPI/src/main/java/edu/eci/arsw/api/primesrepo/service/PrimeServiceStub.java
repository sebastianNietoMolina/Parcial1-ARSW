package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service()
public class PrimeServiceStub implements edu.eci.arsw.api.primesrepo.service.PrimeService
{

    List<FoundPrime> primos;
    public PrimeServiceStub(){
        primos=new CopyOnWriteArrayList<>();
        FoundPrime p1 = new FoundPrime("SebastianNieto","133");
        FoundPrime p2 = new FoundPrime("user2","647");
        FoundPrime p3 = new FoundPrime("user3","6317");
        primos.add(p1);
        primos.add(p2);
        primos.add(p3);
    }

    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws PrimeException {
        boolean fly=true;
        for(FoundPrime fp: primos){
            if(fp.getPrime().equals(foundPrime.getPrime())){
                fly=false;
                throw new PrimeException("Ese primo ya esta en uso");
            }
        }
        if(fly) {
            primos.add(foundPrime);
        }
    }

    @Override
    public List<FoundPrime> getFoundPrimes() throws PrimeException {
        if (!primos.isEmpty()) {
            return primos;
        }
        throw new PrimeException("La lista esta vacia");
    }

    @Override
    public FoundPrime getPrime( String prime ) throws PrimeException
    {
        for(FoundPrime pri: primos){
            if(pri.getPrime().equals(prime)){
                return pri;
            }
        }
        throw new PrimeException("No existe es primo");
    }

    @Override
    public FoundPrime getFoundPrimesById(String primeNumber) throws PrimeException {
        for(FoundPrime pri: primos){
            if(pri.getPrime().equals(primeNumber)){
                return pri;
            }
        }
        throw new PrimeException("No existe ese usario");
    }


}
