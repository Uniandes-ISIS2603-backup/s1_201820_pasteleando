/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity ;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author m.leona
 */
@RunWith(Arquillian.class)

public class TarjetaPuntosTest {
    
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPuntosEntity.class.getPackage())
                .addPackage(TarjetaPuntosPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Test
    public void createTarjetaPuntosEntityTest() {
    PodamFactory factory = new PodamFactoryImpl();
    TarjetaPuntosEntity  newEntity = factory.manufacturePojo(TarjetaPuntosEntity .class);
    TarjetaPuntosEntity  result = persistence.create(newEntity);

    Assert.assertNotNull(result);
    TarjetaPuntosEntity  entity = em.find(TarjetaPuntosEntity .class, result.getId());
    Assert.assertNotNull(entity);
    Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Inject
    private TarjetaPuntosPersistance persistence;
      
    @PersistenceContext
    private EntityManager em;
}
