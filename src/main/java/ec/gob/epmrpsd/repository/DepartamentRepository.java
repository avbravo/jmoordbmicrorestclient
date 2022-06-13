/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package ec.gob.epmrpsd.repository;

import com.avbravo.jmoordb.mongodb.repository.Repository;
import ec.gob.epmrpsd.model.*;
import jakarta.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class DepartamentRepository extends Repository<Departament> {

   
    public DepartamentRepository(){
        super(Departament.class,"autentification","departament");
    }
 

}
