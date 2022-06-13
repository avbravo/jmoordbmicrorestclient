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
public class SendConfigurationRepository extends Repository<SendConfiguration>{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public SendConfigurationRepository(){
        super(SendConfiguration.class, "autentification","sendconfiguration");
    }
}
