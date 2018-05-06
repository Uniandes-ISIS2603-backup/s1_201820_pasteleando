/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
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
 * @author monicabayona
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
      @Inject
    UserTransaction utx;
      
       private List<CalificacionEntity> data = new ArrayList<>();
    
    @Deployment 
    public static JavaArchive createDeployment()
    { 
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionPersistence.class.getPackage())
                .addPackage(CalificacionEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
        em.createQuery("delete from PedidoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
     
     private void insertData() 
     {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Test
    public void createCalificacionEntityTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entidad = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionPersistence.create(entidad);
        
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(entidad.getName(), entity.getName());
    
    }
    
     @Test
    public void getCalificacionTest() 
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = calificacionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para actualizar una calificacion.
     */
    
    @Test
    public void updatePedidoTest() 
    {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        
        newEntity.setId(entity.getId());
        
        calificacionPersistence.update(newEntity);
        
        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar una calificacion.
     */
    
    @Test
    public void deleteCalificacionTest() 
    {
        CalificacionEntity entity = data.get(0);
        calificacionPersistence.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para consultar la lista de Calificaciones.
     */
    @Test
    public void getCalificacionesTest() 
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = calificacionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
      
    }
    
    @Test
    public void findByNameTest()
    {
        CalificacionEntity ent = data.get(0);
        CalificacionEntity newEnt = calificacionPersistence.findByName(ent.getName());
        Assert.assertNotNull(newEnt);
        Assert.assertEquals(newEnt.getId(), ent.getId());
    }
    
    
}
