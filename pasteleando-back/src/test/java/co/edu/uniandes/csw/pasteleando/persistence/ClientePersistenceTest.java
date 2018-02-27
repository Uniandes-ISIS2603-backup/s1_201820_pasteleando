/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author monicabayona
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest {
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ClienteEntity.class.getPackage()).
                addPackage(ClientePersistence.class.getPackage()).
                addAsManifestResource("META-INF/persistence.xml", "persistence.xml").
                addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createClienteEntityTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity entidad = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clientePersistence.create(entidad);
        
        Assert.assertNotNull(result);
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
        Assert.assertEquals(entity.getName(), entidad.getName());
    }
    
   
    
 
    
}
