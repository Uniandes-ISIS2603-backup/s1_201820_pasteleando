/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
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
 * @author ni.ramirez10
 */

@RunWith(Arquillian.class)
public class PqrsPersistenceTest 
{
    /**
     * Inyección de la dependencia a la clase PqrsPersistence cuyos métodos
     * se van a probar.
     */
    
    @Inject
    private PqrsPersistence pqrsPersistence;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Pqrs, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PqrsEntity.class.getPackage())
                .addPackage(PqrsPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Prueba para crear una Pqrs
     */
    
    @Test
    public void createPqrsTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PqrsEntity newEntity = factory.manufacturePojo(PqrsEntity.class);
        PqrsEntity result = pqrsPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PqrsEntity entity = em.find(PqrsEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
}
