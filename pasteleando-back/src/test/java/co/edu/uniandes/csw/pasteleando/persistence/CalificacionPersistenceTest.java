/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author monicabayona
 */
public class CalificacionPersistenceTest {
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment 
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(CalificacionPersistence.class.getPackage())
                .addPackage(CalificacionEntity.class.getPackage()).
                addAsManifestResource("META-INF/persistence.xml", "persistence.xml").
                addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public void createCalificacionEntityTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entidad = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionPersistence.create(entidad);
        
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(entidad.getName(), entity.getName());
    
    }
    public CalificacionPersistenceTest() {
    }
    
    
    
}
