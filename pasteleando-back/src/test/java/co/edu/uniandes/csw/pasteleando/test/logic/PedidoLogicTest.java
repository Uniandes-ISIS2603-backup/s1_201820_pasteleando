/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.PedidoLogic;
import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import co.edu.uniandes.csw.pasteleando.persistence.PedidoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ni.ramirez10
 */

@RunWith(Arquillian.class)
public class PedidoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PedidoLogic pedidoLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PedidoEntity> pedidoData = new ArrayList<>();
    
    private List<PqrsEntity> pqrsData = new ArrayList();
    
     private List<CalificacionEntity> calificacionesData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PedidoEntity.class.getPackage())
                .addPackage(PedidoLogic.class.getPackage())
                .addPackage(PedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
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
        em.createQuery("delete from PqrsEntity").executeUpdate();
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            PqrsEntity pqrs = factory.manufacturePojo(PqrsEntity.class);
            em.persist(pqrs);
            pqrsData.add(pqrs);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            calificacionesData.add(entity);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            PedidoEntity entity = factory.manufacturePojo(PedidoEntity.class);
            em.persist(entity);
            pedidoData.add(entity);
        }
    }
    
    
    
    
}
