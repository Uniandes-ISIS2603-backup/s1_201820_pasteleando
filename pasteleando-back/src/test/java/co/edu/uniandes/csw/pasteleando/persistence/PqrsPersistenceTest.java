/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
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
     * Contexto transacional
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Atributo que modela una lista de pqrs.
     */
    private List<PqrsEntity> data = new ArrayList<>();
    
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
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    
    private void clearData() 
    {
        em.createQuery("delete from PqrsEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
     
     private void insertData() 
     {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            PqrsEntity entity = factory.manufacturePojo(PqrsEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
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
    
    /**
     * Prueba para consultar una pqrs.
     */
    
    @Test
    public void getPqrsTest() 
    {
        PqrsEntity entity = data.get(0);
        PqrsEntity newEntity = pqrsPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para actualizar una pqrs.
     */
    
    @Test
    public void updatePqrsTest() 
    {
        PqrsEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PqrsEntity newEntity = factory.manufacturePojo(PqrsEntity.class);
        
        newEntity.setId(entity.getId());
        
        pqrsPersistence.update(newEntity);
        
        PqrsEntity resp = em.find(PqrsEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar una pqrs.
     */
    
    @Test
    public void deletePqrsTest() 
    {
        PqrsEntity entity = data.get(0);
        pqrsPersistence.delete(entity.getId());
        PqrsEntity deleted = em.find(PqrsEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para consultar la lista de pqrs.
     */
    
    @Test
    public void getLPqrsTest() 
    {
        PqrsEntity entity = data.get(0);
        PqrsEntity newEntity = pqrsPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado()); 
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo()); 
    }
    
    /**
     * prueba para encontrar un pqrs con el nombre
     */
    @Test
    public void getPQRSByNameTest()
    {
        PqrsEntity ent = data.get(0);
        PqrsEntity entEnc = pqrsPersistence.findByName(ent.getName());
        Assert.assertEquals(entEnc, ent);
    }
    
}
