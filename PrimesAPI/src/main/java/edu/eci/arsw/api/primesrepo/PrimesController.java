package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeException;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */

@RestController
@RequestMapping( value = "/primes")
public class PrimesController
{


    @Autowired
    PrimeService ps;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getFoundPrimes() {
        try {
            return new ResponseEntity<>(ps.getFoundPrimes(), HttpStatus.ACCEPTED);
        } catch (PrimeException e) {
            return new ResponseEntity<>("Erro 400", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addFoundPrime(@RequestBody FoundPrime fp){
        try {
            ps.addFoundPrime(fp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PrimeException ex) {
            return new ResponseEntity<>(" Error 500", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{primenumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getFoundPrimesById(@PathVariable String primenumber) {
        try {
            return new ResponseEntity<>(ps.getFoundPrimesById(primenumber), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR 400", HttpStatus.NOT_FOUND);
        }
    }

}
