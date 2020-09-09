package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */

@Service("PrimeService")
public interface PrimeService
{

    void addFoundPrime( FoundPrime foundPrime ) throws edu.eci.arsw.api.primesrepo.service.PrimeException;

    List<FoundPrime> getFoundPrimes() throws edu.eci.arsw.api.primesrepo.service.PrimeException;

    FoundPrime getPrime( String prime ) throws  edu.eci.arsw.api.primesrepo.service.PrimeException;

    FoundPrime getFoundPrimesById( String primeNumbe ) throws  edu.eci.arsw.api.primesrepo.service.PrimeException;

}
